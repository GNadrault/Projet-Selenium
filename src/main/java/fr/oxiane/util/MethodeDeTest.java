package fr.oxiane.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MethodeDeTest {

    private List<Method> methodTestable = new ArrayList<>();
    private List<Method> methodIgnore = new ArrayList<>();
    private StringBuilder methodATester = new StringBuilder();
    private StringBuilder methodAIgnorer = new StringBuilder();

    public List<Method> getMethodTestable() {
        return methodTestable;
    }

    public void setMethodTestable(List<Method> methodTestable) {
        this.methodTestable = methodTestable;
    }

    public List<Method> getMethodIgnore() {
        return methodIgnore;
    }

    public void setMethodIgnore(List<Method> methodIgnore) {
        this.methodIgnore = methodIgnore;
    }

    public StringBuilder getMethodATester() {
        return methodATester;
    }

    public void setMethodATester(StringBuilder methodATester) {
        this.methodATester = methodATester;
    }

    public StringBuilder getMethodAIgnorer() {
        return methodAIgnorer;
    }

    public void setMethodAIgnorer(StringBuilder methodAIgnorer) {
        this.methodAIgnorer = methodAIgnorer;
    }
}
