package fr.oxiane.selenium.test;

import fr.oxiane.selenium.page.HomePage;
import fr.oxiane.selenium.page.LoginPage;
import fr.oxiane.selenium.page.UserPage;
import fr.oxiane.selenium.util.PageTest;

import org.apache.log4j.Logger;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Assert;
import org.junit.Test;

@Wait
public class SearchTest extends BaseTest {

    private static final String RECHERCHE = "spring";
    private static Logger logger = Logger.getLogger(SearchTest.class);

    @Page
    private HomePage homePage;

    @Page
    private UserPage userPage;

    @Test
    @PageTest(pages = HomePage.class)
    public void rechercheTest(){
        goTo(homePage);
        homePage.getSearchBar().write("spring");
        homePage.getSearchButton().click();
        Assert.assertTrue("Search does not work correctly",window().title().contains(RECHERCHE));
    }

    @Test
    @PageTest(pages = {HomePage.class, UserPage.class})
    public void rechercheUserTest(){
        goTo(homePage);
        homePage.getUserOnglet().click();
        userPage.getNewUsers().click();
        Assert.assertTrue("Can not access to user page",window().title().equals(UserPage.getTITLE()));
    }
}
