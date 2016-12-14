package org.itschoolhillel.dnepropetrovsk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.Lecture;
import org.itschoolhillel.dnepropetrovsk.entity.LectureRoom;
import org.itschoolhillel.dnepropetrovsk.pojo.CoursePOJO;
import org.itschoolhillel.dnepropetrovsk.pojo.LecturePOJO;
import org.itschoolhillel.dnepropetrovsk.pojo.TimeTablePOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stephenvolf on 11/12/16.
 */
public class Main {
    private final static Logger LL = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length == 0){
            LL.error("No arguments passed. Exiting.");
            return;
        }
        String courseName = args[0];

        Path courseFile = Paths.get("src/main/resources", courseName + ".txt");

        String json = null;

        try {
            json = new String(Files.readAllBytes(courseFile));
        } catch (IOException e) {
            LL.error("Could not read file", e);
            return;
        }

        List<Lecture> lectureList = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
            mapper.setDateFormat(df);
            Lecture[] lectures = mapper.readValue(json, LecturePOJO[].class);
            lectureList = Arrays.asList(lectures);
        } catch (IOException e) {
            LL.error("Could not deserialize json", e);
            return;
        }

        Course course = new CoursePOJO(courseName, new TimeTablePOJO(lectureList));
        print(course);
    }

    private static void print(Course course){
        System.out.println("Course: " + course.title());
        for(Lecture lecture : course.timeTable().allLectures()){
            System.out.println("Lecture " + lecture.title());
            System.out.println("start: " + lecture.startTime());
            System.out.println("end: " + lecture.endTime());
            System.out.println("description: " + lecture.description());
            LectureRoom room = lecture.lectureRoom();
            System.out.println("room: " + room.floor() + " floor, " + room.number() + ", " + room.description());
        }
    }
}
