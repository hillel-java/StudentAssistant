package org.itschoolhillel.dnepropetrovsk.pojo;

import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.TimeTable;

/**
 * Created by stephenvolf on 14/12/16.
 */
public class CoursePOJO implements Course {
    public String title;
    public TimeTable timeTable;

    public CoursePOJO(String title, TimeTable timeTable) {
        this.title = title;
        this.timeTable = timeTable;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public TimeTable timeTable() {
        return timeTable;
    }
}
