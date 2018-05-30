package fr.oxiane.selenium.page;

import fr.oxiane.selenium.util.DescriptionPage;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;

@PageUrl("https://stackoverflow.com/questions/tagged/{recherche}")
@DescriptionPage(description = "La liste des sujets du site", name = "Sujets", image = "sujet.jpg")
public class SubjectPage extends FluentPage {
}
