package org.itschoolhillel.dnepropetrovsk.pojo;

import org.itschoolhillel.dnepropetrovsk.entity.Student;

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
}
