/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icaro.gutenbook.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private Long id;
    @Column(unique = true)
    private String titulo;

    @JoinTable(name = "autores_libros", joinColumns = @JoinColumn(name = "libros_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor = new ArrayList<Autor>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    private Boolean copyright;

    private Integer cantidadDescargas;

    //********** Getters and Setters ********//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    // ********** Constructors **********//
    public Libro(DatosLibro libroBuscado) {
        this.id = libroBuscado.id();
        this.titulo = libroBuscado.titulo();
        this.autor = libroBuscado.autores().stream()
                .map(da -> new Autor(da.nombre(), da.fechaDeNacimiento(), da.fechaDeFallecimiento()))
                .toList();
        this.idiomas = libroBuscado.idioma();
        this.copyright = libroBuscado.copyright();
        this.cantidadDescargas = libroBuscado.cantidadDescargas();


    }

    public Libro(){}

    @Override
    public String toString() {
        return  '\n' + "Titulo = " + titulo + '\n' +
                "id = " + id + '\n' +
                 autor + '\n' +
                "idiomas = " + idiomas + '\n' +
                "copyright = " + copyright + '\n' +
                "cantidadDescargas = " + cantidadDescargas + '\n';
    }
}
