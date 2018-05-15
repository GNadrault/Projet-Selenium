package fr.oxiane.service;

import fr.oxiane.dto.Pages;
import org.springframework.beans.factory.annotation.Autowired;

public class PageService {

    @Autowired
    Pages pages;

    public Pages getAllPages(){
        return pages;
    }
}
