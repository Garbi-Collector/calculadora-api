package gabri.dev.javaspringcompose.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private Long dni;

    // Guardar siempre hasheada (BCrypt), nunca en texto plano
    @Column
    private String contrasenia;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Prestamo> prestamos = new ArrayList<>();
}