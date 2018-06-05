package fr.oxiane.dto;

import java.util.List;

public class InfoTest {

    private List<TestPage> tests;
    private String navigateur;

    public List<TestPage> getTests() {
        return tests;
    }

    public void setTests(List<TestPage> tests) {
        this.tests = tests;
    }

    public String getNavigateur() {
        return navigateur;
    }

    public void setNavigateur(String navigateur) {
        this.navigateur = navigateur;
    }
}
