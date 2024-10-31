package com.icpep.AttendanceSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_info")
public class Student {

    @Id
    private int studentId;

    private String name;
    private String yearLevel;
    private String section;
    private String course;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
