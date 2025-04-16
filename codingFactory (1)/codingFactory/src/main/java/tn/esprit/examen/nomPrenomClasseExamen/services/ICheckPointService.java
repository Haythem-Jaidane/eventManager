package tn.esprit.examen.nomPrenomClasseExamen.services;


import tn.esprit.examen.nomPrenomClasseExamen.entities.Checkpoint;

import java.util.List;

public interface ICheckPointService {

    Checkpoint addCheckPoint(Checkpoint checkpoint);

    List<Checkpoint> getCheckPointsByEventId(Long eventId);
    Checkpoint getCheckPointById(Long id);
    Checkpoint updateCheckPoint(Long id, Checkpoint checkpoint);
    void deleteCheckPoint(Long id);


}
