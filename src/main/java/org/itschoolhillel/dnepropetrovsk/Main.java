package org.itschoolhillel.dnepropetrovsk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by stephenvolf on 11/12/16.
 */
public class Main {
    private final static Logger LL = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length == 0){
            LL.error("No arguments passed. Exiting.");
            return;
        }
        String courseName = args[0];
        //Let the fun begin!
    }
}
