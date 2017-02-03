package org.itschoolhillel.dnepropetrovsk.datasource.sql.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by stephenvolf on 18/01/17.
 */
public class AddLecture implements StatementContext, StatementContext.ExecutionStrategy {

    private int courseId;
    private int lectureId;

    public AddLecture(int courseId, int lectureId) {
        this.courseId = courseId;
        this.lectureId = lectureId;
    }

    @Override
    public String sql() {
        return "insert into course_lectures(course_id, lecture_id) values(?,?)";
    }

    @Override
    public ExecutionStrategy strategy() {
        return this;
    }

    @Override
    public void setParameters(PreparedStatement statement) throws SQLException {
        statement.setInt(1, courseId);
        statement.setInt(2, lectureId);
    }
}
