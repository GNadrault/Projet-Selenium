package fr.oxiane.controller;

import fr.oxiane.dto.Pages;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sie")
public class PageController {

    public Pages getAllPages(){
        return null;
    }
}
