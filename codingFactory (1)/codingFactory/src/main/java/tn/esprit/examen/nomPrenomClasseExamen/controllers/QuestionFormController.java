package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.QuestionForm;
import tn.esprit.examen.nomPrenomClasseExamen.services.QuestionFormService;

import java.util.List;

@RestController
@RequestMapping("/api/question-forms")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class QuestionFormController {

    private final QuestionFormService questionFormService;

    @Operation(summary = "Add questions to an event")
    @PostMapping("/events/{eventId}")
    public ResponseEntity<QuestionForm> createQuestionForm(
            @PathVariable Long eventId,
            @RequestBody List<String> questions) {
        return ResponseEntity.ok(questionFormService.createQuestionForm(questions, eventId));
    }

    @Operation(summary = "Get all question forms")
    @GetMapping
    public ResponseEntity<List<QuestionForm>> getAllQuestionForms() {
        return ResponseEntity.ok(questionFormService.getAllQuestionForms());
    }

    @Operation(summary = "Get question form by ID")
    @GetMapping("/{id}")
    public ResponseEntity<QuestionForm> getQuestionFormById(@PathVariable Long id) {
        return ResponseEntity.ok(questionFormService.getQuestionFormById(id));
    }

    @Operation(summary = "Get question form by event ID")
    @GetMapping("/events/{eventId}")
    public ResponseEntity<QuestionForm> getQuestionFormByEventId(@PathVariable Long eventId) {
        return ResponseEntity.ok(questionFormService.getQuestionFormByEventId(eventId));
    }

    @Operation(summary = "Update question form")
    @PutMapping("/{id}")
    public ResponseEntity<QuestionForm> updateQuestionForm(
            @PathVariable Long id,
            @RequestBody List<String> questions) {
        return ResponseEntity.ok(questionFormService.updateQuestionForm(id, questions));
    }

    @Operation(summary = "Delete question form")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionForm(@PathVariable Long id) {
        questionFormService.deleteQuestionForm(id);
        return ResponseEntity.ok().build();
    }
}