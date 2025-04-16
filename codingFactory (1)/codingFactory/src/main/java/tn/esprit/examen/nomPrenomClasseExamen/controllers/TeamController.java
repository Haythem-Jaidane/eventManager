package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventParticipant;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Team;
import tn.esprit.examen.nomPrenomClasseExamen.services.EventService;
import tn.esprit.examen.nomPrenomClasseExamen.services.TeamService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Team")
@AllArgsConstructor
public class TeamController {

    private TeamService teamService;
    private EventService eventService;

    @GetMapping("/{idEvent}")
    public ResponseEntity<List<Team>> getAllCheckPoints(@PathVariable Long idEvent) {
        Optional<Event> event = eventService.getEventById(idEvent);
        return ResponseEntity.ok(teamService.getTeamsByEvent(event.orElse(null)));
    }

    @PostMapping("/add")
    public ResponseEntity<Team> createCheckPoint(@RequestBody Team team) {
        Team response = teamService.createTeam(team);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{idEvent}/teams")
    public ResponseEntity<List<EventParticipant>> getAllParticipant(@PathVariable Long idEvent) {
        Optional<Event> event = eventService.getEventById(idEvent);
        return ResponseEntity.ok(teamService.getParticipants(event.orElse(null)));
    }
}
