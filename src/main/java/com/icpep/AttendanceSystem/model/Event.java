package com.icpep.AttendanceSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_info")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
    private String name;
    private boolean wholeDay;
    private boolean eventSelected = false;
    private String clockState = "NONE";

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWholeDay() {
        return wholeDay;
    }

    public void setWholeDay(boolean wholeDay) {
        this.wholeDay = wholeDay;
    }

    public boolean isEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(boolean eventSelected) {
        this.eventSelected = eventSelected;
    }

    public String getClockState() {
        return clockState;
    }

    public void setClockState(String clockState) {
        this.clockState = clockState;
    }
}
