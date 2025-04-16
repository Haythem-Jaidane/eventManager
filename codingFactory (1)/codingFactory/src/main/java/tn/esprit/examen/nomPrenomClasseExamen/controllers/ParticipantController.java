package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Client;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventParticipant;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Participant;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServices;
import tn.esprit.examen.nomPrenomClasseExamen.services.ParticipantService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("examen")
@RestController
public class ParticipantController {
    private final ParticipantService participantService;
    @PostMapping("/participate/{eventId}")
    public ResponseEntity<?> participate(
            @PathVariable Long eventId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestBody(required = false) List<String> answers) {

        if (answers == null || answers.isEmpty()) {
            // Si aucune réponse n'est fournie, renvoyer les questions de l'événement
            List<String> questions = participantService.getQuestionsForEvent(eventId);
            if (questions.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Aucune question trouvée pour cet événement !");
            }
            return ResponseEntity.ok(questions); // On retourne les questions
        }

        // Sinon, on procède à l'inscription
        try {
            participantService.participateIntoEvent(eventId, name, email, answers);
            return ResponseEntity.ok("✅ Réservation réussie !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Erreur : " + e.getMessage());
        }
    }
    // Route pour récupérer les questions d'un événement donné
    @GetMapping("/questions/{eventId}")
    public ResponseEntity<List<String>> getQuestions(@PathVariable Long eventId) {
        List<String> questions = participantService.getQuestionsForEvent(eventId);

        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourner 404 si pas de questions
        }
        return ResponseEntity.ok(questions); // Retourner les questions
    }
    @GetMapping("/event/{eventId}")
    public List<EventParticipant> getParticipantsByEvent(@PathVariable Long eventId) {
        List<EventParticipant> eventParticipants = participantService.getParticipantsByEvent(eventId);
        return eventParticipants ;
    }

    @GetMapping("/event/{eventId}/approved")
    public List<EventParticipant> getParticipantsByEventAndAprroved(@PathVariable Long eventId) {
        List<EventParticipant> eventParticipants = participantService.getParticipantsByEventAndApproved(eventId);
        return eventParticipants ;
    }

    @PostMapping("/{participantId}/score")
    public ResponseEntity<?> saveParticipantScore(
            @PathVariable Long participantId,
            @RequestBody Map<String, Object> body) {

        Number scoreNumber = (Number) body.get("averageScore"); // Récupère un Number
        Double averageScore = scoreNumber != null ? scoreNumber.doubleValue() : null;

        if (averageScore == null) {
            return ResponseEntity.badRequest().body("Invalid average score");
        }

        boolean success = participantService.updateParticipantScore(participantId, averageScore);
        return success ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/{participantId}/average-score")
    public ResponseEntity<Double> getScores(@PathVariable Long participantId) {
        double scores = participantService.getScoresForParticipant(participantId);  // Récupère les anciennes notes
        return ResponseEntity.ok(scores);
    }

    @PutMapping("/{participantId}/affect/{teamId}")
    public ResponseEntity<?> affectToTeam(@PathVariable Long participantId, @PathVariable Long teamId) {
        boolean affected = participantService.affectToTeam(participantId,teamId);
        return affected ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
