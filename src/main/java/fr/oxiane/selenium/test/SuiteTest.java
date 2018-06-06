package fr.oxiane.selenium.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({LoginTest.class, SearchTest.class})
public class SuiteTest{

    private static ExtentReports report;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentTest loggerReport;
    private static Logger loggerConsole = Logger.getLogger(SuiteTest.class);
    private static String pathToReport;
    private static StringBuilder filePath;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
    /**
     * Démarrage du rapport de tests
     */
    //@BeforeClass
    public static void startingReport(){
        pathToReport = Thread.currentThread().getContextClassLoader().getResource("static/rapport").getPath();
        LocalDateTime now = LocalDateTime.now();
        String dateFormatted = now.format(formatter);
        filePath = new StringBuilder("rapport_");
        filePath.append(dateFormatted).append(".html");
        loggerConsole.info("Chemin du rapport: "+ pathToReport + "/" + filePath);
        htmlReporter = new ExtentHtmlReporter(pathToReport + "/" + filePath);
        report = new ExtentReports();
        report.attachReporter(htmlReporter);
        loggerReport = report.createTest("Test Stack Overflow", "Test de login fail et de navigation dans les menus");
        loggerReport.assignCategory("Selenium");
        loggerReport.assignAuthor("G. Nadrault");
    }

    /**
     * Fin du rapport de tests
     */
    //@AfterClass
    public static void endingReport() {
        loggerConsole.info("Ecriture dans le rapport: " + pathToReport + "/" + filePath);
        report.flush();
        loggerConsole.info("Fin du rapport");
    }

    public static ExtentReports getReport() {
        return report;
    }

    public static void setReport(ExtentReports report) {
        SuiteTest.report = report;
    }

    public static ExtentHtmlReporter getHtmlReporter() {
        return htmlReporter;
    }

    public static void setHtmlReporter(ExtentHtmlReporter htmlReporter) {
        SuiteTest.htmlReporter = htmlReporter;
    }

    public static ExtentTest getLoggerReport() {
        return loggerReport;
    }

    public static void setLoggerReport(ExtentTest loggerReport) {
        SuiteTest.loggerReport = loggerReport;
    }

    public static Logger getLoggerConsole() {
        return loggerConsole;
    }

    public static void setLoggerConsole(Logger loggerConsole) {
        SuiteTest.loggerConsole = loggerConsole;
    }

    public static String getPathToReport() {
        return pathToReport;
    }

    public static void setPathToReport(String pathToReport) {
        SuiteTest.pathToReport = pathToReport;
    }

    public static StringBuilder getFilePath() {
        return filePath;
    }

    public static void setFilePath(StringBuilder filePath) {
        SuiteTest.filePath = filePath;
    }
}
