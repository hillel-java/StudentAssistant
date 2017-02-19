package org.itschoolhillel.dnepropetrovsk.servlet;

import org.itschoolhillel.dnepropetrovsk.ContextListener;
import org.itschoolhillel.dnepropetrovsk.datasource.EntitySource;
import org.itschoolhillel.dnepropetrovsk.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by stephenvolf on 05/02/17.
 */
public class LecturesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String courseName = "";
        if (!pathInfo.isEmpty()) {
            String[] pathTokens = pathInfo.split("/");
            if (pathTokens.length > 1) {
                courseName = pathTokens[1];
            }
        }

        if(courseName.isEmpty()){
            return;
        }

        EntitySource source = (EntitySource) getServletContext().getAttribute("ContextListener.ENTITY_SOURCE_KEY");
        Course course = source.course(courseName);
        course.print(new PrintStream(resp.getOutputStream()));
    }
}
