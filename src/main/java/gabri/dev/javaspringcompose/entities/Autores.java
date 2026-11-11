package gabri.dev.javaspringcompose.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String nacionalidad;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private List<Libros> libros = new ArrayList<>();
}