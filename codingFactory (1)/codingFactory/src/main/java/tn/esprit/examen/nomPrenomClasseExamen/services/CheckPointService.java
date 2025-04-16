package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Checkpoint;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.CheckpointRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CheckPointService implements ICheckPointService {

    private CheckpointRepository checkpointRepository;
    private EventRepository eventRepository;

    @Override
    public Checkpoint addCheckPoint(Checkpoint checkpoint) {
        return checkpointRepository.save(checkpoint);
    }

    @Override
    public List<Checkpoint> getCheckPointsByEventId(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        return checkpointRepository.findByEvent(event.get());
    }

    @Override
    public Checkpoint getCheckPointById(Long id) {
        return checkpointRepository.findById(id).get();
    }

    @Override
    public Checkpoint updateCheckPoint(Long id, Checkpoint checkpoint) {
        Checkpoint newCheckpoint = checkpointRepository.findById(id).get();
        return checkpointRepository.save(newCheckpoint);
    }

    @Override
    public void deleteCheckPoint(Long id) {
        Checkpoint checkpoint = checkpointRepository.findById(id).get();
        checkpointRepository.delete(checkpoint);
    }

}
