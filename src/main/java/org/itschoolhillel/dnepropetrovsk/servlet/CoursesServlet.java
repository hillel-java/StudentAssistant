package org.itschoolhillel.dnepropetrovsk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by stephenvolf on 05/02/17.
 */
public class CoursesServlet extends HttpServlet {

    // /courses/bystudent?name=Vasiliy&surname=Pupkin
    // /courses/bylecturer?name=Vasiliy&surname=Pupkin

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String paramName = "";
        if (!pathInfo.isEmpty()) {
            String[] pathTokens = pathInfo.split("/");
            if (pathTokens.length > 1) {
                paramName = pathTokens[1];
            }
        }

        String userType;
        switch (paramName) {
            case "bystudent":
                userType = "student";
                break;
            case "bylecturer":
                userType = "lecturer";
                break;
            default:
                return;
        }

        Map<String, String[]> pathParams = req.getParameterMap();
        String name = pathParams.get("name")[0];
        String surname = pathParams.get("surname")[0];


    }
}
