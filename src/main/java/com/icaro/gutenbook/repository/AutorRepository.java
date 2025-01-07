/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icaro.gutenbook.repository;


import com.icaro.gutenbook.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(:nombre)")
    Optional<Autor> obtenerAutorPorNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE :anio>=a.fechaNacimiento AND :anio<a.fechaFallecimiento")
    List<Autor> obtenerAutoresVivosEnAnio(int anio);
}