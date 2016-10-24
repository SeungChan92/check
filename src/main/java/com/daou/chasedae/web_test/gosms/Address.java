package com.daou.chasedae.web_test.gosms;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.daou.chasedae.web_test.common.Category;
import com.daou.chasedae.web_test.common.Tool;
import com.relevantcodes.extentreports.ExtentTest;

public class Address extends Category {

	public Address(WebDriver driver, String baseUrl, Tool tool, ExtentTest logger) {
		super("Address", driver, baseUrl, tool, logger);
	}

	public void addGroup(String group_name) throws Exception {
		driver.get(baseUrl + "/");
		tool.click(By.linkText("주소록"));
		driver.findElement(By.id("addGroupName")).clear();
		driver.findElement(By.id("addGroupName")).sendKeys(group_name);
		driver.findElement(By.id("addGroup")).click();
		tool.waitFor_alert();
		assertEquals("new_group 그룹을 추가하였습니다.", tool.closeAlert_andGetItsText());
		tool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(group_name)));
	}

	public void deleteGroup() throws Exception {
		driver.get(baseUrl + "/");
		tool.wait.until(ExpectedConditions.elementToBeClickable(By.linkText("주소록")));
	    driver.findElement(By.linkText("주소록")).click();
	    driver.findElement(By.name("no")).click();
	    tool.wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteGroup")));
	    driver.findElement(By.id("deleteGroup")).click();
	    tool.waitFor_alert();
	    assertTrue(tool.closeAlert_andGetItsText().matches("^1개의 그룹을 삭제 하시겠습니까[\\s\\S]$"));
	    tool.waitFor_alert();
	    assertEquals("그룹을 삭제 하였습니다.", tool.closeAlert_andGetItsText());
	}
	
	public void addAddress(String group_name, String name, String mobile) throws Exception {
		String url = "mgr/PPAddressMgr.qri?act=p_addform";
		By button_submit = By.xpath("//a[@href='javascript:jsDoSubmit();']");
		
	    driver.get(baseUrl + "/" + url);
	    
	    tool.selectByValue(By.name("groupno"), "5683");
	    driver.findElement(By.name("firstname")).clear();
	    driver.findElement(By.name("firstname")).sendKeys(name);
	    driver.findElement(By.name("_mobile")).clear();
	    driver.findElement(By.name("_mobile")).sendKeys(mobile);
	    tool.click(button_submit);
	  }

	public void deleteAddress() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("주소록")).click();
	    driver.findElement(By.linkText("new_group")).click();
	    tool.click(By.name("no"));
	    driver.findElement(By.id("deleteMember")).click();
	    tool.waitFor_alert();
	    assertEquals("주소를 삭제 하였습니다.", tool.closeAlert_andGetItsText());
	  }

	public void editAddress(String name, String moblie, String phone, String fax, String email) throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("주소록")).click();
	    driver.findElement(By.linkText("new_group")).click();
	    driver.findElement(By.linkText("수정")).click();
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"name\"]")).clear();
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"name\"]")).sendKeys(name);
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"mobile\"]")).clear();
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"mobile\"]")).sendKeys(moblie);
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"phone\"]")).clear();
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"phone\"]")).sendKeys(phone);
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"fax\"]")).clear();
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"fax\"]")).sendKeys("0102222222");
	    driver.findElement(By.xpath("(//input[@name='email'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@name='email'])[2]")).sendKeys("issea1015");
	    driver.findElement(By.linkText("저장")).click();
	    tool.waitFor_alert();
	    assertEquals("FAX번호 형식이 올바르지 않습니다.", tool.closeAlert_andGetItsText());
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"fax\"]")).clear();
	    driver.findElement(By.cssSelector("td.pad6 > input[name=\"fax\"]")).sendKeys(fax);
	    driver.findElement(By.linkText("저장")).click();
	    tool.waitFor_alert();
	    assertEquals("이메일 형식이 올바르지 않습니다.", tool.closeAlert_andGetItsText());
	    driver.findElement(By.xpath("(//input[@name='email'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@name='email'])[2]")).sendKeys("issea1015@1.1");
	    driver.findElement(By.linkText("저장")).click();
	    tool.waitFor_alert();
	    assertEquals("이메일 형식이 올바르지 않습니다.", tool.closeAlert_andGetItsText());
	    driver.findElement(By.xpath("(//input[@name='email'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@name='email'])[2]")).sendKeys(email);
	    driver.findElement(By.linkText("저장")).click();
	    tool.waitFor_alert();
	    assertEquals("주소를 수정 하였습니다.", tool.closeAlert_andGetItsText());
	    tool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + name + "']")));
	  }

	public void sendAddress() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("주소록")).click();
	    driver.findElement(By.name("no")).click();
	    driver.findElement(By.id("sendAddress")).click();
	    tool.goTo_PopUp();
	    driver.findElement(By.id("get_id")).clear();
	    driver.findElement(By.id("get_id")).sendKeys("issea1015");
	    driver.findElement(By.id("get_name")).clear();
	    driver.findElement(By.id("get_name")).sendKeys("안승찬");
	    driver.findElement(By.id("funSend")).click();
	    tool.waitFor_alert();
	    assertEquals("주소록 보내기를 완료하였습니다.", tool.closeAlert_andGetItsText());
	    tool.goTo_main();
	  }
	
	public void sendAddress_check() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("주소록")).click();
	    tool.waitFor_alert();
	    tool.closeAlert_andGetItsText();
	    driver.findElement(By.linkText("보낸주소결과")).click();
	    
	    // 전송할 주소록으로 등록 됐는지 확인
	    tool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='*new_group']")));
	    
	    driver.findElement(By.name("no")).click();
	    driver.findElement(By.id("funSendGroupDel")).click();
	    tool.waitFor_alert();
	    assertEquals("보낸 주소록을 삭제하였습니다.", tool.closeAlert_andGetItsText());
	  }

	public void addAddress_InManagement() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("주소록")).click();
	    driver.findElement(By.linkText("주소록관리")).click();
	    new Select(driver.findElement(By.name("addressGroupNo"))).selectByVisibleText("new_group");
	    driver.findElement(By.id("addr_name")).clear();
	    driver.findElement(By.id("addr_name")).sendKeys("안승찬");
	    driver.findElement(By.id("addr_hp")).clear();
	    driver.findElement(By.id("addr_hp")).sendKeys("01029591783");
	    driver.findElement(By.id("addr_tel")).clear();
	    driver.findElement(By.id("addr_tel")).sendKeys("0322132660");
	    driver.findElement(By.id("addr_fax")).clear();
	    driver.findElement(By.id("addr_fax")).sendKeys("0322132660");
	    driver.findElement(By.id("addr_email")).clear();
	    driver.findElement(By.id("addr_email")).sendKeys("issea1015@gmail.com");
	    driver.findElement(By.name("memo")).clear();
	    driver.findElement(By.name("memo")).sendKeys("test");
	    driver.findElement(By.id("memberCreate")).click();
	    tool.waitFor_alert();
	    assertEquals("등록하였습니다.", tool.closeAlert_andGetItsText());
	    
	    // wait (화면 밝기 - 회색 -> 흰)
	    tool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='그룹관리']")));
	  }
	
	public void printAddress_Preshow() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("주소록")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'인쇄/다운로드')])[2]")).click();
	    new Select(driver.findElement(By.id("funPrintGroupNo"))).selectByVisibleText("new_group");
	    driver.findElement(By.id("funPrintPreview")).click();
	    tool.goTo_PopUp();
	    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | printWindow | 30000]]
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=printWindow | ]]
	    driver.findElement(By.linkText("닫기")).click();
	    tool.goTo_main();
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    driver.findElement(By.id("p_hp")).click();
	    driver.findElement(By.id("funPrintPreview")).click();
	    tool.goTo_PopUp();
	    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | printWindow | 30000]]
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=printWindow | ]]
	    driver.findElement(By.linkText("닫기")).click();
	    tool.goTo_main();
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    driver.findElement(By.id("p_tel")).click();
	    driver.findElement(By.id("fax")).click();
	    driver.findElement(By.id("p_email")).click();
	    driver.findElement(By.id("funPrintPreview")).click();
	    tool.goTo_PopUp();
	    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | printWindow | 30000]]
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=printWindow | ]]
	    driver.findElement(By.linkText("닫기")).click();
	    tool.goTo_main();
	  }

}
