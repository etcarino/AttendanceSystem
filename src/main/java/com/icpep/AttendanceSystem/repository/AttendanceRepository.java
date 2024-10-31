package com.icpep.AttendanceSystem.repository;

import com.icpep.AttendanceSystem.model.Attendance;
import com.icpep.AttendanceSystem.model.Event;
import com.icpep.AttendanceSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findByStudentAndEvent(Student student, Event event);

    List<Attendance> findAllByOrderByAttendanceIdDesc();

    List<Attendance> findByStudentYearLevel(String yearLevel);

    List<Attendance> findByStudentSection(String section);

    List<Attendance> findByStudentCourse(String course);

    List<Attendance> findByStudentYearLevelAndStudentSection(String yearLevel, String section);

    List<Attendance> findByStudentYearLevelAndStudentSectionAndStudentCourseAndEventName(String yearLevel, String section, String course, String eventName);

    List<Attendance> findByStudentYearLevelAndStudentSectionAndStudentCourse(String yearLevel, String section, String course);

    List<Attendance> findByStudentYearLevelAndStudentCourse(String yearLevel, String course);

    List<Attendance> findByStudentSectionAndStudentCourse(String section, String course);

    List<Attendance> findByEventName(String eventName);


}
