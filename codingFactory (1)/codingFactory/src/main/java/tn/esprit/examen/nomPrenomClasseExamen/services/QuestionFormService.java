package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.entities.QuestionForm;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.EventRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.QuestionFormRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class QuestionFormService {

    private final QuestionFormRepository questionFormRepository;
    private final EventRepository eventRepository;

    public QuestionForm createQuestionForm(List<String> questions, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event with id " + eventId + " not found"));

        // Check if event already has a question form
        if (event.getQuestionForm() != null) {
            throw new RuntimeException("Event already has a question form");
        }

        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(questions);
        questionForm.setEvent(event);
        event.setQuestionForm(questionForm);

        return questionFormRepository.save(questionForm);
    }

    public List<QuestionForm> getAllQuestionForms() {
        return questionFormRepository.findAll();
    }

    public QuestionForm getQuestionFormById(Long id) {
        return questionFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QuestionForm with id " + id + " not found"));
    }

    public QuestionForm getQuestionFormByEventId(Long eventId) {
        return questionFormRepository.findByEvent_Id(eventId);
    }

    public QuestionForm updateQuestionForm(Long id, List<String> questions) {
        QuestionForm existingForm = getQuestionFormById(id);
        existingForm.setQuestions(questions);
        return questionFormRepository.save(existingForm);
    }

    public void deleteQuestionForm(Long id) {
        QuestionForm questionForm = getQuestionFormById(id);
        Event event = questionForm.getEvent();
        event.setQuestionForm(null);
        eventRepository.save(event);
        questionFormRepository.deleteById(id);
    }
}