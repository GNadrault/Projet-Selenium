package fr.oxiane.dto;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MethodeDeTest {

    private List<Method> methodTestable = new ArrayList<>();
    private List<Method> methodIgnore = new ArrayList<>();
    private StringBuilder nameMethodATester = new StringBuilder();
    private StringBuilder nameMethodAIgnorer = new StringBuilder();
    
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
	public StringBuilder getNameMethodATester() {
		return nameMethodATester;
	}
	public void setNameMethodATester(StringBuilder nameMethodATester) {
		this.nameMethodATester = nameMethodATester;
	}
	public StringBuilder getNameMethodAIgnorer() {
		return nameMethodAIgnorer;
	}
	public void setNameMethodAIgnorer(StringBuilder nameMethodAIgnorer) {
		this.nameMethodAIgnorer = nameMethodAIgnorer;
	}
    
    

}
