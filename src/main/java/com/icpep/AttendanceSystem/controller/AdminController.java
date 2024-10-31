package com.icpep.AttendanceSystem.controller;

import com.icpep.AttendanceSystem.model.Attendance;
import com.icpep.AttendanceSystem.model.Event;
import com.icpep.AttendanceSystem.model.Student;
import com.icpep.AttendanceSystem.model.StudentUniqueValues;
import com.icpep.AttendanceSystem.service.AttendanceService;
import com.icpep.AttendanceSystem.service.EventService;
import com.icpep.AttendanceSystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class AdminController {

    private final EventService eventService;
    private final AttendanceService attendanceService;
    private final StudentService studentService;

    public AdminController(EventService eventService, AttendanceService attendanceService, StudentService studentService){
        this.eventService = eventService;
        this.attendanceService = attendanceService;
        this.studentService = studentService;
    }

    // In AdminController.java
    @GetMapping({"", "/"})
    public String showEventNames(@RequestParam(required = false) String yearLevel,
                                 @RequestParam(required = false) String section,
                                 @RequestParam(required = false) String course,
                                 @RequestParam(required = false) String eventName,
                                 Model model) {
        List<Event> events = eventService.getAllEvents();

        // Find the selected event
        Event selectedEvent = events.stream()
                .filter(Event::isEventSelected)
                .findFirst()
                .orElse(null);

        // Find the event based on the eventName filter
        Event filteredEvent = null;
        if (eventName != null && !eventName.isEmpty()) {
            filteredEvent = events.stream()
                    .filter(event -> event.getName().equalsIgnoreCase(eventName))
                    .findFirst()
                    .orElse(null);
        }

        // Ensure the service method considers all filters
        List<Attendance> attendances = attendanceService.getFilteredAttendances(yearLevel, section, course, eventName);
        List<Student> students = studentService.getAllStudents();
        StudentUniqueValues studentUniqueValues = studentService.getUniqueValues();

        model.addAttribute("uniqueValues", studentUniqueValues);
        model.addAttribute("attendances", attendances);
        model.addAttribute("events", events);
        model.addAttribute("students", students);
        model.addAttribute("selectedEvent", selectedEvent);
        model.addAttribute("eventName", eventName);
        model.addAttribute("yearLevel", yearLevel);
        model.addAttribute("section", section);
        model.addAttribute("course", course);


        if (filteredEvent != null) {
            model.addAttribute("filteredEventIsWholeDay", filteredEvent.isWholeDay());
        }

        return "index";
    }


    @GetMapping("/savePage")
    public String savePage(@RequestParam(required = false) String yearLevel,
                           @RequestParam(required = false) String section,
                           @RequestParam(required = false) String course,
                           @RequestParam(required = false) String eventName,
                           Model model) {
        List<Event> events = eventService.getAllEvents();
        List<Student> students = studentService.getAllStudents();
        List<Attendance> attendances = attendanceService.getFilteredAttendances(yearLevel, section, course, eventName);

        Event filteredEvent = null;
        if (eventName != null && !eventName.isEmpty()) {
            filteredEvent = events.stream()
                    .filter(event -> event.getName().equalsIgnoreCase(eventName))
                    .findFirst()
                    .orElse(null);
        }

        Set<Integer> attendanceStudentIds = attendances.stream()
                .map(attendance -> attendance.getStudent().getStudentId())
                .collect(Collectors.toSet());

        List<Student> absentStudents = students.stream()
                .filter(student -> !attendanceStudentIds.contains(student.getStudentId()))
                .filter(student -> {
                    // Apply the same filtering logic as in getFilteredAttendances
                    boolean matchesYearLevel = yearLevel == null || yearLevel.isEmpty() || student.getYearLevel().equalsIgnoreCase(yearLevel);
                    boolean matchesSection = section == null || section.isEmpty() || student.getSection().equalsIgnoreCase(section);
                    boolean matchesCourse = course == null || course.isEmpty() || student.getCourse().equalsIgnoreCase(course);
                    return matchesYearLevel && matchesSection && matchesCourse;
                })
                .collect(Collectors.toList());

        model.addAttribute("attendances", attendances);
        model.addAttribute("events", events);
        model.addAttribute("students", students);
        model.addAttribute("yearLevel", yearLevel);
        model.addAttribute("section", section);
        model.addAttribute("course", course);
        model.addAttribute("eventName", eventName);
        model.addAttribute("absentStudents", absentStudents);  // Add absent students to model

        if (filteredEvent != null) {
            model.addAttribute("isWholeDay", filteredEvent.isWholeDay());
        }

        return "savePage";
    }




    @PostMapping("/selectedEvent")
    public String selectedEvent(@RequestParam("event") int id){
        eventService.updateSelectedEvent(id);
        return "redirect:/";
    }

    @PostMapping("/updateClockState")
    public String updateClockState(@RequestParam("eventId") int eventId, @RequestParam("clockState") String clockState) {
        eventService.updateClockState(eventId, clockState);
        return "redirect:/";
    }




}
