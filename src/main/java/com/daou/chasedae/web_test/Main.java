package com.daou.chasedae.web_test;

import org.junit.runner.JUnitCore;

import com.daou.chasedae.web_test.gosms.Main_Chrome;
import com.daou.chasedae.web_test.common.Data;
import com.daou.chasedae.web_test.common.Report;
import com.daou.chasedae.web_test.common.crawler.MyCrawlController;

public class Main {

//	static Logger log = LogManager.getLogger("LevelLog");
//	static Logger log_NewLine = LogManager.getLogger("NewLine");

//	private static ExtentReports reports = new ExtentReports("logs/[test_result]_adppurio.html", true);

	public static void main(String[] args) throws Exception {    

		JUnitCore.runClasses(Main_Chrome.class);
	}
}
