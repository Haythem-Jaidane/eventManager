package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventParticipant;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventStatus;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Participant;

import java.util.List;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {
    boolean existsByParticipantAndEvent(Participant participant, Event event);
    List<EventParticipant> findByEvent_Id(Long eventId);
    @Query("SELECT p FROM EventParticipant p where p.event.id=:eventId and p.status='APPROVED'")
    List<EventParticipant> findByEvent_IdAndStatus(@Param("eventId") Long eventId);
}
