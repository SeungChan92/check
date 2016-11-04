package suite;

public class Message extends Suite {

	@org.junit.Test
	public void test() {
		webDriver.get("http://www.google.com");
	}
}
