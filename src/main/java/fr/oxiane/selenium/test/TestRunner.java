package fr.oxiane.selenium.test;

import fr.oxiane.selenium.util.Browser;
import fr.oxiane.selenium.util.LoadProperty;
import fr.oxiane.selenium.util.WebDriverConfiguration;
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

    public static void launchTest(List<Method> methods, String navigateur){
        LoadProperty loadProperty = new LoadProperty();
        loadProperty.updateListMethodsTest();
        JUnitCore junitRunner = new JUnitCore();
        SuiteTest.startingReport();
        updateBrowser(navigateur);
        for (Method method : methods) {
			Request request = Request.method(method.getDeclaringClass(), method.getName());
			Result result = junitRunner.run(request);
			System.out.println(result.wasSuccessful());
		}
		SuiteTest.endingReport();
    }

    public static void updateBrowser(String navigateur){
        Browser browser = Browser.fromString(navigateur);
        WebDriverConfiguration.setBROWSER(browser);
    }
}

