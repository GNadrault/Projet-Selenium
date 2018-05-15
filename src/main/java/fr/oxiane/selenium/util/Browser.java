package fr.oxiane.selenium.util;

public enum Browser {
    CHROME("http://localhost:4445/wd/hub"),
    FIREFOX("http://localhost:4446/wd/hub"),
    IE("a definir"),
    EDGE("a definir"),
    LOCAL("");

    private String url;

    Browser(String paramUrl) {
        url = paramUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
