package fr.oxiane.controller;

import fr.oxiane.dto.Page;
import fr.oxiane.dto.TestPage;
import fr.oxiane.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sie")
public class PageController {

    @Autowired
    PageService pageService;

    @GetMapping("/pages")
    public List<Page> getAllPages(){
        return pageService.getAllPages();
    }

    @GetMapping("/pages/{name}")
    public Page findPageByName(@PathVariable String name) {
        return pageService.findPageByName(name);
    }
    
    @PostMapping("/pages")
    public List<Page> testerPages(@RequestBody ArrayList<Page> pages){
        System.out.println("J'AI RECU L'ORDRE DE LANCER LES TESTS");
        return pageService.testAllPageTest(pages);
    }
}
