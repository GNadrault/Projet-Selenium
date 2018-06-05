package fr.oxiane.selenium.util;

public enum Browser {
    CHROME("http://localhost:4445/wd/hub", "Chrome"),
    FIREFOX("http://localhost:4446/wd/hub", "Firefox"),
    IE("à definir", "Internet Explorer"),
    EDGE("à definir", "Edge"),
    LOCAL("à définir","Local");

    private String url;
    private String name;

    Browser(String paramUrl, String paramName) {
        url = paramUrl;
        name = paramName;
    }
    
    public static Browser fromString(String name){
        if (name != null){
            for (Browser browser: Browser.values()) {
                if (browser.getName().equalsIgnoreCase(name)){
                    return browser;
                }
            }
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
