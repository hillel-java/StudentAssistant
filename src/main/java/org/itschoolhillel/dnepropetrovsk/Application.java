package org.itschoolhillel.dnepropetrovsk;

import org.itschoolhillel.dnepropetrovsk.datasource.EntitySource;
import org.itschoolhillel.dnepropetrovsk.datasource.json.JsonEntitySource;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.SQLEntitySource;
import org.itschoolhillel.dnepropetrovsk.entity.Course;

import java.util.Date;

/**
 * Created by stephenvolf on 08/01/17.
 */
public class Application {
    private final Date from;
    private final Date to;
    private final String courseName;

    public Application(Date from, Date to, String courseName){
        this.from = from;
        this.to = to;
        this.courseName = courseName;
    }

    public void run(){
        EntitySource entitySource = new SQLEntitySource();
//        EntitySource entitySource = new JsonEntitySource();
        Course course = entitySource.course(this.courseName);
        //TODO: implement date filtration
        course.print();
        entitySource.close();
    }
}
