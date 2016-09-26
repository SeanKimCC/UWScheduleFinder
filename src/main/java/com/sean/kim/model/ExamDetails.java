package com.sean.kim.model;

/**
 * Created by seankim on 2016-09-26.
 */
public interface ExamDetails{

    String getCourse();

    void setCourse(String course);

    String getSection();

    void setSection(String section);

    String getDay();

    void setDay(String day);

    String getDate();

    void setDate(String date);

    String getStartTime();

    void setStartTime(String startTime);

    String getEndTime();

    void setEndTime(String endTime);

    String getLocation();

    void setLocation(String location) ;

    String getNote();

    void setNote(String note);
}
