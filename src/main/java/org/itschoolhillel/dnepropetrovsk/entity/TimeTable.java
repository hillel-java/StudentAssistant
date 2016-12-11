package org.itschoolhillel.dnepropetrovsk.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by stephenvolf on 12/12/16.
 */
public interface TimeTable {
    List<Lecture> allLectures();
    List<Lecture> lecturesFrom(Date date);
    List<Lecture> lecturesTo(Date date);
}
