package tn.esprit.examen.nomPrenomClasseExamen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class EventParticipant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private EventStatus status = EventStatus.PENDING;

  @ManyToOne
  @JoinColumn(name = "participant_id")
  private Participant participant;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;


  @ManyToOne
  @JoinColumn(name = "event_id")
  @JsonIgnoreProperties("participant")
  private Event event;

  @ElementCollection
  private List<String> answers; // Stocke les réponses aux questions
}
