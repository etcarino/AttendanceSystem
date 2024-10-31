package com.icpep.AttendanceSystem.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name ="event_id", nullable = false)
    private Event event;

    private LocalTime tIn;
    private LocalTime tOut;
    private LocalTime tInAm;
    private LocalTime tOutAm;
    private LocalTime tInPm;
    private LocalTime tOutPm;


    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalTime gettIn() {
        return tIn;
    }

    public void settIn(LocalTime tIn) {
        this.tIn = tIn;
    }

    public LocalTime gettOut() {
        return tOut;
    }

    public void settOut(LocalTime tOut) {
        this.tOut = tOut;
    }

    public LocalTime gettInAm() {
        return tInAm;
    }

    public void settInAm(LocalTime tInAm) {
        this.tInAm = tInAm;
    }

    public LocalTime gettOutAm() {
        return tOutAm;
    }

    public void settOutAm(LocalTime tOutAm) {
        this.tOutAm = tOutAm;
    }

    public LocalTime gettInPm() {
        return tInPm;
    }

    public void settInPm(LocalTime tInPm) {
        this.tInPm = tInPm;
    }

    public LocalTime gettOutPm() {
        return tOutPm;
    }

    public void settOutPm(LocalTime tOutPm) {
        this.tOutPm = tOutPm;
    }
}
