package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.entities.Evaluation;

import java.util.List;

public interface IEvaluationService {
    void evaluateCheckpoints(List<Evaluation> evaluations);
}
