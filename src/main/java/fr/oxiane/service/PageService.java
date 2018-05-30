package fr.oxiane.service;

import fr.oxiane.dto.MethodeDeTest;
import fr.oxiane.dto.Page;
import fr.oxiane.dto.TestPage;
import fr.oxiane.selenium.test.TestRunner;
import fr.oxiane.selenium.util.DescriptionPage;
import fr.oxiane.selenium.util.LoadProperty;

import fr.oxiane.selenium.util.PageTest;
import org.fluentlenium.core.FluentPage;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class PageService {

	private static final String PACKAGE_NAME = "fr.oxiane.selenium.page";
	private static List<Method> methods;

	public List<Page> getAllPages() {
		List<Page> pages = new ArrayList<>();
		Reflections getPages = new Reflections(PACKAGE_NAME);
		Set<Class<? extends FluentPage>> classesPages = getPages.getSubTypesOf(FluentPage.class);
		for (Class classPage : classesPages) {
			Page page = new Page();
			mettreAJourPage(page, classPage);
			pages.add(page);
		}
		return pages;
	}

	public Page findPageByName(String name){
		Page page = new Page();
		Reflections getPage = new Reflections(PACKAGE_NAME);
		for (Class<?> cl : getPage.getTypesAnnotatedWith(DescriptionPage.class)) {
			DescriptionPage descriptionPage = cl.getAnnotation(DescriptionPage.class);
			if (descriptionPage.name().equals(name)){
				mettreAJourPage(page, cl);
			}
		}
		return page;
	}

	private void mettreAJourPage(Page page, Class classPage) {
		DescriptionPage descriptionPage = (DescriptionPage) classPage.getAnnotation(DescriptionPage.class);
		page.setTestsPage(searchMethodsTestPage(classPage));
		page.setDescription(descriptionPage.description());
		page.setName(descriptionPage.name());
		page.setImage(descriptionPage.image());
		page.setPageName(classPage.getName());
	}

	private List<TestPage> searchMethodsTestPage(Class classPage) {
		MethodeDeTest methodesATester = LoadProperty.getListeMethode();
		List<TestPage> methods = new ArrayList<>();
		for (Method method : methodesATester.getMethodTestable()) {
			if (method.isAnnotationPresent(PageTest.class)) {
				PageTest pageTest = method.getAnnotation(PageTest.class);
				if (Arrays.asList(pageTest.pages()).contains(classPage)) {
					TestPage testPage = new TestPage();
					testPage.setName(method.getName());
					methods.add(testPage);
				}
			}
		}
		return methods;
	}

	public List<Page> testAllPageTest(List<Page> pages) {
		getMethodsToTest(pages);
		TestRunner.LaunchTest(methods);
		return pages;
	}
	
	private List<Method> getMethodsToTest(List<Page> pages) {
		MethodeDeTest methodesATester = LoadProperty.getListeMethode();
		methods = new ArrayList<>();
		for (Method method : methodesATester.getMethodTestable()) {
			if (method.isAnnotationPresent(PageTest.class)) {
				PageTest pageTest = method.getAnnotation(PageTest.class);
				for (Page page : pages) {
					Class classPage = getClassByName(page.getPageName());
					if (Arrays.asList(pageTest.pages()).contains(classPage) && !methods.contains(method)) {
						methods.add(method);
					}
				}
			}
		}
		return methods;
	}


	private Class getClassByName(String className) {
		Class classPage = null;
		try {
			classPage = Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classPage;
	}

	public static List<Method> getMethods() {
		return methods;
	}

	public static void setMethods(List<Method> methods) {
		PageService.methods = methods;
	}
}
