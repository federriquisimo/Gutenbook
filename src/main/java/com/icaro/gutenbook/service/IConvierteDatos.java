package com.icaro.gutenbook.service;
//CREADA EN EL VIDEO 1.4
public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
