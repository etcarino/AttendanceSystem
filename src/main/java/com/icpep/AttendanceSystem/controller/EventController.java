package com.icpep.AttendanceSystem.controller;

import com.icpep.AttendanceSystem.model.Event;
import com.icpep.AttendanceSystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    //constructor injection
    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    //Get all events method
    @GetMapping({"", "/"})
    public String viewEventPage(Model model){
        model.addAttribute("events", eventService.getAllEvents());
        return "/events/index";
    }

    //got to add event form page using Event Class as the blueprint
    @GetMapping("/addEventForm")
    public String addEventForm(Model model){
        Event event = new Event();
        model.addAttribute("event", event);
        return "events/add_event";
    }

    //save event information
    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute("event") Event event){
        eventService.saveEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable(value = "id") int id){
        this.eventService.deleteEventById(id);
        return "redirect:/events";
    }

    @GetMapping("/selectedEvent")
    public String getSelectedEvent(Model model) {
        Optional<Event> event = eventService.getSelectedEvent();
        event.ifPresent(e -> model.addAttribute("selectedEvent", e));
        return "attendance/index";
    }
}
