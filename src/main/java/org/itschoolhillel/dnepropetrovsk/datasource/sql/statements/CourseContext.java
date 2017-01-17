package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.CoursePOJO;
import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.LecturePOJO;
import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.LectureRoomPOJO;
import org.itschoolhillel.dnepropetrovsk.datasource.json.pojo.TimeTablePOJO;
import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.Lecture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by stephenvolf on 11/01/17.
 */
public class CourseContext implements QueryContext<Course> {
    private final String courseName;
    private final QueryStrategy<List<Lecture>> strategy = new CourseStatementStrategy();
    public CourseContext(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public Course result() {
        return new CoursePOJO(courseName, new TimeTablePOJO(strategy.result()));
    }

    @Override
    public String sql() {
        return "select l.start_time," +
                " l.end_time," +
                " lr.floor as roomFloor," +
                " lr.`number` as roomNumber," +
                " lr.description as roomDescription," +
                " l.title," +
                " l.description" +
                " from" +
                " lectures as l join lecture_rooms as lr on" +
                " l.lecture_room_id = lr.id join course_lectures as cl on" +
                " l.id = cl.lecture_id join courses as c on" +
                " cl.course_id = c.id" +
                " where c.title = ?";
    }

    @Override
    public QueryStrategy strategy() {
        return strategy;
    }

    private class CourseStatementStrategy implements QueryStrategy<List<Lecture>> {
        private final List<Lecture> result = new LinkedList<>();

        @Override
        public void setParameters(PreparedStatement statement) throws SQLException {
            statement.setString(1, courseName);
        }

        @Override
        public void extractResult(ResultSet resultSet) throws SQLException {
            while (resultSet.next()) {
                Date startDate = resultSet.getDate("start_time");
                Date endDate = resultSet.getDate("end_time");
                int floor = resultSet.getInt("roomFloor");
                int roomNumber = resultSet.getInt("roomNumber");
                String roomDescription = resultSet.getString("roomDescription");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LectureRoomPOJO lectureRoom = new LectureRoomPOJO(floor, roomNumber, roomDescription);
                Lecture lecture = new LecturePOJO(startDate, endDate, lectureRoom, title, description);
                result.add(lecture);
            }
        }

        @Override
        public List<Lecture> result() {
            return result;
        }
    }
}
