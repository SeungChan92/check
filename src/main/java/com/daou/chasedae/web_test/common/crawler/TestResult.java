package com.daou.chasedae.web_test.common.crawler;

import java.util.ArrayList;
import java.util.List;

public class TestResult {

	private String path;
	private ArrayList<String> inputTagList;
	
	public TestResult(String path) {
		this.path = path;
		inputTagList = new ArrayList<String>();
	}

	public void add_inputTag(String inputTag_name) {
		inputTagList.add(inputTag_name);
	}
	
	public String getPath() {
		return path;
	}
	public ArrayList<String> getInputTagList() {
		return inputTagList;
	}
}
