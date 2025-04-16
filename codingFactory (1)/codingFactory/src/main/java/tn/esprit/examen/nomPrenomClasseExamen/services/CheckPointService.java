package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Checkpoint;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.CheckpointRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CheckPointService implements ICheckPointService {

    private CheckpointRepository checkpointRepository;

    @Override
    public Checkpoint addCheckPoint(Checkpoint checkpoint) {
        return checkpointRepository.save(checkpoint);
    }

    @Override
    public List<Checkpoint> getCheckPointsByEvent() {
        return checkpointRepository.findAll();
    }

    @Override
    public Boolean deleteCheckPoint(Checkpoint checkpoint) {
        return null;
    }

    @Override
    public Boolean updateCheckPoint(Checkpoint checkpoint) {
        return null;
    }
}
