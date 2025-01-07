/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icaro.gutenbook.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int birth_year;
    private int death_year;

    // Esto vincula al autor con los libros
    @ManyToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    // ********  Constructores  ********

    public Autor() {}

    public Autor(String nombre, int birth_year, int death_year) {
        this.nombre = nombre;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    // ********  Getters and Setters  **********


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getDeath_year() {
        return death_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    // To String

    @Override
    public String toString() {
        return "\n" + "Autor: " + this.nombre + "\n" +
                " Fecha de Nacimiento: " + this.birth_year + "\n" +
                " Fecha de Fallecimiento: " + this.death_year + "\n";

    }
}
