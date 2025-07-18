package com.example.noticiasapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilitar EdgeToEdge para manejar márgenes
        EdgeToEdge.enable(this);

        // Establecer el contenido de la actividad
        setContentView(R.layout.activity_main);

        // Configurar el ListView
        listView = findViewById(R.id.listView);

        // Crear cliente HTTP inseguro
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiws.uteq.edu.ec/") // URL Base del API
                .client(okHttpClient) // Añadir el cliente inseguro
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Obtener las noticias
        obtenerNoticias();

        // Manejar el padding de la UI con el sistema de barras
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void obtenerNoticias() {
        // Hacer la llamada a la API
        Call<List<Noticia>> call = apiService.obtenerNoticias();
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful()) {
                    List<Noticia> noticias = response.body();
                    // Usar ArrayAdapter para mostrar las noticias en el ListView
                    ArrayAdapter<Noticia> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, noticias);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                // Manejo de errores
            }
        });
    }
}
