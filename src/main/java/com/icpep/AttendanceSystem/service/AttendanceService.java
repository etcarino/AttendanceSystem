package com.icpep.AttendanceSystem.service;

import com.icpep.AttendanceSystem.model.Attendance;
import com.icpep.AttendanceSystem.model.Event;
import com.icpep.AttendanceSystem.model.Student;
import com.icpep.AttendanceSystem.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    //show all items
    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = attendanceRepository.findAllByOrderByAttendanceIdDesc();
        return attendanceList;
    }

    // In AttendanceService.java
    public List<Attendance> getFilteredAttendances(String yearLevel, String section, String course, String eventName) {
        // Fetch all attendances
        List<Attendance> attendances = attendanceRepository.findAll();

        // Filter by year level if provided
        if (yearLevel != null && !yearLevel.isEmpty()) {
            attendances = attendances.stream()
                    .filter(a -> a.getStudent().getYearLevel().equalsIgnoreCase(yearLevel))
                    .collect(Collectors.toList());
        }

        // Filter by section if provided
        if (section != null && !section.isEmpty()) {
            attendances = attendances.stream()
                    .filter(a -> a.getStudent().getSection().equalsIgnoreCase(section))
                    .collect(Collectors.toList());
        }

        // Filter by course if provided
        if (course != null && !course.isEmpty()) {
            attendances = attendances.stream()
                    .filter(a -> a.getStudent().getCourse().equalsIgnoreCase(course))
                    .collect(Collectors.toList());
        }

        // Filter by event name if provided
        if (eventName != null && !eventName.isEmpty()) {
            attendances = attendances.stream()
                    .filter(a -> a.getEvent().getName().equalsIgnoreCase(eventName))
                    .collect(Collectors.toList());
        }

        return attendances;
    }


    //save method
    public void saveAttendance(Attendance attendance){
        attendanceRepository.save(attendance);
    }

    public Optional<Attendance> findByStudentAndEvent(Student student, Event event) {
        return attendanceRepository.findByStudentAndEvent(student, event);
    }


}
