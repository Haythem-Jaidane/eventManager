package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventParticipant;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Team;

import java.util.List;

public interface ITeamService {
    Team createTeam(Team team);
    List<Team> getTeamsByEvent(Event event);
    List<EventParticipant> getParticipants(Event event);
}
