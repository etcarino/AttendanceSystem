package com.icpep.AttendanceSystem.service;

import com.icpep.AttendanceSystem.model.Student;
import com.icpep.AttendanceSystem.model.StudentUniqueValues;
import com.icpep.AttendanceSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //Show ALL Items
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //Filter Method
    public List<Student> getFilteredStudents(String yearLevel, String section, String course) {
        if ((yearLevel != null && !yearLevel.isEmpty()) &&
                (section != null && !section.isEmpty()) &&
                (course != null && !course.isEmpty())) {
            return studentRepository.findByYearLevelAndSectionAndCourse(yearLevel, section, course);
        } else if ((yearLevel != null && !yearLevel.isEmpty()) &&
                (section != null && !section.isEmpty())) {
            return studentRepository.findByYearLevelAndSection(yearLevel, section);
        } else if ((yearLevel != null && !yearLevel.isEmpty()) &&
                (course != null && !course.isEmpty())) {
            return studentRepository.findByYearLevelAndCourse(yearLevel, course);
        } else if ((section != null && !section.isEmpty()) &&
                (course != null && !course.isEmpty())) {
            return studentRepository.findBySectionAndCourse(section, course);
        } else if (yearLevel != null && !yearLevel.isEmpty()) {
            return studentRepository.findByYearLevel(yearLevel);
        } else if (section != null && !section.isEmpty()) {
            return studentRepository.findBySection(section);
        } else if (course != null && !course.isEmpty()) {
            return studentRepository.findByCourse(course);
        } else {
            return studentRepository.findAll();
        }
    }


    //Save Method
    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    //Get Id when edit button pressed method
    public Optional<Student> getStudentId(int id){
        return studentRepository.findById(id);
    }

    //Delete Method
    public void deleteStudentById(int id){
        studentRepository.deleteById(id);
    }

    public StudentUniqueValues getUniqueValues() {
        List<String> yearLevels = studentRepository.findAll()
                .stream()
                .map(Student::getYearLevel)
                .distinct()
                .collect(Collectors.toList());

        List<String> sections = studentRepository.findAll()
                .stream()
                .map(Student::getSection)
                .distinct()
                .collect(Collectors.toList());

        return new StudentUniqueValues(yearLevels, sections);
    }

    public List<Student> getStudentsWithoutAttendance() {
        return studentRepository.findStudentsWithoutAttendance();
    }

}
