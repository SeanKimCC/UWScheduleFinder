package com.sean.kim.model.impl;

import com.sean.kim.model.ExamDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by seankim on 2016-09-26.
 */
@Entity
@Table(name ="UW_EXAM_SCHEDULE")
public class ExamDetailsImpl extends BaseModelImpl implements ExamDetails, Serializable{

    @Column(name = "COURSE")
    private String course;

    @Column(name = "SECTION")
    private String section;

    @Column(name = "DAY")
    private String day;

    @Column(name = "DATE")
    private String date;

    @Column(name = "START_TIME")
    private String startTime;

    @Column(name = "END_TIME")
    private String endTime;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "NOTE")
    private String note;


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
