package com.daou.chasedae.web_test;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;

public class Main {

	static Logger log = LogManager.getLogger("LevelLog");
	static Logger log_NewLine = LogManager.getLogger("NewLine");

	public static void main(String[] args) throws Exception {    

		log_NewLine.log(Level.getLevel("BOUND"), "");
		log.log(Level.getLevel("BOUND"), "main - start");
		
		JUnitCore.main("com.daou.chasedae.web_test.adppurio.Main_Chrome"//);
				, "com.daou.chasedae.web_test.adppurio.Main_Firefox"
				, "com.daou.chasedae.web_test.adppurio.Main_IE");
	}
}
