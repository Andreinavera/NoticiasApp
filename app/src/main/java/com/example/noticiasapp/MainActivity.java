package com.example.noticiasapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

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

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView listView;
    private ApiService apiService;
    private VideoAdapter videoAdapter;

    // Tu token Bearer
    // ADVERTENCIA: INCORPORAR TOKENS DIRECTAMENTE EN EL CÓDIGO FUENTE
    // NO ES UNA PRÁCTICA SEGURA PARA APLICACIONES EN PRODUCCIÓN.
    // CONSIDERA ALMACENARLOS DE FORMA SEGURA (ej. SharedPreferences cifradas, NDK).
    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJfeDF1c2VyZGV2IiwiaWF0IjoxNzUyODY4NjIwLCJleHAiOjE3NTI5NTUwMjB9.tZPdC3wEOhgHZz_QFk5TQa2SrFXimlgVSFa6cwhiwY"; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        videoAdapter = new VideoAdapter(this, Collections.emptyList());
        listView.setAdapter(videoAdapter);

        // Crear cliente HTTP inseguro (mantener para desarrollo si el servidor usa un certificado autofirmado)
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiws.uteq.edu.ec/") //
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        obtenerNoticias();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void obtenerNoticias() {
        Log.d(TAG, "Intentando obtener noticias de la API con token Bearer...");

        // Añadir el prefijo "Bearer " al token
        String authorizationHeader = "Bearer " + BEARER_TOKEN;

        // Pasar el encabezado de autorización a la llamada de la API
        Call<List<Noticia>> call = apiService.obtenerNoticias(authorizationHeader);
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful()) {
                    List<Noticia> noticias = response.body();
                    if (noticias != null && !noticias.isEmpty()) {
                        Log.d(TAG, "Noticias obtenidas exitosamente. Cantidad: " + noticias.size());
                        videoAdapter.clear();
                        videoAdapter.addAll(noticias);
                        videoAdapter.notifyDataSetChanged();
                    } else {
                        Log.w(TAG, "La API devolvió una lista de noticias vacía o nula.");
                        Toast.makeText(MainActivity.this, "No hay noticias disponibles en este momento.", Toast.LENGTH_LONG).show();
                        videoAdapter.clear();
                        videoAdapter.notifyDataSetChanged();
                    }
                } else {
                    String errorBody = "";
                    try {
                        if (response.errorBody() != null) {
                            errorBody = response.errorBody().string();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Error al leer errorBody: " + e.getMessage());
                    }
                    Log.e(TAG, "Error en la respuesta de la API: Código " + response.code() + ", Mensaje: " + response.message() + ", Body de error: " + errorBody);
                    Toast.makeText(MainActivity.this, "Error al cargar noticias: " + response.code() + " " + response.message(), Toast.LENGTH_LONG).show();
                    videoAdapter.clear();
                    videoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Log.e(TAG, "Fallo al conectar con la API para obtener noticias: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
                videoAdapter.clear();
                videoAdapter.notifyDataSetChanged();
            }
        });
    }
}