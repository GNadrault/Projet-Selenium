package fr.oxiane.service;

import fr.oxiane.dto.*;
import fr.oxiane.selenium.test.SuiteTest;
import fr.oxiane.selenium.test.TestRunner;
import fr.oxiane.selenium.util.DescriptionPage;
import fr.oxiane.selenium.util.LoadProperty;

import fr.oxiane.selenium.util.PageTest;
import org.fluentlenium.core.FluentPage;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class PageService {

	private static final String PACKAGE_NAME = "fr.oxiane.selenium.page";
	private static List<Method> methods;
	private static final String PATH_TO_FILE ="http://localhost:8181/rapport/";

	public List<Page> getAllPages() {
		List<Page> pages = new ArrayList<>();
		Reflections getPages = new Reflections(PACKAGE_NAME);
		Set<Class<? extends FluentPage>> classesPages = getPages.getSubTypesOf(FluentPage.class);
		for (Class classPage : classesPages) {
			Page page = new Page();
			mettreAJourPage(page, classPage);
			pages.add(page);
		}
		pages.sort(Comparator.comparing(Page::getName));
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
		page.setPageName(classPage.getName());
		page.setDescription(descriptionPage.description());
		page.setName(descriptionPage.name());
		page.setImage(descriptionPage.image());
		page.setTestsPage(searchMethodsTestPage(page, classPage));
	}

	private List<TestPage> searchMethodsTestPage(Page page, Class classPage) {
		MethodeDeTest methodesATester = LoadProperty.getListeMethode();
		List<TestPage> methods = new ArrayList<>();
		for (Method method : methodesATester.getMethodTestable()) {
			if (method.isAnnotationPresent(PageTest.class)) {
				PageTest pageTest = method.getAnnotation(PageTest.class);
				if (Arrays.asList(pageTest.pages()).contains(classPage)) {
					TestPage testPage = new TestPage();
					testPage.setName(method.getName());
					testPage.setClasse(method.getDeclaringClass().getName());
					testPage.setPageName(page.getName());
					methods.add(testPage);
				}
			}
		}
		return methods;
	}

	public Rapport testAllPageTest(InfoTest infoTest) {
		getMethodsTest(infoTest.getTests());
		TestRunner.launchTest(methods, infoTest.getNavigateur());
		Rapport rapport = new Rapport();
		rapport.setChemin(PATH_TO_FILE+SuiteTest.getFilePath().toString());
		return rapport;
	}

	private List<Method> getMethodsTest(List<TestPage> testPages){
		methods = new ArrayList<>();
		for (TestPage testPage: testPages) {
			try {
				Class<?> c = getClassByName(testPage.getClasse());
				Method method = c.getDeclaredMethod(testPage.getName());
				methods.add(method);
			}  catch (NoSuchMethodException e) {
				e.printStackTrace();
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

	public static String getPackageName() {
		return PACKAGE_NAME;
	}

	public static String getPathToFile() {
		return PATH_TO_FILE;
	}
}
