package page;

import org.openqa.selenium.WebDriver;

import infra.Config;
import infra.Tool;

public abstract class Page {

	protected final WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
		
		// Check that we're on the right page.		
//		System.out.println("expected url : " + Config.get_fromService("baseUrl") + Tool.getUrl(this.getClass().getName()));
//		System.out.println("actual	 url : " + this.driver.getCurrentUrl());
		if (!((Config.get_fromService("baseUrl") + Tool.getUrl(this.getClass().getName()))
				.equals(this.driver.getCurrentUrl()))) {
			throw new IllegalStateException("This is not the " + this.getClass().getName());
		}
	}
}
