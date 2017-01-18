package org.itschoolhillel.dnepropetrovsk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by stephenvolf on 11/12/16.
 */
public class Main {
    private final static Logger LL = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ParseException {
//        if (args.length == 0){
//            LL.error("No arguments passed. Exiting.");
//            return;
//        }
//        String courseName = args[0];

        String courseName = "java12";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date from = sdf.parse("01-11-2016");
        Date to = sdf.parse("21-12-2016");
        Application app = new Application(from, to, courseName);
        app.run();
    }
}
