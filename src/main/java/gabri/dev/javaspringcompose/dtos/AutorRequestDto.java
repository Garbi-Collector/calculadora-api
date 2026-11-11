package gabri.dev.javaspringcompose.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto de entrada para crear o actualizar un autor.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorRequestDto {
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
