package com.icpep.AttendanceSystem.controller;

import com.icpep.AttendanceSystem.model.Attendance;
import com.icpep.AttendanceSystem.model.AttendanceDto;
import com.icpep.AttendanceSystem.model.Event;
import com.icpep.AttendanceSystem.model.Student;
import com.icpep.AttendanceSystem.service.AttendanceService;
import com.icpep.AttendanceSystem.service.EventService;
import com.icpep.AttendanceSystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EventService eventService;
    private final StudentService studentService;

    public AttendanceController(AttendanceService attendanceService, EventService eventService, StudentService studentService){
        this.attendanceService = attendanceService;
        this.eventService = eventService;
        this.studentService = studentService;
    }

    @GetMapping({"", "/"})
    public String viewClientPage(Model model) {
        AttendanceDto attendanceDto = new AttendanceDto();
        model.addAttribute("attendanceDto", attendanceDto);
        model.addAttribute("attendanceGet", attendanceService.getAllAttendance());

        Optional<Event> selectedEvent = eventService.getSelectedEvent();
        selectedEvent.ifPresent(event -> model.addAttribute("selectedEvent", event));

        return "attendance/index";
    }


    //save attendance
    @PostMapping("/saveAttendance")
    public String saveAttendance(@ModelAttribute AttendanceDto attendanceDto, Model model) {
        try {
            // Sanitize and parse the student ID
            String sanitizedId = attendanceDto.getStudentId().replaceAll("-", "");
            int sanitizedStudentId = Integer.parseInt(sanitizedId);

            // Fetch Student and Event entities
            Optional<Student> studentOptional = studentService.getStudentId(sanitizedStudentId);
            Optional<Event> eventOptional = eventService.getEventId(attendanceDto.getEventId());

            if (studentOptional.isPresent() && eventOptional.isPresent()) {
                Student student = studentOptional.get();
                Event event = eventOptional.get();

                // Set the current time based on the clock state
                LocalTime currentTime = LocalTime.now();
                attendanceDto.setCurrentTimeBasedOnClockState(currentTime);

                // Check if the Attendance record already exists
                Optional<Attendance> existingAttendanceOptional = attendanceService.findByStudentAndEvent(student, event);

                Attendance attendance;
                if (existingAttendanceOptional.isPresent()) {
                    // Retrieve existing Attendance record
                    attendance = existingAttendanceOptional.get();

                    // Update relevant time field based on the clockState
                    switch (attendanceDto.getClockState()) {
                        case "CLOCK_IN":
                            attendance.settIn(attendanceDto.gettIn());
                            break;
                        case "CLOCK_OUT":
                            attendance.settOut(attendanceDto.gettOut());
                            break;
                        case "CLOCK_IN_AM":
                            attendance.settInAm(attendanceDto.gettInAm());
                            break;
                        case "CLOCK_OUT_AM":
                            attendance.settOutAm(attendanceDto.gettOutAm());
                            break;
                        case "CLOCK_IN_PM":
                            attendance.settInPm(attendanceDto.gettInPm());
                            break;
                        case "CLOCK_OUT_PM":
                            attendance.settOutPm(attendanceDto.gettOutPm());
                            break;
                    }

                } else {
                    // Create a new Attendance record
                    attendance = new Attendance();
                    attendance.setStudent(student);
                    attendance.setEvent(event);

                    // Set the time fields based on the clockState
                    switch (attendanceDto.getClockState()) {
                        case "CLOCK_IN":
                            attendance.settIn(attendanceDto.gettIn());
                            break;
                        case "CLOCK_OUT":
                            attendance.settOut(attendanceDto.gettOut());
                            break;
                        case "CLOCK_IN_AM":
                            attendance.settInAm(attendanceDto.gettInAm());
                            break;
                        case "CLOCK_OUT_AM":
                            attendance.settOutAm(attendanceDto.gettOutAm());
                            break;
                        case "CLOCK_IN_PM":
                            attendance.settInPm(attendanceDto.gettInPm());
                            break;
                        case "CLOCK_OUT_PM":
                            attendance.settOutPm(attendanceDto.gettOutPm());
                            break;
                    }
                }

                // Save the Attendance entity (insert or update)
                attendanceService.saveAttendance(attendance);

                // Add success message and student name to the model
                model.addAttribute("successMessage", "Attendance recorded successfully for student: " + student.getName());

            } else {
                // Handle case where student or event is not found
                model.addAttribute("errorMessage", "Student or Event not found.");
            }

            // Add attributes for index.html
            model.addAttribute("attendanceDto", new AttendanceDto()); // Reset the form object
            model.addAttribute("attendanceGet", attendanceService.getAllAttendance());
            Optional<Event> selectedEventOptional = eventService.getSelectedEvent();
            selectedEventOptional.ifPresent(event -> model.addAttribute("selectedEvent", event));

            return "attendance/index";

        } catch (NumberFormatException e) {
            // Handle invalid student ID format
            model.addAttribute("errorMessage", "Invalid student ID format.");

            // Add attributes for index.html
            model.addAttribute("attendanceDto", new AttendanceDto()); // Reset the form object
            model.addAttribute("attendanceGet", attendanceService.getAllAttendance());
            Optional<Event> selectedEventOptional = eventService.getSelectedEvent();
            selectedEventOptional.ifPresent(event -> model.addAttribute("selectedEvent", event));

            return "attendance/index";
        }
    }



}
