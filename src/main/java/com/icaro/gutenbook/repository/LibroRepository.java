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

    @Query("SELECT a FROM Libro b JOIN b.autor a WHERE a.birth_year <= :agnio AND a.death_year >= :agnio")
    List<Autor> encontrarAutoresVivos(int agnio);

    @Query("SELECT DISTINCT l FROM Libro b JOIN b.idiomas l WHERE :idioma IN (l)")
    List<Libro> encontrarLibroPorIdioma(String idioma);

   
}
