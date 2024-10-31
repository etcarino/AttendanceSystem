package com.icpep.AttendanceSystem.model;

import java.util.List;

public class StudentUniqueValues {

    private List<String> yearLevels;
    private List<String> sections;


    public StudentUniqueValues(List<String> yearLevels, List<String> sections) {
        this.yearLevels = yearLevels;
        this.sections = sections;
    }

    public List<String> getYearLevels() {
        return yearLevels;
    }

    public void setYearLevels(List<String> yearLevels) {
        this.yearLevels = yearLevels;
    }

    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

}
