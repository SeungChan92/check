package com.daou.chasedae.web_test;
import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;

import com.daou.chasedae.web_test.common.crawler.BasicCrawlController;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Main {

//	static Logger log = LogManager.getLogger("LevelLog");
//	static Logger log_NewLine = LogManager.getLogger("NewLine");

	private static ExtentReports reports = new ExtentReports("logs/[test_result]_adppurio.html", true);

	public static void main(String[] args) throws Exception {    

		reports.loadConfig(new File("src/main/resources/extent-config.xml"));
		ExtentTest logger = reports.startTest("adppurio");
		BasicCrawlController.crawl(reports, logger);
		
//		log_NewLine.log(Level.getLevel("BOUND"), "");
//		log.log(Level.getLevel("BOUND"), "main - start");
//		
//		JUnitCore.main("com.daou.chasedae.web_test.adppurio.Main_Chrome"//);
//				, "com.daou.chasedae.web_test.adppurio.Main_Firefox"
//				, "com.daou.chasedae.web_test.adppurio.Main_IE");
		
		reports.endTest(logger);
		reports.flush();
		reports.close();
	}
}
