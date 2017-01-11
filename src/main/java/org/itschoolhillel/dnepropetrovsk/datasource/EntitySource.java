package org.itschoolhillel.dnepropetrovsk.datasource;

import org.itschoolhillel.dnepropetrovsk.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * Created by stephenvolf on 08/01/17.
 */
public interface EntitySource {

    Course course(String courseName);

    Map<Integer, List<String>> subscriptions();

    void close();
}
