package com.example.Biblioteca.service;

import java.util.List;

public interface IGenericService <T>{
    public void guardar(T t);
    public T obtenerUno(Long id);
    public List<T> obtrnerTodos();
    public void eliminar(Long id);
    public T obtenerPorNombre(String nombre); 
}
