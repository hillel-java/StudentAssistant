package org.itschoolhillel.dnepropetrovsk.entity;

import java.io.PrintStream;

/**
 * Created by stephenvolf on 12/12/16.
 */
public interface Course {
    String title();
    TimeTable timeTable();
    void print();
    void print(PrintStream ps);
}
