package com.example.Biblioteca.service.servicioLector;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Biblioteca.Excepciones.ElementoEnUsoException;
import com.example.Biblioteca.Excepciones.ElementoRepetidoException;
import com.example.Biblioteca.dto.LectorDto.DtoLectorModificar;
import com.example.Biblioteca.dto.LectorDto.DtoLectorMostrar;
import com.example.Biblioteca.dto.LectorDto.DtoLectorIngreso;
import com.example.Biblioteca.modelo.Lector;
import com.example.Biblioteca.repository.LectorRepository;
import com.example.Biblioteca.repository.PrestamoRepository;
import com.example.Biblioteca.service.InstitucionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LectorService implements ILectorService{
    @Autowired
    private LectorRepository lecRepo;
    @Autowired
    private InstitucionService inserv;
    @Autowired
    private PrestamoRepository peRepo;

    @Override
    public DtoLectorMostrar GuardarLector(DtoLectorIngreso lec) {
        if(lecRepo.existsByNombreAndApellido(lec.getNombre(),lec.getApellido())){           // si ya existe es lector
            throw new ElementoRepetidoException("Nombre, Apellido", "Existe ya un registro de este lector");
        }else{
            DtoLectorMostrar l = new DtoLectorMostrar(lecRepo.save(convertirALector(lec)));
            return l;
        }
    }

    @Override
    public DtoLectorMostrar obtenerUno(Long id) {
        if(id != null){
            DtoLectorMostrar l = new DtoLectorMostrar(lecRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Lector no encontrado.")));
            return l;
        }else{
            return null;
        }
        
    }

    @Override
    public List<DtoLectorMostrar> obtenerTodos() {
        return convertirADtoLectorMostrar(lecRepo.findAll());
    }

    @Override
    public List<DtoLectorMostrar> mostrarPorInsitucion(Long idInstitucion) {
        return convertirADtoLectorMostrar(lecRepo.findByInstitucionIdInstitucion(idInstitucion));
    }

    @Override
    public void eliminar(Long id) {
        if(id != null){
            this.obtenerUno(id);        // en caso no exista arrojara una excepción ya definida en este método
            
            if(peRepo.existsByLectorIdLector(id)){          // si el lector tiene un prestamo no se podrá eliminar
                throw new ElementoEnUsoException("ID","No es posible eliminar este Lector, tiene un prestamo peniente");
            }else{
                lecRepo.deleteById(id);
            }
            
        }
        
    }


    @Override
    // para realizar actualizaciones basta con setear los valores a cada uno de los campos, y lo guardará automaticamente
    // con transactional los cambios se hacer oficiales al final cuando el ultimo cambio se halla concretado caso contrario
    // no realizará ningun cambio.
    @Transactional      
    public void actualizarLector(DtoLectorModificar lectorDto) {
        Lector lector = lecRepo.findById(lectorDto.getIdLector()).orElseThrow(() -> new EntityNotFoundException("Lector no encontrado."));

        String auxNombre = lectorDto.getNombre();           // variables auxiliares
        String auxApellido = lectorDto.getApellido();

        if(auxNombre != null || auxApellido != null){       // se nos envían nombre o apellido
            if(auxNombre != null && auxApellido == null){       // si solo nos envían el nombre
                auxApellido = lector.getApellido();             // buscamos en la base de datos el apellido
            }else if(auxNombre == null && auxApellido != null){ // si solo nos envian el apellido 
                auxNombre = lector.getNombre();                 // buscamos en la base de datos el nombre
            }                                                   // si nos envían los dos no hacemos nada

            if(lecRepo.existsByNombreAndApellido(auxNombre, auxApellido)){      // si ya existe un registro con el nombre y apellido
                Lector auxiliar = lecRepo.findByNombreAndApellido(auxNombre, auxApellido);      // lo buscamos en la base de datos y comparamos si es el mismo
                if(auxiliar == lector){     // si es el mismo guardamos los cambios
                    lector.setNombre(auxNombre);
                    lector.setApellido(auxApellido);
                }else{      // si no es el mismo es por que existe otro registro distinto al que estamos actualizando
                    throw new ElementoRepetidoException("Nombre, Apellido", "Existe ya un registro de este lector");
                }
            }else{          // si no hay ningun registro guardamos los cambios
                lector.setNombre(auxNombre);
                lector.setApellido(auxApellido);
            }
        }

        if(lectorDto.getNumTelefono() != null){
            lector.setNumTelefono(lectorDto.getNumTelefono());
        }

        if(lectorDto.getIdInstitucion() != null){
            lector.setInstitucion(inserv.obtenerUno(lectorDto.getIdInstitucion()));            
        }
    }





    private Lector convertirALector(DtoLectorIngreso l1){       //convertir a partir de un dto de ingreso de lector a lector 
        Lector lec = new Lector();
        lec.setNombre(l1.getNombre());
        lec.setApellido(l1.getApellido());
        lec.setNumTelefono(l1.getTelefono());
        System.out.println("Id institucion" + l1.getIdInstitucion()+"\n");
        lec.setInstitucion((l1.getIdInstitucion() != null) ?         // si nos llega una institución la buscamos y setemos.
                            inserv.obtenerUno(l1.getIdInstitucion()) : null);
        
        return lec;
    }
    
    private List<DtoLectorMostrar> convertirADtoLectorMostrar(List<Lector> libros){
        List<DtoLectorMostrar> libsF = libros.stream().map(DtoLectorMostrar::new).collect(Collectors.toList());

        return libsF;
    }

    @SuppressWarnings("null") // indica al compilador que ignore las advertencias de este método
    @Override                                  // se usará en otras clases que requiera un Lector
    public Lector Retorna1Lector(Long id) {
        return lecRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Lector no encontrado."));
    }
}

