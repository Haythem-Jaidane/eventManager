package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Checkpoint;
import tn.esprit.examen.nomPrenomClasseExamen.services.ICheckPointService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Checkpoints")
@AllArgsConstructor
public class CheckPointController {

    private ICheckPointService checkPointService;

    private RestTemplate restTemplate;

    @GetMapping("/ask")
    public String ask(@RequestParam String prompt) {
        String ollamaUrl = "http://localhost:11434/api/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama2"); // or "llama2" or your preferred model
        request.put("prompt", prompt);
        request.put("stream", false);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(ollamaUrl, entity, Map.class);

        return response.getBody().get("response").toString();
    }

    // ✅ GET checkpoints by Event ID
    @GetMapping("/{eventId}")
    public ResponseEntity<List<Checkpoint>> getCheckPointsByEventId(@PathVariable Long eventId) {
        List<Checkpoint> checkpoints = checkPointService.getCheckPointsByEventId(eventId);
        return ResponseEntity.ok(checkpoints);
    }

    // ✅ CREATE a checkpoint
    @PostMapping("/add")
    public ResponseEntity<Checkpoint> createCheckPoint(@RequestBody Checkpoint checkpoint) {
        Checkpoint response = checkPointService.addCheckPoint(checkpoint);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ✅ UPDATE a checkpoint
    @PutMapping("/{id}")
    public ResponseEntity<Checkpoint> updateCheckPoint(@PathVariable Long id, @RequestBody Checkpoint updatedCheckpoint) {
        Checkpoint checkpoint = checkPointService.updateCheckPoint(id, updatedCheckpoint);
        return ResponseEntity.ok(checkpoint);
    }

    // ✅ DELETE a checkpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckPoint(@PathVariable Long id) {
        checkPointService.deleteCheckPoint(id);
        return ResponseEntity.noContent().build();
    }


}
