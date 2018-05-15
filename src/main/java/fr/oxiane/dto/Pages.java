package fr.oxiane.dto;

import org.fluentlenium.core.FluentPage;

import java.util.ArrayList;
import java.util.List;

public class Pages {

    private List<FluentPage> pageList = new ArrayList<>();

    public List<FluentPage> getPageList() {
        return pageList;
    }

    public void setPageList(List<FluentPage> pageList) {
        this.pageList = pageList;
    }
}
