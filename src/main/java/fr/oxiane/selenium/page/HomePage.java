package fr.oxiane.selenium.page;

import fr.oxiane.selenium.util.DescriptionPage;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;
import org.openqa.selenium.support.FindBy;

@PageUrl("https://stackoverflow.com/")
@DescriptionPage(description = "La page d'accueil du site StackOverflow", name = "Accueil", image = "accueil.jpg")
public class HomePage extends FluentPage {

    private final static String URL = "https://stackoverflow.com/";
    private final static String TITLE = "Stack Overflow - Where Developers Learn, Share, & Build Careers";

    @FindBy(css = ".login-link")
    private FluentWebElement loginBtn;

    @FindBy(css = ".f-input")
    private FluentWebElement searchBar;

    @FindBy(css = ".btn-topbar-primary")
    private FluentWebElement searchButton;

    @FindBy(css = "#nav-users")
    private FluentWebElement userOnglet;

    public static String getURL() {
        return URL;
    }

    public FluentWebElement getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(FluentWebElement loginBtn) {
        this.loginBtn = loginBtn;
    }

    public static String getTITLE() {
        return TITLE;
    }

    public FluentWebElement getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(FluentWebElement searchBar) {
        this.searchBar = searchBar;
    }

    public FluentWebElement getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(FluentWebElement searchButton) {
        this.searchButton = searchButton;
    }

    public FluentWebElement getUserOnglet() {
        return userOnglet;
    }

    public void setUserOnglet(FluentWebElement userOnglet) {
        this.userOnglet = userOnglet;
    }
}
