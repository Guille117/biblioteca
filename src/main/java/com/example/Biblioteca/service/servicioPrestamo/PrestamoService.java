package com.example.Biblioteca.service.servicioPrestamo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Biblioteca.dto.DtoPrestamo;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.modelo.Prestamo;
import com.example.Biblioteca.repository.LibroRepository;
import com.example.Biblioteca.repository.PrestamoRepository;
import com.example.Biblioteca.validacion.ValidarPrestamo.IValidarPrestamo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;


@Service
public class PrestamoService implements IPrestamoService{

    @Autowired
    private PrestamoRepository presRepo;
    @Autowired
    private LibroRepository libRepo;
    @Autowired
    private List<IValidarPrestamo> validadores;

    @Override
    @Transactional
    public void guardar(Prestamo t) {
        validadores.forEach(v -> v.validar(t));
        List<Libro> lib = t.getLibros();
        lib.forEach(l -> prestamo(l, true));
        presRepo.save(t);
    }

    @Override
    public Prestamo obtenerUno(Long id) {
       return presRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Préstamo no encontrado"));
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return presRepo.findAll();
    }
    
    @Override
    public DtoPrestamo mostrarPrestamo(Long id) {
        return new DtoPrestamo(this.obtenerUno(id));
    }

    @Override
    public List<DtoPrestamo> mostrarPrestamos() {
        List<DtoPrestamo> prestamos = this.obtenerTodos().stream().map(DtoPrestamo::new).collect(Collectors.toList());
        return prestamos;
    }

    @Override
    @Transactional
    public void finalizarPrestamo(Prestamo prestamo) {
        List<Libro> libros = prestamo.getLibros();
        libros.forEach(l -> prestamo(l, false));
        prestamo.setPrestamoActivo(false);

    }

    @Override
    public List<DtoPrestamo> mostrarActivos(boolean activo) {
        List<Prestamo> pres = presRepo.findByPrestamoActivo(activo);
        List<DtoPrestamo> prestamos = pres.stream().map(DtoPrestamo::new).collect(Collectors.toList());
        return prestamos;
    }


    @Override
    public void eliminar(Long id) {
        if(!presRepo.existsByIdPrestamoAndPrestamoActivoTrue(id)){
            presRepo.deleteById(id);
        }else{
            throw new ValidationException("Este prestamo aún esta activo");
        }
        
    }


    public void prestamo(Libro lib, boolean prestar){
        Libro libro = libRepo.findById(lib.getIdLibro()).orElse(null);
        if(prestar){
            if(libro.getDisponible() > 0){
                libro.setDisponible(libro.getDisponible() - 1);
                libro.setEnPrestamo(libro.getEnPrestamo() + 1);
            }else{
                throw new RuntimeException("No hay suficientes libros disponibles");
            }
           
        }else{
            libro.setDisponible(libro.getDisponible() + 1);;
            libro.setEnPrestamo(libro.getEnPrestamo() - 1);;
        }
        
    }   
}
