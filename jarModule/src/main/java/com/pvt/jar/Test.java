package com.pvt.jar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(
            Test.class);

    private static final String FILENAME = "/file/does/not/exist";

    public static void main(String[] args) {
        logger.info("Just a log message.");
        logger.debug("Message for debug level.");
        System.out.println("hihihi");
    }
}
