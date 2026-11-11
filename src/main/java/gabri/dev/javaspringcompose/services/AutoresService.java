package gabri.dev.javaspringcompose.services;

import gabri.dev.javaspringcompose.models.AutoresModel;

import java.util.List;

/**
 * Servicio de negocio para la gestión de autores.
 */
public interface AutoresService {

    /**
     * Obtiene todos los autores.
     * @return la lista de autores.
     */
    List<AutoresModel> findAll();

    AutoresModel create(AutoresModel autoresModel);

}