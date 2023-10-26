package com.example.Biblioteca.service;

import java.util.List;

public interface IGenericService <T>{
    public void guardar(T t);
    public T obtenerUno(Long id);
    public List<T> obtenerTodos();
    public void eliminar(Long id);
}
