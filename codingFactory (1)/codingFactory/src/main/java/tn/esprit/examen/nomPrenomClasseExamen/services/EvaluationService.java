package tn.esprit.examen.nomPrenomClasseExamen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Evaluation;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.EvaluationRepository;

import java.util.List;

@Service
public class EvaluationService implements IEvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public void evaluateCheckpoints(List<Evaluation> evaluations) {
        for (Evaluation eval : evaluations) {
            Evaluation evaluation = new Evaluation();
            evaluation.setCheckpointId(eval.getCheckpointId());
            evaluation.setTeamId(eval.getTeamId());
            evaluation.setScore(eval.getScore());
            evaluationRepository.save(evaluation);
        }
    }
}
