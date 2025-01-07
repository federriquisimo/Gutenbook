/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icaro.gutenbook.repository;

import com.icaro.gutenbook.model.Autor;
import com.icaro.gutenbook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    @Query("SELECT a FROM Libro b JOIN b.autor a")
    List<Autor> encontrarAutores();

    @Query("SELECT a FROM Libro b JOIN b.autor a WHERE a.death_year >= :anio")
    List<Autor> encontrarAutoresVivos(int anio);

    @Query("SELECT DISTINCT l FROM Libro b JOIN b.idiomas l WHERE :idioma IN (l)")
    List<Libro> encontrarLibroPorIdioma(String idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.cantidadDescargas DESC LIMIT 10")
    List<Libro> obtenerTopLibros();

    @Query("SELECT a FROM Libro b JOIN b.autor a WHERE a.nombre LIKE :nombre%")
    Autor encontrarAutor(String nombre);


    @Query("SELECT a FROM Libro b JOIN b.autor a WHERE a.birth_year BETWEEN :anioDesde AND :anioHasta AND a.death_year BETWEEN :anioDesde AND :anioHasta")
    List<Autor> obtenerAutoresVivosRango(int anioDesde, int anioHasta);


}
