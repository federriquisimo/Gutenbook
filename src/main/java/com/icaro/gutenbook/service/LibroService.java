/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icaro.gutenbook.service;


import com.icaro.gutenbook.model.Autor;
import com.icaro.gutenbook.model.Datos;
import com.icaro.gutenbook.model.DatosLibro;
import com.icaro.gutenbook.model.Libro;
import com.icaro.gutenbook.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repoLibro;


    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private String URL_BASE = "https://gutendex.com";

    public void guardarLibro(DatosLibro d) {
        Libro nuevoLibro = new Libro(d);
        repoLibro.save(nuevoLibro);
        System.out.println(nuevoLibro.toString());
    }

    public void buscarLibroPorTitulo(String titulo) {
        Optional<Libro> libroBuscado = repoLibro.findByTituloContainsIgnoreCase(titulo);

        if (libroBuscado.isPresent()) {
            System.out.println("El libro es: " + libroBuscado.get());
        } else {
            String json = consumoApi.obtenerDatos(URL_BASE + "/books/?search=" + titulo.replace(" ", "%20")); // Primero guardo el json
            Datos libroBuscadoWeb = conversor.obtenerDatos(json, Datos.class); // Aplico el primer tratamiento de datos
            List<DatosLibro> libroWeb = libroBuscadoWeb.resultado(); // Segundo tratamiento de datos

            // Ahora toca guardar en la BD libroBuscadoWeb y mostrar el resultado en pantalla
            guardarLibro(libroWeb.get(0));

        }
    }

    public void listaLibros() {
        List<Libro> libros = repoLibro.findAll();
        libros.stream().forEach(System.out::println);
    }

    public void listaAutores() {
        List<Autor> autores = repoLibro.encontrarAutores();
        autores.forEach(a -> System.out.println(a.toString()));
    }

    public void listaAutoresVivos(int anio) {
        List<Autor> autoresVivos = repoLibro.encontrarAutoresVivos(anio);
        autoresVivos.stream().forEach(System.out::println);
    }

    public void ListaLibrosPorIdioma(String idioma) {
        List<Libro> librosIdioma = repoLibro.encontrarLibroPorIdioma(idioma);
        librosIdioma.stream().forEach(System.out::println);
    }

    public void obtenerDatosEstadisticos() {
        List<Libro> librosRepo = repoLibro.findAll();
        ArrayList<Libro> libros = new ArrayList<Libro>(librosRepo);
        DoubleSummaryStatistics data = libros.stream().collect(Collectors.summarizingDouble(Libro::getCantidadDescargas));
        System.out.println(
                "----- DATOS DE LOS LIBROS -----"+
                        "\nMedia de descargas: "+ String.format("%1.2f", data.getAverage())+
                        "\nMayor descargada: "+data.getMax()+
                        "\nMenor descargada "+data.getMin()+
                        "\nCantidad de libros almacenados: "+data.getCount()+
                        "\n------------------------------");
    }

    public void obtenerTopLibros() {
        List<Libro> topLibros = repoLibro.obtenerTopLibros();
        topLibros.forEach(System.out::println);
    }

    public void obtenerAutor(String nombre) {
        Autor autor = repoLibro.encontrarAutor(nombre);
        System.out.println(autor.toString());
    }

    public void obtenerAutoresVivosRango(int anioDesde, int anioHasta) {
        List<Autor> autores = repoLibro.obtenerAutoresVivosRango(anioDesde, anioHasta);
        autores.forEach(System.out::println);
    }



}