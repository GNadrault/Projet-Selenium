package fr.oxiane.selenium.page;

import fr.oxiane.selenium.util.DescriptionPage;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@PageUrl("https://stackoverflow.com/users/login")
@DescriptionPage(description = "Page de connexion des utilisateurs", name = "Connexion", image = "connexion.jpg")
public class LoginPage extends FluentPage {

    private static final String URL = "https://stackoverflow.com/users/login";
    private static final String TITLE = "Log In - Stack Overflow";

    @FindBy(css = "#email")
    private static FluentWebElement loginText;

    @FindBy(css = "#password")
    private static FluentWebElement passwordText;

    @FindBy(css = "#submit-button")
    private static FluentWebElement loginBtn;

    @FindBy(css = ".message-text")
    private static FluentWebElement errorMessage;

    public static String getURL() {
        return URL;
    }

    public static FluentWebElement getLoginText() {
        return loginText;
    }

    public static void setLoginText(FluentWebElement loginText) {
        LoginPage.loginText = loginText;
    }

    public static FluentWebElement getPasswordText() {
        return passwordText;
    }

    public static void setPasswordText(FluentWebElement passwordText) {
        LoginPage.passwordText = passwordText;
    }

    public static FluentWebElement getLoginBtn() {
        return loginBtn;
    }

    public static void setLoginBtn(FluentWebElement loginBtn) {
        LoginPage.loginBtn = loginBtn;
    }

    public static FluentWebElement getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(FluentWebElement errorMessage) {
        LoginPage.errorMessage = errorMessage;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    public static String getTITLE() {
        return TITLE;
    }
}
