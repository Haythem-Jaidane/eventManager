package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByEmail(String email);
    //Double findAverageScoreById(Long participantId);
    @Query("SELECT p.averageScore FROM Participant p WHERE p.id = :participantId")
    Double findAverageScoreById(@Param("participantId") Long participantId);

    // Récupérer les participants d'un événement par eventId
    @Query("SELECT p FROM Participant p JOIN p.events e WHERE e.id = :eventId")
    List<Participant> findParticipantsByEventId(@Param("eventId") Long eventId);
}