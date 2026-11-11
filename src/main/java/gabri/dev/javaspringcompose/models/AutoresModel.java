package gabri.dev.javaspringcompose.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de autor: representación de negocio, sin detalles de persistencia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoresModel {

    /**
     * identificador del autor
     */
    private Long id;

    /**
     * nombre del autor.
     */
    private String nombre;
    /**
     * apellido del autor.
     */
    private String apellido;
    /**
     * nacionalidad del autor.
     */
    private String nacionalidad;
}
