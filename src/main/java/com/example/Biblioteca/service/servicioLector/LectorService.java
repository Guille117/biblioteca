package com.example.Biblioteca.service.servicioLector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.dto.DtoLector;
import com.example.Biblioteca.dto.LectorDto.DtoLectorIngreso;
import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.repository.LectorRepository;
import com.example.Biblioteca.service.InstitucionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LectorService implements ILectorService{
    @Autowired
    private LectorRepository lecRepo;
    @Autowired
    private InstitucionService inserv;

    @Override
    public void guardar(Lector t) {
        /*validadores.forEach(v -> v.validar(t));

        if(t.getInstitucion() != null){
           t.setInstitucion(inserv.obtenerUno(t.getInstitucion().getIdInstitucion()));
        }

        lecRepo.save(t);*/
    }

    @Override
    public Lector obtenerUno(Long id) {
        return lecRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Lector no encontrado."));
    }

    @Override
    public List<Lector> obtenerTodos() {
        return lecRepo.findAll();
    }

    @Override
    public List<Lector> mostrarPorInsitucion(Long idInstitucion) {
        return lecRepo.findByInstitucionIdInstitucion(idInstitucion);
    }

    @Override
    public void eliminar(Long id) {
        lecRepo.deleteById(id);
    }

    @Override
    public void actualizarLector(DtoLector lectorDto) {
        Lector lector = this.obtenerUno(lectorDto.getIdLector());
        
        if(lectorDto.getNombre() != null){
            lector.setNombre(lectorDto.getNombre());
        }

        if(lector.getApellido() != null){
            lector.setApellido(lectorDto.getApellido());
        }

        if(lectorDto.getNumTelefono() != null){
            lector.setNumTelefono(lectorDto.getNumTelefono());
        }

        if(lectorDto.getInstitucion() != null){
            lector.setInstitucion(inserv.obtenerUno(lectorDto.getIdLector()));            
        }
        lecRepo.save(lector);
    }


    public Lector convertirALector(DtoLector lec){
        Lector lector = null;
        if(lec.getInstitucion() != null){
            lector = new Lector(null, null, null, lec.getInstitucion(), null);
        }
        return lector;
    }

    @Override
    public Lector sevaLector(DtoLectorIngreso lec) {
        return lecRepo.save(convertirALector(lec));
    }



    private Lector convertirALector(DtoLectorIngreso l1){       //convertir a partir de un dto de ingreso de lector a lector 
        Lector lec = new Lector();
        lec.setNombre(l1.getNombre());
        lec.setApellido(l1.getApellido());
        lec.setNumTelefono(l1.getTelefono());
        
        lec.setInstitucion((l1.getIdIstitucion() != null) ?         // si nos llega una institución la buscamos y setemos.
                            inserv.obtenerUno(l1.getIdIstitucion()) : null);
        
        return lec;
    }
}
