package com.icpep.AttendanceSystem.service;


import com.icpep.AttendanceSystem.model.Event;
import com.icpep.AttendanceSystem.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    //Show ALL items
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    //Save Method
    public void  saveEvent(Event event){
        eventRepository.save(event);
    }

    //Get id for edit
    public Optional<Event> getEventId(int id){
        return eventRepository.findById(id);
    }


    //Delete Method
    public void deleteEventById(int id){
        eventRepository.deleteById(id);
    }

    @Transactional
    public void updateSelectedEvent(int selectedEventId) {
        List<Event> events = eventRepository.findAll();
        for (Event event : events) {
            event.setEventSelected(false);
        }
        eventRepository.saveAll(events);

        Event selectedEvent = eventRepository.findById(selectedEventId).orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + selectedEventId));
        selectedEvent.setEventSelected(true);
        eventRepository.save(selectedEvent);
    }

    //get event where selected is true
    public Optional<Event> getSelectedEvent() {
        return eventRepository.findByEventSelectedTrue();
    }

    @Transactional
    public void updateClockState(int eventId, String clockState) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setClockState(clockState);
        eventRepository.save(event);
    }

}
