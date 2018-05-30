package fr.oxiane.dto;
import java.util.List;

public class Page {

    private String name;
    private String image;
    private String description;
    private String pageName;
    private boolean isSelected;
    private List<TestPage> testsPage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}

	public List<TestPage> getTestsPage() {
		return testsPage;
	}

	public void setTestsPage(List<TestPage> testsPage) {
		this.testsPage = testsPage;
	}
}