package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Checkpoint;
import tn.esprit.examen.nomPrenomClasseExamen.services.ICheckPointService;

import java.util.ArrayList;
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
    public ResponseEntity<Map<String, Object>> ask(@RequestParam String prompt) {
        String ollamaUrl = "http://localhost:11434/api/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama2");
        request.put("prompt", prompt);
        request.put("stream", false);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(ollamaUrl, entity, Map.class);

        // 🧠 Extract plain text response
        String rawResponse = response.getBody().get("response").toString();

        // ✨ Parse checkpoints from the AI response (you might improve this with real AI parsing)
        List<Map<String, Object>> checkpoints = new ArrayList<>();

        // For demo: Split by lines or pattern (You might want to improve this later with JSON output from LLM)
        String[] lines = rawResponse.split("\\n");
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                Map<String, Object> cp = new HashMap<>();
                cp.put("title", line.length() > 30 ? line.substring(0, 30) + "..." : line); // fake title
                cp.put("description", line);
                cp.put("percentage", 10); // or calculate dynamically
                checkpoints.add(cp);
            }
        }

        // 🌟 Construct response
        Map<String, Object> result = new HashMap<>();
        result.put("summary", "Checkpoints generated from AI prompt");
        result.put("checkpoints", checkpoints);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
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
