package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany
    @JoinColumn(name = "participants")
    private List<EventParticipant> participant;
}
