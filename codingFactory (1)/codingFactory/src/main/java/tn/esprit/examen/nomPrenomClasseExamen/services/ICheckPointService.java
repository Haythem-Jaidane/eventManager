package tn.esprit.examen.nomPrenomClasseExamen.services;


import tn.esprit.examen.nomPrenomClasseExamen.entities.Checkpoint;

import java.util.List;

public interface ICheckPointService {

    Checkpoint addCheckPoint(Checkpoint checkpoint);

    List<Checkpoint> getCheckPointsByEvent();

    Boolean deleteCheckPoint(Checkpoint checkpoint);

    Boolean updateCheckPoint(Checkpoint checkpoint);


}
