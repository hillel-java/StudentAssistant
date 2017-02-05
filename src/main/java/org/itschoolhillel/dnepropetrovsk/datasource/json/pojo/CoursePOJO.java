package org.itschoolhillel.dnepropetrovsk.datasource.json.pojo;

import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.Lecture;
import org.itschoolhillel.dnepropetrovsk.entity.LectureRoom;
import org.itschoolhillel.dnepropetrovsk.entity.TimeTable;

import java.io.PrintStream;

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

    @Override
    public void print(PrintStream ps){
        ps.println("Course: " + this.title);
        for (Lecture lecture : this.timeTable.allLectures()) {
            ps.println("Lecture " + lecture.title());
            ps.println("start: " + lecture.startTime());
            ps.println("end: " + lecture.endTime());
            ps.println("description: " + lecture.description());
            LectureRoom room = lecture.lectureRoom();
            ps.println("room: " + room.floor() + " floor, " + room.number() + ", " + room.description());
        }
    }

    @Override
    public void print(){
        print(System.out);
    }
}
