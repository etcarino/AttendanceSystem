package com.icpep.AttendanceSystem.model;

import java.time.LocalTime;

public class AttendanceDto {

    private String studentId;
    private int eventId;
    private LocalTime tIn = null;
    private LocalTime tOut = null;
    private LocalTime tInAm = null;
    private LocalTime tOutAm = null;
    private LocalTime tInPm = null;
    private LocalTime tOutPm = null;
    private String clockState;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public String getClockState() {
        return clockState;
    }

    public void setClockState(String clockState) {
        this.clockState = clockState;
    }

    public void setCurrentTimeBasedOnClockState(LocalTime currentTime) {
        switch (clockState) {
            case "CLOCK_IN":
                this.tIn = currentTime;
                break;
            case "CLOCK_OUT":
                this.tOut = currentTime;
                break;
            case "CLOCK_IN_AM":
                this.tInAm = currentTime;
                break;
            case "CLOCK_OUT_AM":
                this.tOutAm = currentTime;
                break;
            case "CLOCK_IN_PM":
                this.tInPm = currentTime;
                break;
            case "CLOCK_OUT_PM":
                this.tOutPm = currentTime;
                break;
        }
    }
}
