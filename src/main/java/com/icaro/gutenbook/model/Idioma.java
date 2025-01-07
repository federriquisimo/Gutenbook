/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.icaro.gutenbook.model;

public enum Idioma {
    es("Español"),
    en("Inglés"),
    de("Alemán"),
    fr("Francés"),
    nd("No disponible"),
    ;

    private String idiomaCompleto;

    Idioma(String idiomaCompleto){
        this.idiomaCompleto=idiomaCompleto;
    }

    public static Idioma stringToEnum(String idioma){
        for(Idioma item:Idioma.values()){
            if(item.name().equalsIgnoreCase(idioma)){
                return item;
            }
        }
        return nd;
    }

    public static void listarIdiomas(){
        for (Idioma idioma:Idioma.values()){
            System.out.println(idioma.name()+" - "+idioma.getIdiomaCompleto());
        }
    }

    public String getIdiomaCompleto() {
        return idiomaCompleto;
    }
}
