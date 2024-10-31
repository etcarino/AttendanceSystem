package com.icpep.AttendanceSystem.repository;

import com.icpep.AttendanceSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByYearLevel(String yearLevel);

    List<Student> findBySection(String section);

    List<Student> findByCourse(String course);

    List<Student> findByYearLevelAndSection(String yearLevel, String section);

    List<Student> findByYearLevelAndCourse(String yearLevel, String course);

    List<Student> findBySectionAndCourse(String section, String course);

    List<Student> findByYearLevelAndSectionAndCourse(String yearLevel, String section, String course);

    @Query("SELECT s FROM Student s WHERE s.studentId NOT IN (SELECT a.student.studentId FROM Attendance a)")
    List<Student> findStudentsWithoutAttendance();

}
