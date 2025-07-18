# Noticias_Video_App

Una aplicación de Android simple diseñada para mostrar los 10 resúmenes semanales más recientes en formato de video.

## Descripción del Proyecto

 Noticias_Video_App es una aplicación Android básica que consume datos de los **10 resúmenes semanales más recientes** desde un endpoint de API REST y los muestra de manera clara y organizada en un `ListView`. La aplicación se enfoca en proporcionar un acceso rápido a los contenidos de video más recientes, ordenados por su fecha de publicación.

## Características

* **Consumo de API REST:** Utiliza la potente librería Retrofit para realizar solicitudes HTTP eficientes a un endpoint de API REST específico.
* **Visualización de Resúmenes Semanales:** Presenta un listado dinámico de los **10 resúmenes semanales más recientes**, mostrando información clave como el título y la fecha de publicación.
* **Diseño Limpio y Receptivo:** La interfaz de usuario es simple y está optimizada para mostrar el contenido de los resúmenes de forma legible en diferentes dispositivos Android.
* **Adaptador de Lista Personalizado:** Implementa un `ArrayAdapter` personalizado (`NoticiaAdapter`) para renderizar cada elemento de video en el `ListView` con un diseño específico.
* **Manejo de Red Avanzado:** Configurada con OkHttp para manejar eficientemente las operaciones de red, incluyendo la gestión de certificados SSL.

## Estructura del Proyecto

* `MainActivity.java`: La actividad principal que orquesta la carga de datos, la configuración del cliente de red y la actualización de la interfaz de usuario con los resúmenes.
* `ApiService.java`: Una interfaz declarativa para Retrofit que define el contrato de la API para la obtención de los resúmenes semanales.
* `Video.java`: La clase modelo de datos que estructura la información de cada resumen (fecha de publicación, título, URL de video y portada del video).
* `VideoAdapter.java`: El adaptador personalizado que toma los objetos `Noticia` y los mapea a las vistas individuales dentro del `ListView`, utilizando `list_item_noticia.xml`.
* `activity_main.xml`: Define la estructura principal de la interfaz de usuario de la actividad, conteniendo el `ListView`.
* `list_item_video.xml`: Define el layout visual para cada elemento individual en la lista de resúmenes, mostrando el título y la fecha de publicación.


