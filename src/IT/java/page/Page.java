package page;

import org.openqa.selenium.WebDriver;

import infra.Config;

public class Page {
	
	protected final WebDriver driver;
	
	public Page(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        if (!(Config.get("baseUrl") + Config.get_url("this.getClass().getName()")).equals(this.driver.getCurrentUrl())) {
            throw new IllegalStateException("This is not the " + this.getClass().getName());
        }
    }
}
