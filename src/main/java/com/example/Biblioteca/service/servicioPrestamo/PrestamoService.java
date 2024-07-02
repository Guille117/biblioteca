package com.example.Biblioteca.service.servicioPrestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.Excepciones.ValidacionException;
import com.example.Biblioteca.dto.PrestamoDto.DtoPrestamoMostrar;
import com.example.Biblioteca.dto.PrestamoDto.DtoPrestamoIngreso;
import com.example.Biblioteca.modelo.Libro;
import com.example.Biblioteca.modelo.Prestamo;
import com.example.Biblioteca.repository.PrestamoRepository;
import com.example.Biblioteca.service.servicioLector.LectorService;
import com.example.Biblioteca.service.servicioLibro.LibroService;
import com.example.Biblioteca.validacion.ValidarPrestamo.IValidarPrestamo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;


@Service
public class PrestamoService implements IPrestamoService{

    @Autowired
    private PrestamoRepository presRepo;
    @Autowired
    private LibroService libServ;
    @Autowired
    private LectorService lecServ;
    @Autowired
    private List<IValidarPrestamo> validadores;

    @Override
    public Prestamo guardarPrestamo(DtoPrestamoIngreso t) {
        Prestamo pr = convertirAPrestamo(t);
        validadores.forEach(v -> v.validar(pr));
        presRepo.save(pr);
        return pr;
    }

    @Override
    public Prestamo obtenerPrestamo(Long idPrestamo) {
        if(idPrestamo == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return presRepo.findById(idPrestamo).orElseThrow(() -> new EntityNotFoundException("Prestamo no encontrado"));    
    }

    
    @Override
    public DtoPrestamoMostrar mostrarPrestamo(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return new DtoPrestamoMostrar(presRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Préstamo no encontrado")));
    }

    @Override
    public List<DtoPrestamoMostrar> mostrarPrestamos() {
        return convertirADtoPrestamos(presRepo.findAll());
    }

    @Override
    @Transactional
    public void finalizarPrestamo(Prestamo prestamo) {
        List<Libro> libros = prestamo.getLibros();
        libros.forEach(l -> prestamo(l.getIdLibro(), false));
        prestamo.setPrestamoActivo(false);

    }

    @Override
    public List<DtoPrestamoMostrar> mostrarActivos(boolean activo) {
        return convertirADtoPrestamos(presRepo.findByPrestamoActivo(activo));
    }


    @Override
    public void eliminarPrestamo(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }else{
            if(!presRepo.existsByIdPrestamoAndPrestamoActivoTrue(id)){
                presRepo.deleteById(id);
            }else{
                throw new ValidationException("Este prestamo aún esta activo");
        }
        }
        
        
    }


    // metodo que nos permite realizar los cambios en un libro cuando se presta un libro o cuando se finaliza el prestamo del libro
    // cuando se presta un libro, verifica si está disponible y resta 1 al atributo "disponible" del libro
    // cuando finaliza un prestamo suma 1 a "disponible" del libro, representando la devolución del libro
    // determinamos si es prestamo y finalizar un prestamo por parametro booleano "prestar"

    private void prestamo(Long idLib, boolean prestar){
        Libro libro = libServ.obtenerUnoLibro(idLib);
        if(prestar){
            if(libro.getDisponible() > 0){
                libro.setDisponible(libro.getDisponible() - 1);
                libro.setEnPrestamo(libro.getEnPrestamo() + 1);
            }else{
                throw new ValidacionException("Libro con ID "+idLib, "Libro no disponible.");

            }
           
        }else{
            libro.setDisponible(libro.getDisponible() + 1);
            libro.setEnPrestamo(libro.getEnPrestamo() - 1);
        }
    }   

    private Prestamo convertirAPrestamo(DtoPrestamoIngreso pr){  // convertimos de un dto a prestamo
        Prestamo pres1 = new Prestamo();
        List<Libro> libros = new ArrayList<>();

        for (Long idLibro : pr.getLibros()) {
            prestamo(idLibro, true);
            libros.add(libServ.obtenerUnoLibro(idLibro));
        }

        pres1.setLibros(libros);
        pres1.setLector(lecServ.Retorna1Lector(pr.getIdLector()));
        pres1.setFechaVencimiento(pr.getFechaVencimiento());
    
        return pres1;
    }

    private List<DtoPrestamoMostrar> convertirADtoPrestamos(List<Prestamo> pres){
        List<DtoPrestamoMostrar> prestamos = pres.stream().map(DtoPrestamoMostrar::new).collect(Collectors.toList());
        return prestamos;
    }

}
