package org.itschoolhillel.dnepropetrovsk.pojo;

import org.itschoolhillel.dnepropetrovsk.entity.Lecture;
import org.itschoolhillel.dnepropetrovsk.entity.LectureRoom;

import java.util.Date;

/**
 * Created by stephenvolf on 14/12/16.
 */
public class LecturePOJO implements Lecture {
    public Date startTime;
    public Date endTime;
    public LectureRoomPOJO lectureRoom;
    public String title;
    public String description;

    @Override
    public Date startTime() {
        return startTime;
    }

    @Override
    public Date endTime() {
        return endTime;
    }

    @Override
    public LectureRoom lectureRoom() {
        return lectureRoom;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String description() {
        return description;
    }
}
