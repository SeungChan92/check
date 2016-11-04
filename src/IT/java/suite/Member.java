package suite;

public class Member extends Suite{
	
	@org.junit.Test
	public void test() {
		webDriver.get("http://www.naver.com");
	}
}