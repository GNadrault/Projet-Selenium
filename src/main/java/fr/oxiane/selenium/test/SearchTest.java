package fr.oxiane.selenium.test;

import com.aventstack.extentreports.ExtentTest;
import fr.oxiane.selenium.page.AffectationPage;
import fr.oxiane.selenium.page.HomePage;
import fr.oxiane.selenium.page.LoginPage;
import fr.oxiane.selenium.page.UserPage;
import fr.oxiane.selenium.util.PageTest;

import org.apache.log4j.Logger;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.hook.wait.Wait;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Wait
public class SearchTest extends BaseTest {

    private static final String RECHERCHE_ESSAI = "spring";
    private static Logger logger = Logger.getLogger(SearchTest.class);
    private static final String RECHERCHE = "-ext@";
    private static final String MAISON = "ELS";
    private static final String GROUPE = "DSI";
    private static final String ROLE = "Administrateur";

    @Page
    private HomePage homePage;

    @Page
    private UserPage userPage;

    @Page
    private AffectationPage affectationPage;

    @Page
    private LoginPage loginPage;

    @Test
    @PageTest(pages = HomePage.class)
    public void rechercheTest(){
        goTo(homePage);
        homePage.getSearchBar().write("spring");
        homePage.getSearchButton().click();
        Assert.assertTrue("Search does not work correctly",window().title().contains(RECHERCHE_ESSAI));
    }

    @Test
    @PageTest(pages = {HomePage.class, UserPage.class})
    public void rechercheUserTest(){
        goTo(homePage);
        homePage.getUserOnglet().click();
        userPage.getNewUsers().click();
        Assert.assertTrue("Can not access to user page",window().title().equals(UserPage.getTITLE()));
    }

    @Test
    @PageTest(pages = AffectationPage.class)
    public void searchAffectations(){
        goTo(loginPage);
        loginPage.loginForm.fill().with("jqueinnec");
        loginPage.passwordForm.fill().with("TODO");
        loginPage.buttonForm.click();
        await().explicitlyFor(500, TimeUnit.MILLISECONDS);
        goTo(affectationPage);

        // SELENIUM
        affectationPage.inputRechercheUser.fill().with(RECHERCHE);
        await().explicitlyFor(300, TimeUnit.MILLISECONDS);
        affectationPage.selectFiltreHouse.fillSelect().withText(MAISON);
        await().explicitlyFor(300, TimeUnit.MILLISECONDS);
        affectationPage.selectFiltreGroup.fillSelect().withText(GROUPE);
        await().explicitlyFor(300, TimeUnit.MILLISECONDS);
        affectationPage.selectFiltreRole.fillSelect().withText(ROLE);
        logger.info("Lancement de la recherche avec Nom:"+ RECHERCHE +", Maison: "+ MAISON+ ", Groupe: "+ GROUPE+", Rôle: "+ROLE);
        //takeScreenshoot("affectationPage",affectationPage);
        if (affectationPage.tableResultatsLigne.size() > 0){
            logger.info( "Nombre de résultat trouvé: "+ affectationPage.tableResultatsLigne.size());
            for (FluentWebElement ligne: affectationPage.tableResultatsLigne) {
                FluentWebElement nom = ligne.$("tr > td.ng-binding:nth-child(2)").first();
                FluentWebElement prenom = ligne.$("tr > td.ng-binding:nth-child(3)").first();
                FluentWebElement mail = ligne.$("tr > td.ng-binding:nth-child(4)").first();
                FluentWebElement maison = ligne.$("tr > td.ng-binding:nth-child(5)").first();
                List<String> affectations = ligne.$("td > ul > li[ng-repeat=\"d in row.userGroups\"]").textContents();
                StringBuilder sb = new StringBuilder();
                for (String affect: affectations) {
                    sb.append(affect);
                }
                Assert.assertTrue("Le nom, prénom et/ou mail contient le contenu de la recherche",nom.textContent().contains(RECHERCHE) || prenom.textContent().contains(RECHERCHE) || mail.textContent().contains(RECHERCHE));
                //takeScreenshoot("affectationPage",affectationPage);
                if (sb.toString().contains(GROUPE) && sb.toString().contains(ROLE)){
                    logger.info("La recherche fonctionne correctement");
                } else {
                    logger.info("La recherche ne fonctionne pas correctement");
                }
            }
        } else {
            logger.info("Aucun résultat trouvé");
        }
    }

    @Test
    @PageTest(pages = AffectationPage.class)
    public void changePage(){

    }

    @Test
    @PageTest(pages = AffectationPage.class)
    public void editAffectation(){

    }

    @Test
    @PageTest(pages = AffectationPage.class)
    public void createAffectation(){

    }
}
