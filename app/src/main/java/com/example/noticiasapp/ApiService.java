package com.example.noticiasapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header; // Importar Header
import java.util.List;

public interface ApiService {
    @GET("h6RPoSoRaah0Y4Bah28eew/functions/information/entity/3")
    Call<List<Noticia>> obtenerNoticias(@Header("Authorization") String authToken); // Se añade el parámetro de encabezado
}