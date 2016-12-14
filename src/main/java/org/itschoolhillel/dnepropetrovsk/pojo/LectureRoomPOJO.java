package org.itschoolhillel.dnepropetrovsk.pojo;

import org.itschoolhillel.dnepropetrovsk.entity.LectureRoom;

/**
 * Created by stephenvolf on 14/12/16.
 */
public class LectureRoomPOJO implements LectureRoom {
    public int floor;
    public int number;
    public String description;

    @Override
    public int floor() {
        return floor;
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public String description() {
        return description;
    }
}
