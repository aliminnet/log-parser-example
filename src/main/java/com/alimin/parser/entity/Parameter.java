package com.alimin.parser.entity;

public class Parameter {

    private String path;

    private String startDate;

    private String duration;

    private String threshold;

    public Parameter(String path, String startDate, String duration, String threshold) {
        this.path = path;
        this.startDate = startDate;
        this.duration = duration;
        this.threshold = threshold;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
}
