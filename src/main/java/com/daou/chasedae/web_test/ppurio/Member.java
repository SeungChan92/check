package com.daou.chasedae.web_test.ppurio;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.daou.chasedae.web_test.common.Tool;

public class Member {
	private WebDriver driver;
	private String baseUrl;
	private Tool tool;

	public Member(WebDriver driver, String baseUrl, Tool tool) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.tool = tool;
	}

	public void login() throws Exception {
		driver.get(baseUrl + "/mgr/index.qri");
		driver.findElement(By.id("p_id")).clear();
		driver.findElement(By.id("p_id")).sendKeys("issea1015");
		driver.findElement(By.id("p_ps")).clear();
		driver.findElement(By.id("p_ps")).sendKeys("vnfms2357!");
		driver.findElement(By.cssSelector("button.bt_login.bt_stl01")).click();
	}

	public void logout() throws Exception {
		driver.get(baseUrl + "/mgr/index.qri");
		driver.findElement(By.linkText("로그아웃")).click();
	}

	public void findId_ByMobile() throws Exception {
		driver.get(baseUrl + "/mgr/DFFindIdPwd.qri?act=find_id_pwd&what=id");
		driver.findElement(By.linkText("아이디찾기")).click();
		driver.findElement(By.id("username_emailmobile")).clear();
		driver.findElement(By.id("username_emailmobile")).sendKeys("안승찬");
		driver.findElement(By.id("mobile2")).clear();
		driver.findElement(By.id("mobile2")).sendKeys("2959");
		driver.findElement(By.id("mobile3")).clear();
		driver.findElement(By.id("mobile3")).sendKeys("1783");
		driver.findElement(By.id("DoSubmit_emailmobile")).click();
		driver.findElement(By.linkText("‘ * ’ 부분 확인하기")).click();
		driver.findElement(By.linkText("인증번호 받기")).click();
		tool.waitForAlert();
		assertEquals("선택하신 수단으로 아이디를 전송하였습니다.", tool.closeAlertAndGetItsText());
	}

	public void findId_ByEmail() throws Exception {
	    driver.get(baseUrl + "/mgr/index.qri");
	    driver.findElement(By.linkText("아이디찾기")).click();
	    driver.findElement(By.id("email")).click();
	    driver.findElement(By.id("username_emailmobile")).clear();
	    driver.findElement(By.id("username_emailmobile")).sendKeys("안승찬");
	    driver.findElement(By.id("email1")).clear();
	    driver.findElement(By.id("email1")).sendKeys("issea1015");
	    driver.findElement(By.id("email2")).clear();
	    driver.findElement(By.id("email2")).sendKeys("gmail.com");
	    driver.findElement(By.id("DoSubmit_emailmobile")).click();
	    driver.findElement(By.linkText("‘ * ’ 부분 확인하기")).click();
	    driver.findElement(By.linkText("인증메일 받기")).click();
	    tool.waitForAlert();
	    assertEquals("선택하신 수단으로 아이디를 전송하였습니다.", tool.closeAlertAndGetItsText());
	  }
}
