package gabri.dev.javaspringcompose.repositories;

import gabri.dev.javaspringcompose.entities.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoresRepository extends JpaRepository<Autores, Long> {
}