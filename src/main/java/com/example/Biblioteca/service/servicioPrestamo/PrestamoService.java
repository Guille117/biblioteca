package com.example.Biblioteca.service.servicioPrestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Biblioteca.dto.DtoPrestamo;
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
    @Transactional
    public Prestamo guardarPrestamo(DtoPrestamoIngreso t) {
        Prestamo pr = convertirAPrestamo(t);
        validadores.forEach(v -> v.validar(pr));

        if(pr == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
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
    public DtoPrestamo mostrarPrestamo(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Debe ingresar id");
        }
        return new DtoPrestamo(presRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Préstamo no encontrado")));
    }

    @Override
    public List<DtoPrestamo> mostrarPrestamos() {
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
    public List<DtoPrestamo> mostrarActivos(boolean activo) {
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


    private void prestamo(Long idLib, boolean prestar){
        Libro libro = libServ.obtenerUnoLibro(idLib);
        if(prestar){
            if(libro.getDisponible() > 0){
                libro.setDisponible(libro.getDisponible() - 1);
                libro.setEnPrestamo(libro.getEnPrestamo() + 1);
            }else{
                throw new RuntimeException("No hay suficientes libros disponibles");
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
        pres1.setLector(lecServ.obtenerUno(pr.getIdLector()));
        pres1.setFechaVencimiento(pr.getFechaVencimiento());
    
        return pres1;
    }

    private List<DtoPrestamo> convertirADtoPrestamos(List<Prestamo> pres){
        List<DtoPrestamo> prestamos = pres.stream().map(DtoPrestamo::new).collect(Collectors.toList());
        return prestamos;
    }

}
