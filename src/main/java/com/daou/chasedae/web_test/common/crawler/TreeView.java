//package com.daou.chasedae.web_test.common.crawler;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//
//public class TreeView {
//
//	private static ExtentReports reports;
//	private static ExtentTest rootTest;
//	private static ExtentTest parentTest;
//	static HashMap<String, ExtentTest> testList = new HashMap <String, ExtentTest>();
//
//	public TreeView(ExtentReports reports, ExtentTest rootTest) {
//		this.reports = reports;
//		this.rootTest = rootTest;
//		this.parentTest = rootTest;
//	}
//
//	public static void put(TreeMap structure, String root, String rest, TestResult testResult) {
//		String[] tmp = rest.split("/", 2);
//		
//		// append test
//		if (!testList.containsKey(tmp[0]))
//		{
//			ExtentTest test = reports.startTest(tmp[0]);
//			for(String inputTag_name : testResult.getInputTagList()) {
//				test.log(LogStatus.INFO, inputTag_name);
//			}
//			testList.put(tmp[0], test);
//			testList.get(root).appendChild(test);
//		}
//
//		TreeMap rootDir = (TreeMap) structure.get(root);
//
//		if (rootDir == null) {
//			rootDir = new TreeMap();
//			structure.put(root, rootDir);
//
//		}
//		
//		System.out.println("TreeView.put()");
//		System.out.println("root\t: " + root);
//		System.out.println("tmp[0]\t: " + tmp[0]);
//		System.out.println("");
//
//		if (tmp.length == 1) { // path end
//			rootDir.put(tmp[0], null);
////			for(String inputTag_name : testResult.getInputTagList()) {
////				testList.get("tmp[0]").log(LogStatus.PASS, inputTag_name);
////			}
//		} else {
//			put(rootDir, tmp[0], tmp[1], testResult);
//		}
//	}
//	public static void print(String parent, TreeMap map, String delimeter) {
//		if (map == null || map.isEmpty())
//			return;
//		for (Object m : map.entrySet()) {
////			if (!testList.containsKey((String)((Map.Entry)m).getKey()))
////			{
////				ExtentTest test = reports.startTest((String)((Map.Entry)m).getKey());
////				testList.put((String)((Map.Entry)m).getKey(), test);
////				testList.get(parent).appendChild(test);
////			}
//
//			System.out.println(delimeter + "-" + ((Map.Entry)m).getKey());
//			print((String)((Map.Entry)m).getKey(), (TreeMap)((Map.Entry)m).getValue(), " |" + delimeter);
//		}
//	}
//}
