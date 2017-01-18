package org.itschoolhillel.dnepropetrovsk;

import org.itschoolhillel.dnepropetrovsk.datasource.EntitySource;
import org.itschoolhillel.dnepropetrovsk.datasource.json.JsonEntitySource;
import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.LecturePOJO;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.SQLEntitySource;
import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.Lecture;

import java.util.Calendar;
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
        SQLEntitySource entitySource = new SQLEntitySource();
//        EntitySource entitySource = new JsonEntitySource();


        Calendar calendar = Calendar.getInstance();
        Date startTime = calendar.getTime();
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date endDime = calendar.getTime();


        Lecture lecture = new LecturePOJO(startTime, endDime, null, "title", "decription");
        entitySource.addLecture(null, lecture, 4);


        Course course = entitySource.course(this.courseName);
        //TODO: implement date filtration
        course.print();

        entitySource.close();
    }
}
