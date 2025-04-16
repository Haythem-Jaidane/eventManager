package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.*;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.EventParticipantRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.EventRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.ParticipantRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.TeamRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;
    private final EventParticipantRepository eventParticipantRepository;
    private final TeamRepository teamRepository;


    public void participateIntoEvent(Long idEvent, String nom, String email, List<String> reponses) {
        // 1. Vérifier si l'événement existe
        Event event = eventRepository.findById(idEvent)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé !"));

        // 2. Récupérer le formulaire de questions
        QuestionForm questionForm = event.getQuestionForm();
        if (questionForm == null || questionForm.getQuestions().isEmpty()) {
            throw new RuntimeException("Aucune question trouvée pour cet événement.");
        }

        // 3. Vérifier que toutes les questions ont une réponse
        List<String> questions = questionForm.getQuestions();
        if (reponses.size() != questions.size()) {
            throw new RuntimeException("Le nombre de réponses ne correspond pas au nombre de questions !");
        }

        // 4. Vérifier si le participant existe déjà, sinon le créer
        Participant participant = participantRepository.findByEmail(email)
                .orElseGet(() -> {
                    Participant newParticipant = new Participant();
                    newParticipant.setName(nom);
                    newParticipant.setEmail(email);
                    return participantRepository.save(newParticipant);
                });

        // 5. Vérifier s'il a déjà participé à cet événement
        boolean alreadyParticipated = eventParticipantRepository.existsByParticipantAndEvent(participant, event);
        if (alreadyParticipated) {
            throw new RuntimeException("Le participant a déjà réservé cet événement.");
        }

        // 6. Créer un EventParticipant
        EventParticipant eventParticipant = new EventParticipant();
        eventParticipant.setParticipant(participant);
        eventParticipant.setEvent(event);
        eventParticipant.setAnswers(reponses);

        // 7. Sauvegarder en base
        eventParticipantRepository.save(eventParticipant);

        System.out.println("✅ Réservation réussie pour " + nom + " à l'événement " + event.getTitle());
    }
    public List<String> getQuestionsForEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("❌ Événement introuvable !"));
        if (event.getQuestionForm() != null) {
            return event.getQuestionForm().getQuestions();
        }
        return Collections.emptyList(); // Retourne une liste vide si pas de questions
    }
    // Exemple dans un service ou un contrôleur backend
    public List<EventParticipant> getParticipantsByEvent(Long eventId) {
        List<EventParticipant> participants = eventParticipantRepository.findByEvent_Id(eventId);

        // Assure-toi que les informations du participant sont chargées
        for (EventParticipant eventParticipant : participants) {
            // Si nécessaire, tu peux initialiser les informations du participant
            // Assure-toi que l'objet Participant contient bien `name` et `email`
            eventParticipant.getParticipant().getName();
            eventParticipant.getParticipant().getEmail();
            eventParticipant.getParticipant().getAverageScore();
        }

        return participants;
    }

    public List<EventParticipant> getParticipantsByEventAndApproved(Long eventId) {
        List<EventParticipant> participants = eventParticipantRepository.findByEvent_IdAndStatus(eventId);

        System.out.println(participants);
        // Ensure participant information is loaded
        for (EventParticipant eventParticipant : participants) {
            if (eventParticipant.getParticipant() != null) {
                // Access participant information safely
                String name = eventParticipant.getParticipant().getName();
                String email = eventParticipant.getParticipant().getEmail();
                Double averageScore = eventParticipant.getParticipant().getAverageScore();

                // Log participant details if needed
            } else {

            }
        }

        return participants;
    }
    // Mettre à jour la moyenne des notes dans la base de données
    public boolean updateParticipantScore(Long participantId, Double averageScore) {
        Optional<Participant> participant = participantRepository.findById(participantId);
        if (participant.isPresent()) {
            participant.get().setAverageScore(averageScore); // Mettre à jour la moyenne des notes
            participantRepository.save(participant.get());
            return true;
        }
        return false;
    }

    public boolean updateParticipantStatus(Long participantId, EventStatus status) {
        Optional<EventParticipant> participant = eventParticipantRepository.findById(participantId);
        if (participant.isPresent()) {
            participant.get().setStatus(status); // Mettre à jour la moyenne des notes
            eventParticipantRepository.save(participant.get());
            return true;
        }
        return false;
    }
    public double getScoresForParticipant(Long id){
        return participantRepository.findAverageScoreById(id);
    }
    public List<Participant> getParticipantsByEventId(Long eventId) {
        return participantRepository.findParticipantsByEventId(eventId);
    }

    public Boolean affectToTeam(Long participantId, Long teamId) {
        Optional<EventParticipant> participant = eventParticipantRepository.findById(participantId);
        Optional<Team> team = teamRepository.findById(teamId);
        if (participant.isPresent()) {
            participant.get().setTeam(team.get()); // Mettre à jour la moyenne des notes
            eventParticipantRepository.save(participant.get());
            return true;
        }
        return false;
    }
}
