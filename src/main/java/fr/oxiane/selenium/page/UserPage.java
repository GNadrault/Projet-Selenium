package fr.oxiane.selenium.page;

import fr.oxiane.selenium.util.DescriptionPage;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@DescriptionPage(description = "La liste des utilisateurs du site", name = "Utilisateurs", image = "user.jpg")
public class UserPage extends FluentPage {

    @FindBy (css = "#userfilter")
    private FluentWebElement userFilter;

    @FindBy (css = "a[data-value=\"newusers\"]")
    private FluentWebElement newUsers;

    private static final String TITLE  = "Users - Stack Overflow";

    public FluentWebElement getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(FluentWebElement userFilter) {
        this.userFilter = userFilter;
    }

    public FluentWebElement getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(FluentWebElement newUsers) {
        this.newUsers = newUsers;
    }

    public static String getTITLE() {
        return TITLE;
    }
}
