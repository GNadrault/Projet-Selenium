package fr.oxiane.selenium.page;

import fr.oxiane.selenium.util.DescriptionPage;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by jqueinnec on 01/02/2017.
 */
@PageUrl("http://10.16.14.5:8282/#/login")
@DescriptionPage(description = "La page de login ELS", name = "Login ELS", image = "accueil.jpg")
public class LoginPage extends FluentPage {

    @FindBy(name = "username")
    public FluentWebElement loginForm;
    @FindBy(name = "password")
    public FluentWebElement passwordForm;
    @FindBy(tagName = "button")
    public FluentWebElement buttonForm;

    @FindBy(xpath = "//div[@class='modal-dialog modal-login']/descendant::input[@name='username'] ")
    public FluentWebElement loginFormPopup;
    @FindBy(xpath = "//div[@class='modal-dialog modal-login']/descendant::input[@name='password'] ")
    public FluentWebElement passwordFormPopup;
    @FindBy(xpath = "//div[@class='modal-dialog modal-login']/descendant::button ")
    public FluentWebElement buttonFormPopup;


    //le texte permettant d'indiquer que l'on se trouve sur le site d'adminsitration
    @FindBy(css = "h5.ng-binding")
    public FluentWebElement ecmText;
    @FindBy(css = "span.ng-binding")
    public FluentWebElement version;
    //les erreurs en cas de non connexion
    @FindBy(className = "alert-danger")
    public FluentWebElement errorMessage;

    @FindBy(name = "loginForm")
    public FluentWebElement completeLoginForm;

    @FindBy(css = "h4[translate=\"login.modalTitle\"]")
    public FluentWebElement erreurExpire;

    //un booleen pour savoir si on est connecté
    public boolean connected = false;
    protected WebDriver driver;
    protected String baseUrl = "";

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void isAt() {
        await().until(buttonForm).displayed();
    }



    public LoginPage setLoginForm(String userLogin) {
        if (loginForm.present()) {
            loginForm.clear();
            loginForm.fill().with(userLogin);
        }
        return this;
    }

    public LoginPage setPasswordForm(String userPassword) {
        if (passwordForm.present()) {
            passwordForm.clear();
            passwordForm.fill().with(userPassword);
        }
        return this;
    }

    public LoginPage clickLoginButton() {
        if (buttonForm.present()) {
            await().explicitlyFor(2,TimeUnit.SECONDS).atMost(10, TimeUnit.SECONDS).until(buttonForm).clickable();
            buttonForm.click();
        }
        return this;
    }

public boolean errorMessageIsDisplay() {

        if (errorMessage != null) {
            String testMessage = errorMessage.text();
            return errorMessage.displayed();
        }
        return false;
    }

    public String getVersion() {
        if (version != null) {
            return version.text();
        }
        return "";
    }

    public LoginPage loginToECMUserFlashPage(String userLogin, String userPassword) {
        if (buttonForm.present()) {
            this.setLoginForm(userLogin)
                    .setPasswordForm(userPassword)
                    .clickLoginButton();
            connected = true;
        }
        return this;
    }
    public LoginPage loginToECMUserFlashPagePopup(String userLogin, String userPassword) {
        loginFormPopup.clear();
        loginFormPopup.fill().with(userLogin);
        passwordFormPopup.clear();
        passwordFormPopup.fill().with(userPassword);
        await().explicitlyFor(500,TimeUnit.MILLISECONDS);
        buttonFormPopup.click();
        await().explicitlyFor(500,TimeUnit.MILLISECONDS);
        connected = true;
        return this;
    }

    public boolean isConnected() {
        return connected;// !(buttonForm.present()&&buttonForm.displayed());
    }

}

