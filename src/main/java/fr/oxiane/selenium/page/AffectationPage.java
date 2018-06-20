package fr.oxiane.selenium.page;

import fr.oxiane.selenium.util.DescriptionPage;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


@PageUrl("http://10.16.14.5:8282/#/users")
@FindBy(css = "span[translate='userApp.user.home.title']")
@DescriptionPage(description = "La page des affectation ELS", name = "Affectation", image = "accueil.jpg")
public class AffectationPage extends FluentPage {


    @FindBy(css = "a[href*='#/users']")
    public FluentWebElement linkAccesPage;
    @FindBy(css = "span[translate='userApp.user.home.title']")
    public FluentWebElement titrePage;

    @FindBy(css = "input[ng-change=\"$ctrl.loadAllUser('filtre')\"]")
    public FluentWebElement inputRechercheUser;
    @FindBy(css = "button[href*='#/users/new']")
    public FluentWebElement buttonAddUser;
    @FindBy(css="button[ng-click=\"$ctrl.loadAllUser('filtre')\"]")
    public FluentWebElement buttonSearch;
    @FindBy(css = "select[ng-change=\"$ctrl.houseChanged()\"]")
    public FluentWebElement selectFiltreHouse;
    @FindBy(css = "select[ng-change=\"$ctrl.groupChanged()\"]")
    public FluentWebElement selectFiltreGroup;
    @FindBy(css = "select[ng-change=\"$ctrl.roleChanged()\"]")
    public FluentWebElement selectFiltreRole;
    @FindBy(css = "button[ng-click=\"$ctrl.loadAllUser('init')\"]")
    public FluentWebElement buttonRefresh;
    @FindBy(css = "table[st-table=\"users\"]")
    public FluentWebElement tableResultats;
    @FindBy(css = "table[st-table=\"users\"] > tbody > tr[ng-repeat=\"row in $ctrl.users\"]")
    public List<FluentWebElement> tableResultatsLigne;

    @Page
    protected LoginPage loginpage;

    public AffectationPage go(String userLogin, String userPassword) {
        if (!loginpage.connected) {
            loginpage.loginToECMUserFlashPage(userLogin, userPassword);
            await().explicitlyFor(500, TimeUnit.MILLISECONDS);
            if (loginpage.erreurExpire.present() && loginpage.erreurExpire.displayed()) {
                loginpage.connected = false;
                loginpage.loginToECMUserFlashPagePopup(userLogin, userPassword);
            }
        }
        await().until(linkAccesPage).clickable();
        linkAccesPage.click();
        return this;
    }

}
