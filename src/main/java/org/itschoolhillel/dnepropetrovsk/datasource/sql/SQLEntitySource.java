package org.itschoolhillel.dnepropetrovsk.datasource.sql;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.itschoolhillel.dnepropetrovsk.datasource.EntitySource;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.AddLecture;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.CourseContext;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.statements.NewLecture;
import org.itschoolhillel.dnepropetrovsk.entity.Course;
import org.itschoolhillel.dnepropetrovsk.entity.Lecture;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by stephenvolf on 08/01/17.
 */
public class SQLEntitySource implements EntitySource {
    private BoneCP pool;
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    public SQLEntitySource() {

    }

    private void init() {
        synchronized (initialized) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                return;
            }

            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl("jdbc:mysql://mysql-t2micro.c6wjr65m2iif.us-west-2.rds.amazonaws.com:3306/students_assistant");
            config.setUsername("student");
            config.setPassword("student");

            try {
                pool = new BoneCP(config);
                initialized.set(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Course course(String courseName) {
        if (!initialized.get()) {
            init();
        }
        CourseContext context = new CourseContext(courseName);
        SQLStatement statement = new SQLStatement(pool);
        statement.executeQuery(context);
        return context.result();
    }

    public void addLecture(Course course, Lecture lecture) {
        if (!initialized.get()) {
            init();
        }

        NewLecture newLectureContext = new NewLecture(lecture, 4);

        SQLStatement statement = new SQLStatement(pool);
        statement.executeInsert(newLectureContext);
        AddLecture addLectureContext = new AddLecture(1, newLectureContext.generatedKey());
        statement.executeUpdate(addLectureContext);
    }

    @Override
    public Map<Integer, List<String>> subscriptions() {
        if (!initialized.get()) {
            init();
        }
        return new HashMap<>();
    }

    @Override
    public void close() {
        pool.shutdown();
    }
}
