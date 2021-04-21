package gui.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class testingLog4 {
    private static final Logger logger = LogManager.getLogger(testingLog4.class);

    public static void main(String[] args) {
        System.out.println("hello");

        logger.info("this is an info");
        logger.warn("this is an warning");

        logger.warn("this is an warning");
        System.out.println("end");
    }
}
