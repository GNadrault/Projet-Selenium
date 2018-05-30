package fr.oxiane.selenium.test;

import fr.oxiane.selenium.util.LoadProperty;
import fr.oxiane.service.PageService;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {

    private static String pathToProperties;
    private static Logger loggerConsole = Logger.getLogger(TestRunner.class);

    public static void LaunchTest(List<Method> methods){
        LoadProperty loadProperty = new LoadProperty();
        loadProperty.updateListMethodsTest();
        JUnitCore junitRunner = new JUnitCore();
        SuiteTest.startingReport();
        for (Method method : methods) {
			Request request = Request.method(method.getDeclaringClass(), method.getName());
			Result result = junitRunner.run(request);
			System.out.println(result.wasSuccessful());
		}
		SuiteTest.endingReport();
    }
}
