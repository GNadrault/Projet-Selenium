package fr.oxiane.selenium.test;

import fr.oxiane.selenium.page.LoginPage;
import fr.oxiane.selenium.util.PageTest;

import org.apache.log4j.Logger;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Wait
public class LoginTest extends BaseTest {

    @Page
    private LoginPage loginPage;

    private static final String USERNAME_FAIL = "test@gmail.com";
    private static final String PASSWORD_FAIL = "test";
    private static final String MESSAGE_LOGIN_FAIL = "The email or password is incorrect.";
    private static Logger logger = Logger.getLogger(LoginTest.class);

    @Test
    @PageTest(pages = LoginPage.class)
    public void loginFailTest() {
        goTo(loginPage);
        LoginPage.getLoginText().write(USERNAME_FAIL);
        LoginPage.getPasswordText().write(PASSWORD_FAIL);
        LoginPage.getLoginBtn().click();
        await().explicitlyFor(5, TimeUnit.SECONDS).until(LoginPage.getErrorMessage()).displayed();
        Assert.assertTrue("Error message is not displayed", LoginPage.getErrorMessage().displayed());
        Assert.assertTrue("Error message is not correct", LoginPage.getErrorMessage().text().contains(MESSAGE_LOGIN_FAIL));
    }
}
