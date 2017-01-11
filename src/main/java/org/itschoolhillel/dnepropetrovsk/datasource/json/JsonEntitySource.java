package org.itschoolhillel.dnepropetrovsk.datasource.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.itschoolhillel.dnepropetrovsk.datasource.EntitySource;
import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.Lecture;
import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.CoursePOJO;
import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.LecturePOJO;
import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.TimeTablePOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by stephenvolf on 08/01/17.
 */
public class JsonEntitySource implements EntitySource {
    private final Logger LL = LoggerFactory.getLogger(JsonEntitySource.class);

    @Override
    public Course course(String courseName) {
        Course course = new CoursePOJO(courseName, new TimeTablePOJO(new LinkedList<>()));
        Path courseFile = Paths.get("src/main/resources/courses", courseName + ".txt");

        String json = null;

        try {
            json = new String(Files.readAllBytes(courseFile));
        } catch (IOException e) {
            LL.error("Could not read file", e);
            return course;
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
            return course;
        }

        Course result = new CoursePOJO(courseName, new TimeTablePOJO(lectureList));
        return result;
    }

    @Override
    public Map<Integer, List<String>> subscriptions() {
        Map<Integer, List<String>> result = new HashMap<>();
        Path subs = Paths.get("src/main/resources/subscriptions.txt");

        byte[] json;
        try {
            json = Files.readAllBytes(subs);
        } catch (IOException e) {
            LL.error("Failed to open file", e);
            return result;
        }

        try {
            result = new ObjectMapper().readValue(json, Map.class);
        } catch (IOException e) {
            LL.error("Failed to read file", e);
        }
        return result;
    }

    @Override
    public void close() {

    }
}
