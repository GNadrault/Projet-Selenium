package fr.oxiane.test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import fr.oxiane.util.WebDriverConfiguration;
import org.apache.log4j.Logger;
import org.fluentlenium.adapter.junit.FluentTest;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class BaseTest extends FluentTest{

    protected static int nbLog = 0;
    protected static int nbTest = 0;
    protected static int nbError = 0;
    protected WebDriver driver;
    protected ExtentTest loggerTest;
    protected static ExtentTest loggerClass;
    private static String currentClassName = "";
    private static Logger loggerConsole = Logger.getLogger(BaseTest.class);

    @Rule
    public TestRule initLogger = new TestWatcher() {
        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            takeScreenShotTest();
            try {
                loggerTest.pass("Passed: "+ description.getMethodName(), MediaEntityBuilder.createScreenCaptureFromPath((nbLog)+".png").build());
                loggerConsole.info("----- Passed -----");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenShotTest();
            try {
                loggerTest.fail("Failed: " + description.getMethodName() +", "+ e.getLocalizedMessage(), MediaEntityBuilder.createScreenCaptureFromPath((nbLog)+".png").build());
                loggerConsole.info("----- Failed -----");
                nbError++;
            } catch (IOException ioE) {
                ioE.printStackTrace();
            }
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            loggerTest.skip("Skiped: " + description.getMethodName() +", "+ e.getLocalizedMessage());
            loggerConsole.info("----- Skipped -----");
        }

        @Override
        protected void starting(Description description) {
            if (!currentClassName.equalsIgnoreCase(description.getClassName())){
                currentClassName = description.getClassName();
                loggerClass = SuiteTest.getLoggerReport().createNode(description.getClassName());
            }
            loggerConsole.info("----- Starting Test n°"+(++nbTest)+": "+description.getMethodName()+" -----");
            loggerTest = loggerClass.createNode(description.getMethodName());


        }

        @Override
        protected void finished(Description description) {
            loggerTest.getModel().setEndTime(new Date());
        }
    };

    @Override
    public WebDriver newWebDriver() {
        driver = WebDriverConfiguration.webDriverConfiguration();
        return driver;
    }

    public static int countNbScreenShot(){
        File f = new File("src/main/resources/rapport/");
        nbLog = 0;
        for (File file : f.listFiles()) {
            if ((file.isFile())&&(file.getName().endsWith(".png"))) {
                nbLog++;
            }
        }
        return nbLog;
    }

    public void takeScreenShotTest(){

        this.takeScreenShot(SuiteTest.getPathName()+(countNbScreenShot()+".png"));
    }
}
