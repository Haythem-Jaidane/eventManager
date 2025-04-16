package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Checkpoint;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;

import java.util.List;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {
    List<Checkpoint> findByEvent(Event event);
}
