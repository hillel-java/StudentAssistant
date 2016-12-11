package org.itschoolhillel.dnepropetrovsk.entity;

import java.util.Date;

/**
 * Created by stephenvolf on 12/12/16.
 */
public interface Lecture {
    Date startTime();
    Date endTime();
    LectureRoom lectureRoom();
    String title();
    String description();
}
