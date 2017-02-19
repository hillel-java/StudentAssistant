package org.itschoolhillel.dnepropetrovsk.datasource.sql;

import org.itschoolhillel.dnepropetrovsk.datasource.EntitySource;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.AddLecture;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.CourseContext;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.NewLecture;
import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stephenvolf on 08/01/17.
 */
@Service
public class SQLEntitySource implements EntitySource {
    private final DataSource ds;

    public SQLEntitySource(@Autowired DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Course course(String courseName) {

        CourseContext context = new CourseContext(courseName);
        SQLStatement statement = new SQLStatement(ds);
        statement.executeQuery(context);
        return context.result();
    }

    public void addLecture(Course course, Lecture lecture) {

        NewLecture newLectureContext = new NewLecture(lecture, 4);

        SQLStatement statement = new SQLStatement(ds);
        statement.executeInsert(newLectureContext);
        AddLecture addLectureContext = new AddLecture(1, newLectureContext.generatedKey());
        statement.executeUpdate(addLectureContext);
    }

    @Override
    public Map<Integer, List<String>> subscriptions() {

        return new HashMap<>();
    }

    @Override
    public void close() {

    }
}
