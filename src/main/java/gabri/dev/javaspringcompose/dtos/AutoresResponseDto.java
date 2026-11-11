package gabri.dev.javaspringcompose.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto de salida con los datos de un autor.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoresResponseDto {
    /**
     * id del autor.
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
