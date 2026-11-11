package gabri.dev.javaspringcompose.services.impl;

import gabri.dev.javaspringcompose.entities.Autores;
import gabri.dev.javaspringcompose.models.AutoresModel;
import gabri.dev.javaspringcompose.repositories.AutoresRepository;
import gabri.dev.javaspringcompose.services.AutoresService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de autores.
 */
@Service
@Transactional
public class AutoresServiceImpl implements AutoresService {

    /**
     * Repositorio de autores.
     */
    private final AutoresRepository autoresRepository;

    /**
     * ModelMapper para convertir entre entidad y modelo.
     */
    private final ModelMapper modelMapper;

    /**
     * ModelMapper que ignora los campos nulos al actualizar.
     */
    private final ModelMapper mergerMapper;

    /**
     * Constructor del servicio.
     * @param autoresRepository repositorio de autores.
     * @param modelMapper mapper por defecto.
     * @param mergerMapper mapper para actualizaciones parciales.
     */
    public AutoresServiceImpl(AutoresRepository autoresRepository,
                              @Qualifier("modelMapper") ModelMapper modelMapper,
                              @Qualifier("mergerMapper") ModelMapper mergerMapper) {
        this.autoresRepository = autoresRepository;
        this.modelMapper = modelMapper;
        this.mergerMapper = mergerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutoresModel> findAll() {

        //traemos las entidades como lista
        List<Autores> autoresList = autoresRepository.findAll();

        //Crear una lista de autores modelos para guardar y enviar
        List<AutoresModel> autoresmodeloList = new ArrayList<>();

        for(Autores autorEntidad : autoresList){

            AutoresModel autor = new AutoresModel();

            autor.setId(autorEntidad.getId());
            autor.setNombre(autorEntidad.getNombre());
            autor.setApellido(autorEntidad.getApellido());
            autor.setNacionalidad(autorEntidad.getNacionalidad());

            autoresmodeloList.add(autor);
        }
        return autoresmodeloList;
    }

    @Override
    public AutoresModel create(AutoresModel autorModel) {
        Autores autorEntity = new Autores();

        autorEntity.setNombre(autorModel.getNombre());
        autorEntity.setApellido(autorModel.getApellido());
        autorEntity.setNacionalidad(autorModel.getNacionalidad());

        Autores autorSaved = autoresRepository.save(autorEntity);

        AutoresModel autorSavedModel = new AutoresModel();
        autorSavedModel.setId(autorSaved.getId());
        autorSavedModel.setNombre(autorSaved.getNombre());
        autorSavedModel.setApellido(autorSaved.getApellido());
        autorSavedModel.setNacionalidad(autorSaved.getNacionalidad());

        return autorSavedModel;


    }

}
