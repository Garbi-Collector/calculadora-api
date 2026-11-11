package gabri.dev.javaspringcompose.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_prestamo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestamo_id", nullable = false)
    private Prestamos prestamo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replica_id", nullable = false)
    private Replicas replicas;
}