package gabri.dev.javaspringcompose.services.impl;

import gabri.dev.javaspringcompose.entities.Autor;
import gabri.dev.javaspringcompose.models.AutorModel;
import gabri.dev.javaspringcompose.repositories.AutorRepository;
import gabri.dev.javaspringcompose.services.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de autores.
 */
@Service
@Transactional
public class AutorServiceImpl implements AutorService {

    /**
     * Repositorio de autores.
     */
    private final AutorRepository autoresRepository;

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
    public AutorServiceImpl(AutorRepository autoresRepository,
                            @Qualifier("modelMapper") ModelMapper modelMapper,
                            @Qualifier("mergerMapper") ModelMapper mergerMapper) {
        this.autoresRepository = autoresRepository;
        this.modelMapper = modelMapper;
        this.mergerMapper = mergerMapper;
    }


    /**
     * metodo para mostrar a todos los autores
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<AutorModel> findAll() {

        //traemos las entidades como lista
        List<Autor> autoresList = autoresRepository.findAll();

        //Crear una lista de autores modelos para guardar y enviar
        List<AutorModel> autoresmodeloList = new ArrayList<>();

        for(Autor autorEntidad : autoresList){
            AutorModel autor = entityToModel(autorEntidad);
            autoresmodeloList.add(autor);
        }
        return autoresmodeloList;
    }

    /**
     * metodo para mostar al autor con el id buscado
     * @param id
     * @return
     */
    @Override
    public AutorModel findById(Long id) {
        Autor autor = autoresRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Entidad no encontrada con ID: "+id));

        return entityToModel(autor);
    }

    /**
     * metodo para crear una entidad autor
     * @param autorModel
     * @return
     */
    @Override
    public AutorModel create(AutorModel autorModel) {

        Autor autorEntity = modelToEntity(autorModel);

        Autor autorSaved = autoresRepository.save(autorEntity);

        AutorModel autorSavedModel = entityToModel(autorSaved);

        return autorSavedModel;
    }

    /**
     * metodo para actualizar una entidad autor
     * @param autoresModel
     * @return
     */
    @Override
    public AutorModel update(AutorModel autoresModel) {
        return null;
    }

    /**
     * metodo para eliminar una entidad autor
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    /**
     * METODOS AUXILIARES
     */

    private AutorModel entityToModel(Autor autorEntidad) {
        AutorModel autorModel = new AutorModel();
        autorModel.setId(autorEntidad.getId());
        autorModel.setNombre(autorEntidad.getNombre());
        autorModel.setApellido(autorEntidad.getApellido());
        autorModel.setNacionalidad(autorEntidad.getNacionalidad());

        return autorModel;
    }
    private Autor modelToEntity(AutorModel autorModel) {
        Autor autorEntity = new Autor();
        autorEntity.setId(autorModel.getId());
        autorEntity.setNombre(autorModel.getNombre());
        autorEntity.setApellido(autorModel.getApellido());
        autorEntity.setNacionalidad(autorModel.getNacionalidad());

        return autorEntity;
    }




}
