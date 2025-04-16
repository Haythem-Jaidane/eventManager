package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    List<Team> findAllByEvent(Event event);
}
