package com.icaro.gutenbook.main;


import com.icaro.gutenbook.service.ConsumoAPI;
import com.icaro.gutenbook.service.ConvierteDatos;
import com.icaro.gutenbook.view.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Menu menu = new Menu();      
    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
   // private final String API_KEY = "&apikey=4fc7c187";
    private ConvierteDatos conversor = new ConvierteDatos();
    
    public void muestraElMenu(){
        menu.menu();
        System.out.println("Escoge un idioma en especial ");
        //Busca los libros escritos en el idioma escogido
        var idioma = input.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?languages=" + idioma);
        //https://www.omdbapi.com/?t=game+of+thrones&apikey=4fc7c187
        //DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(json);

     /*   //Busca los datos de todas las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= datos.totalTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);

        //Mostrar solo el titulo de los episodios para las temporadas
        for (int i = 0; i < datos.totalTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporadas.size(); j++) {
                System.out.println(episodiosTemporadas.get(j).titulo());
            }
        }
        // Mejoría usando funciones Lambda
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));*/
    }
}
