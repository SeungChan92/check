//package com.daou.chasedae.web_test.common.crawler;
///**
// * Licensed to the Apache Software Foundation (ASF) under one or more
// * contributor license agreements.  See the NOTICE file distributed with
// * this work for additional information regarding copyright ownership.
// * The ASF licenses this file to You under the Apache License, Version 2.0
// * (the "License"); you may not use this file except in compliance with
// * the License.  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//import java.util.ArrayList;
//import java.util.Set;
//import java.util.regex.Pattern;
//
//import org.apache.http.Header;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import edu.uci.ics.crawler4j.crawler.Page;
//import edu.uci.ics.crawler4j.crawler.WebCrawler;
//import edu.uci.ics.crawler4j.parser.HtmlParseData;
//import edu.uci.ics.crawler4j.url.WebURL;
//
///**
// * @author Yasser Ganjisaffar
// */
//public class BasicCrawler extends WebCrawler {
//
//	public static ArrayList<TestResult> testResultList = new ArrayList<TestResult>();
//
//	private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");
//
//	/**
//	 * You should implement this function to specify whether the given url
//	 * should be crawled or not (based on your crawling logic).
//	 */
//	@Override
//	public boolean shouldVisit(Page referringPage, WebURL url) {
//		// avoid logout
//		WebURL webUrl = new WebURL();
//		webUrl.setURL("http://www.adppurio.com/logout");
//		if (url.equals(webUrl)) {
//			return false;
//		}
//
//		// avoid number only URL
//		if(Character.isDigit(url.getPath().charAt(url.getPath().length()-1)))
//		{
//			return false;
//		}
//
//		String href = url.getURL().toLowerCase();
//		// Ignore the url if it has an extension that matches our defined set of image extensions.
//		if (IMAGE_EXTENSIONS.matcher(href).matches()) {
//			return false;
//		}
//
//		// Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
//		return href.startsWith("http://www.adppurio.com/");
//	}
//
//	/**
//	 * This function is called when a page is fetched and ready to be processed
//	 * by your program.
//	 */
//	@Override
//	public void visit(Page page) {
//		//		System.out.println("visit()");
//
//		int docid = page.getWebURL().getDocid();
//		String url = page.getWebURL().getURL();
//		String domain = page.getWebURL().getDomain();
//		String path = page.getWebURL().getPath();
//		String subDomain = page.getWebURL().getSubDomain();
//		String parentUrl = page.getWebURL().getParentUrl();
//		String anchor = page.getWebURL().getAnchor();
//
//		TestResult testResult = new TestResult(path);
//
//		logger.debug("Docid: {}", docid);
//		logger.info("URL: {}", url);
//		logger.debug("Domain: '{}'", domain);
//		logger.debug("Sub-domain: '{}'", subDomain);
//		logger.debug("Path: '{}'", path);
//		logger.debug("Parent page: {}", parentUrl);
//		logger.debug("Anchor text: {}", anchor);
//
//		if (page.getParseData() instanceof HtmlParseData) {
//			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
//			String text = htmlParseData.getText();
//			String html = htmlParseData.getHtml();
//			Set<WebURL> links = htmlParseData.getOutgoingUrls();
//
//			logger.debug("Text length: {}", text.length());
//			logger.debug("Html length: {}", html.length());
//			logger.debug("Number of outgoing links: {}", links.size());
//			Document doc = Jsoup.parse(html);
//			Elements elements = doc.select("input[type=text]");
//
//			for (Element element: elements) {
//				logger.debug("Element name        : " + element.attr("name"));
//				testResult.add_inputTag(element.attr("name"));
//			}
//		}
//
//		Header[] responseHeaders = page.getFetchResponseHeaders();
//		if (responseHeaders != null) {
//			logger.debug("Response headers:");
//			for (Header header : responseHeaders) {
//				logger.debug("\t{}: {}", header.getName(), header.getValue());
//			}
//		}
//		
//		testResultList.add(testResult);
//
//		logger.debug("=============");
//	}
//}