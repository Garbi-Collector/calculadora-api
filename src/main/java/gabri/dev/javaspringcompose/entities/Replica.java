package gabri.dev.javaspringcompose.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Una réplica es un ejemplar físico de un libro (un libro tiene muchas réplicas)
@Entity
@Table(name = "replicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Replica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_stock", unique = true)
    private Long numeroStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libros;

    // Sin cascade: el historial de préstamos no debe borrarse con la réplica
    @OneToMany(mappedBy = "replicas", fetch = FetchType.LAZY)
    private List<DetallePrestamo> detallePrestamo = new ArrayList<>();
}