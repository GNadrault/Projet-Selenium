package fr.oxiane.selenium.test;

import fr.oxiane.selenium.util.LoadProperty;
import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {

    private static String pathToProperties;
    private static Logger loggerConsole = Logger.getLogger(TestRunner.class);

    public static void main(String args[]){
        LoadProperty loadProperty = new LoadProperty();
        loadProperty.updateListMethodsTest();
        Result result = JUnitCore.runClasses(SuiteTest.class);
        if (result.getFailures().size()>0){
            loggerConsole.info("Errors: "+BaseTest.nbError);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        loggerConsole.info(result.wasSuccessful());
    }
}
