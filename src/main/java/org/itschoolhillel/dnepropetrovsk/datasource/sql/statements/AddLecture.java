package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import org.itschoolhillel.dnepropetrovsk.entity.Lecture;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by stephenvolf on 18/01/17.
 */
public class AddLecture implements StatementContext, StatementContext.ExecutionStrategy {

    private int courseId;
    private Timestamp startTime;
    private int roomId;

    public AddLecture(int courseId, Date startDate, int roomId){
        this.courseId = courseId;
        this.startTime = new Timestamp(startDate.getTime());
        this.roomId = roomId;
    }

    @Override
    public String sql() {
        return "insert into course_lectures(course_id, lecture_id)" +
                " select ?, id from lectures where start_time=? and lecture_room_id=?";
    }

    @Override
    public ExecutionStrategy strategy() {
        return this;
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        statement.setInt(1, courseId);
        statement.setTimestamp(2, startTime);
        statement.setInt(3, roomId);
    }
}
