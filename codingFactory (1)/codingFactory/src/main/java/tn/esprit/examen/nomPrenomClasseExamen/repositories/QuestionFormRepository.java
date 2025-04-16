package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.nomPrenomClasseExamen.entities.QuestionForm;

import java.util.Optional;

@Repository
public interface QuestionFormRepository extends JpaRepository<QuestionForm, Long> {
    QuestionForm findByEvent_Id(Long eventId);
}