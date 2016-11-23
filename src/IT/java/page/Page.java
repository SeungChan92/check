package page;

import org.openqa.selenium.WebDriver;

import infra.Config;

public abstract class Page {

	protected final WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page.
		System.out.println("baseUrl : " + Config.get_fromService("baseUrl"));
		System.out.println("page's Url : " + Config.get_url(this.getClass().getSimpleName()));
		System.out.println("current Url : " + this.driver.getCurrentUrl());
		
//		if (!((Config.get_fromAsp("baseUrl") + Config.get_url(this.getClass().getSimpleName())).equals(this.driver.getCurrentUrl()))) {
//			throw new IllegalStateException("This is not the " + this.getClass().getName());
//		}
	}
}
