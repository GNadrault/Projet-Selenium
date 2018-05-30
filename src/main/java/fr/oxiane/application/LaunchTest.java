package fr.oxiane.application;

import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import fr.oxiane.selenium.test.SuiteTest;
import fr.oxiane.selenium.util.LoadProperty;

public class LaunchTest {
	
    private static Logger loggerConsole = Logger.getLogger(LaunchTest.class);

	public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SuiteTest.class);
        for (Failure failure : result.getFailures()) {
            loggerConsole.info(failure.toString());
        }
        loggerConsole.info(result.wasSuccessful());
	}

}
