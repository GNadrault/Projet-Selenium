package fr.oxiane.selenium.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverConfiguration {

    private static Logger loggerConsole = Logger.getLogger(WebDriverConfiguration.class);
    private static WebDriver driver;
    private static DesiredCapabilities capabilities;
    private static Browser BROWSER = Browser.CHROME;
    private static final Dimension DIMENSION = new Dimension(1600,1200);

    public static WebDriver webDriverConfiguration(){
        loggerConsole.info("Démarrage du driver: "+ BROWSER);
        switch (BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.LINUX);
                try {
                    driver = new RemoteWebDriver(new URL(BROWSER.getUrl()), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case FIREFOX:
            default:
                WebDriverManager.firefoxdriver().setup();
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setPlatform(Platform.LINUX);
                try {
                    driver = new RemoteWebDriver(new URL(BROWSER.getUrl()), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case LOCAL:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        initDriver();
        return driver;
    }

    public static void initDriver(){
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(DIMENSION);
    }

    public static Logger getLoggerConsole() {
        return loggerConsole;
    }

    public static void setLoggerConsole(Logger loggerConsole) {
        WebDriverConfiguration.loggerConsole = loggerConsole;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        WebDriverConfiguration.driver = driver;
    }

    public static DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public static void setCapabilities(DesiredCapabilities capabilities) {
        WebDriverConfiguration.capabilities = capabilities;
    }

    public static Browser getBROWSER() {
        return BROWSER;
    }

    public static void setBROWSER(Browser BROWSER) {
        WebDriverConfiguration.BROWSER = BROWSER;
    }

    public static Dimension getDIMENSION() {
        return DIMENSION;
    }
}
