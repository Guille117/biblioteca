package com.example.Biblioteca.service.sercicioInstitucion;

import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.service.IGenericService;

public interface IInsitucionService extends IGenericService<Institucion>{
    public void actualizar(Long id);
}
