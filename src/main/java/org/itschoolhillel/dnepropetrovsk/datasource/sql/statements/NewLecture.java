package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import org.itschoolhillel.dnepropetrovsk.entity.Lecture;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by stephenvolf on 15/01/17.
 */
public class NewLecture implements StatementContext, StatementContext.ExecutionStrategy {
    private final Timestamp startTime;
    private final Timestamp endTime;
    private final int roomId;
    private final String title;
    private final String description;

    public NewLecture(Lecture lecture, int roomId) {
        this.startTime = new Timestamp(lecture.startTime().getTime());
        this.endTime = new Timestamp(lecture.endTime().getTime());
        this.roomId = roomId;
        this.title = lecture.title();
        this.description = lecture.description();
    }

    @Override
    public String sql() {
        return "insert into lectures(start_time, end_time, lecture_room_id, title, description)" +
                " VALUES(?,?,?,?,?)";
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, startTime);
        statement.setTimestamp(2, endTime);
        statement.setInt(3, roomId);
        statement.setString(4, title);
        statement.setString(5, description);
    }

    @Override
    public ExecutionStrategy strategy() {
        return this;
    }

}
