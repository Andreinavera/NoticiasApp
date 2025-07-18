package com.example.noticiasapp;

public class Noticia {
    private String fechapub;
    private String titulo;
    private String urlvideo1;
    private String portadaVideo;

    // Constructor
    public Noticia(String fechapub, String titulo, String urlvideo1, String portadaVideo) {
        this.fechapub = fechapub;
        this.titulo = titulo;
        this.urlvideo1 = urlvideo1;
        this.portadaVideo = portadaVideo;
    }

    // Getters y Setters
    public String getFechapub() {
        return fechapub;
    }

    public void setFechapub(String fechapub) {
        this.fechapub = fechapub;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlvideo1() {
        return urlvideo1;
    }

    public void setUrlvideo1(String urlvideo1) {
        this.urlvideo1 = urlvideo1;
    }

    public String getPortadaVideo() {
        return portadaVideo;
    }

    public void setPortadaVideo(String portadaVideo) {
        this.portadaVideo = portadaVideo;
    }

    @Override
    public String toString() {
        return titulo; // Esto es lo que se mostrará en el ListView
    }
}
