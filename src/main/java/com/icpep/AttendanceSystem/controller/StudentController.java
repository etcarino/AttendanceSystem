package com.icpep.AttendanceSystem.controller;

import com.icpep.AttendanceSystem.model.Student;
import com.icpep.AttendanceSystem.model.StudentDto;
import com.icpep.AttendanceSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    //constructor injection
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Get all students w/o filter method
    @GetMapping({"", "/"})
    public String viewStudentPage(@RequestParam(required = false) String yearLevel,
                                  @RequestParam(required = false) String section,
                                  @RequestParam(required = false) String course,
                                  Model model) {
        List<Student> students = studentService.getFilteredStudents(yearLevel, section, course);
        model.addAttribute("students", students);
        model.addAttribute("yearLevel", yearLevel);
        model.addAttribute("course", course);
        model.addAttribute("section", section);
        return "/students/index";
    }


    //when addStudentForm is entered the method will be used and will go to add student form page
    @GetMapping("/addStudentForm")
    public String addStudentForm(Model model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("studentDto", studentDto); // Consistent with the form
        return "students/add_student";
    }

    //Save Student Info
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute StudentDto studentDto, Model model) {
        try {

            String sanitizedId = studentDto.getStudentId().replaceAll("-", "");
            int sanitizedStudentId = Integer.parseInt(sanitizedId);

            Student student = new Student();
            student.setStudentId(sanitizedStudentId);
            student.setName(studentDto.getName());
            student.setYearLevel(studentDto.getYearLevel());
            student.setSection(studentDto.getSection());
            student.setCourse(studentDto.getCourse());

            studentService.saveStudent(student);
        } catch (NumberFormatException e) {

            model.addAttribute("error", "Invalid student ID format.");
            return "students/add_student";
        }

        return "redirect:/students";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/updateStudentForm/{id}")
    public String updateStudentForm(@PathVariable(value = "id") int id, Model model) {
        Student student = studentService.getStudentId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "students/update_student";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") int id){
        this.studentService.deleteStudentById(id);
        return "redirect:/students";
    }


}
