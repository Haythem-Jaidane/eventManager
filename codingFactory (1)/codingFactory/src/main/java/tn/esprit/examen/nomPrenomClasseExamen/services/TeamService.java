package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventParticipant;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Team;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService implements ITeamService{

    private TeamRepository teamRepository;

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getTeamsByEvent(Event event) {
        return teamRepository.findAllByEvent(event);
    }

    @Override
    public List<EventParticipant> getParticipants(Event event) {
        return teamRepository.findAllByEvent(event).stream()
                .flatMap(team -> team.getParticipant().stream())
                .collect(Collectors.toList());
    }
}
