package com.example.noticiasapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
// Puedes agregar ImageView y una librería como Picasso o Glide si quieres mostrar portadaVideo
// import android.widget.ImageView;
// import com.squareup.picasso.Picasso; // O Glide

import java.util.List;

public class VideoAdapter extends ArrayAdapter<Noticia> {

    public VideoAdapter(Context context, List<Noticia> noticias) {
        super(context, 0, noticias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener el objeto Noticia para esta posición
        Noticia noticia = getItem(position);

        // Si la vista no está siendo reutilizada, inflarla
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_video, parent, false);
        }

        // Obtener las referencias de las vistas del diseño personalizado
        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvFechaPublicacion = convertView.findViewById(R.id.tvFechaPublicacion);
        // ImageView ivPortada = convertView.findViewById(R.id.ivPortada); // Si añades ImageView

        // Rellenar las vistas con los datos de la noticia
        if (noticia != null) {
            tvTitulo.setText(noticia.getTitulo());
            tvFechaPublicacion.setText(noticia.getFechapub());

            // Si tienes una URL de imagen, cárgala aquí (ejemplo con Picasso)
            // if (noticia.getPortadaVideo() != null && !noticia.getPortadaVideo().isEmpty()) {
            //     Picasso.get().load(noticia.getPortadaVideo()).into(ivPortada);
            // } else {
            //     ivPortada.setImageResource(R.drawable.placeholder_image); // Imagen por defecto
            // }
        }

        // Devolver la vista completa para renderizarla en el ListView
        return convertView;
    }
}