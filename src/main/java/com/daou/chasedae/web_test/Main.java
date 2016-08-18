package com.daou.chasedae.web_test;

import org.junit.runner.JUnitCore;

import com.daou.chasedae.web_test.adppurio.Main_Chrome;
import com.daou.chasedae.web_test.common.Data;
import com.daou.chasedae.web_test.common.crawler.MyCrawlController;

public class Main {

//	static Logger log = LogManager.getLogger("LevelLog");
//	static Logger log_NewLine = LogManager.getLogger("NewLine");

//	private static ExtentReports reports = new ExtentReports("logs/[test_result]_adppurio.html", true);

	public static void main(String[] args) throws Exception {    

		// 2nd Edition
		MyCrawlController.crawl();
//		Data.readFile();
		JUnitCore.runClasses(Main_Chrome.class);
		Data.writeFile();
		
		
		// 1st Edition
//		reports.loadConfig(new File("src/main/resources/extent-config.xml"));
//		ExtentTest logger = reports.startTest("adppurio");
		
//		log_NewLine.log(Level.getLevel("BOUND"), "");
//		log.log(Level.getLevel("BOUND"), "main - start");
//		
//		JUnitCore.main("com.daou.chasedae.web_test.adppurio.Main_Chrome"//);
//				, "com.daou.chasedae.web_test.adppurio.Main_Firefox"
//				, "com.daou.chasedae.web_test.adppurio.Main_IE");
		
//		reports.endTest(logger);
//		reports.flush();
//		reports.close();
	}
}
