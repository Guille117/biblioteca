package com.example.Biblioteca.service.servicioLector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Biblioteca.dto.DtoLector;
import com.example.Biblioteca.modelo.Institucion;
import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.repository.InstitucionRepository;
import com.example.Biblioteca.repository.LectorRepository;
import com.example.Biblioteca.validacion.validarLector.ValidarLector;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LectorService implements ILectorService{

    @Autowired
    private LectorRepository lecRepo;
    @Autowired
    private InstitucionRepository instRepo;
    @Autowired
    private List<ValidarLector> validadores;

    @Override
    public void guardar(Lector t) {
        validadores.forEach(v -> v.validar(t));

        Institucion insti = null;

        if(t.getInstitucion() != null){
            insti = (t.getInstitucion().getIdInstitucion() != null) ?
                    instRepo.findById(t.getInstitucion().getIdInstitucion()).orElse(null) :
                    instRepo.findByNombre(t.getInstitucion().getNombre());
        }
        
        if(insti != null) t.setInstitucion(insti);
        
        lecRepo.save(t);
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
        
        if(convertirALector(lectorDto) != null){
             Lector lectorPivote = convertirALector(lectorDto);
             validadores.forEach(v -> v.validar(lectorPivote));
            Institucion ins = (lectorDto.getInstitucion().getIdInstitucion() != null) ?
                    instRepo.findById(lectorDto.getInstitucion().getIdInstitucion()).orElse(null) :
                    instRepo.findByNombre(lectorDto.getInstitucion().getNombre());
            if(ins == null){
                ins = lectorDto.getInstitucion();
                instRepo.save(ins);
            }
            lector.setInstitucion(ins);
        }
        
        if(lectorDto.getNombre() != null){
            lector.setNombre(lectorDto.getNombre());
        }

        if(lectorDto.getApellido() != null){
            lector.setApellido(lectorDto.getApellido());
        }
        
        if(lector.getNumTelefono() != null){
            lector.setNumTelefono(lectorDto.getNumTelefono());
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
}
