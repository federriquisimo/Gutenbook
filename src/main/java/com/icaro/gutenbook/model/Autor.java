/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icaro.gutenbook.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro>libros;

    public Autor(DatosAutor autor){
        this.nombre=autor.nombre();
        this.fechaDeNacimiento= autor.fechaDeNacimiento();
        this.fechaDeFallecimiento= autor.fechaDeFallecimiento();
    }

    public Autor(){};

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaNacimiento) {
        this.fechaDeNacimiento = fechaNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaFallecimiento) {
        this.fechaDeFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaDeNacimiento +
                ", fechaFallecimiento=" + fechaDeFallecimiento +
                ", libros=" + libros +
                '}';
    }

    public void imprimirInformacion() {
        System.out.println("*****Autor*****");
        System.out.println("Nomber:" + nombre);
        System.out.println("Fecha de nacimiento: "+fechaDeNacimiento);
        System.out.println("Fecha de fallecimiento: "+fechaDeFallecimiento);
        System.out.println("Libros: "+libros);
        System.out.println("");
    }
}
