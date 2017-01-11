package org.itschoolhillel.dnepropetrovsk.datasource.json.pojo;

import org.itschoolhillel.dnepropetrovsk.entity.Student;
import org.itschoolhillel.dnepropetrovsk.entity.TimeTable;

/**
 * Created by svolkovskyi on 20.12.16.
 */
public class StudentPOJO implements Student {
    public String name;
    public String surname;

    public StudentPOJO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public StudentPOJO() {

    }


    @Override
    public String name() {
        return null;
    }

    @Override
    public String surname() {
        return null;
    }

    @Override
    public TimeTable timeTable() {
        return null;
    }
}
