package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Event;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventStatus;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
    @Transactional

    public Event createEvent(Event event) {
        event.setStatus(EventStatus.PENDING);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setTitle(eventDetails.getTitle());
                    event.setDescription(eventDetails.getDescription());
                    event.setEventDate(eventDetails.getEventDate());
                    event.setLocation(eventDetails.getLocation());
                    event.setCapacity(eventDetails.getCapacity());
                    event.setObjectives(eventDetails.getObjectives());
                    event.setEventType(eventDetails.getEventType());
                    event.setPrizes(eventDetails.getPrizes());
                //    event.setStatus(eventDetails.getStatus());
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
    //  Only Admins can update the event status
    public Event updateEventStatus(Long id, EventStatus status) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setStatus(status);
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
