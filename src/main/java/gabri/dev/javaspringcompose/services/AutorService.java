package gabri.dev.javaspringcompose.services;

import gabri.dev.javaspringcompose.models.AutorModel;

import java.util.List;

/**
 * Servicio de negocio para la gestión de autores.
 */
public interface AutorService {

    /**
     * Obtiene todos los autores.
     * @return la lista de autores.
     */
    List<AutorModel> findAll();
    
    AutorModel findById(Long id);

    AutorModel create(AutorModel autoresModel);

    AutorModel update(AutorModel autoresModel);

    boolean deleteById(Long id);


}