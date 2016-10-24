package com.daou.chasedae.web_test.prosms;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.daou.chasedae.web_test.common.Category;
import com.daou.chasedae.web_test.common.Tool;
import com.daou.chasedae.web_test.common.Variables_User;
import com.relevantcodes.extentreports.ExtentTest;

public class Member extends Category {
	
	public Member(WebDriver driver, String baseUrl, Tool tool, ExtentTest logger) {
		super("Member", driver, baseUrl, tool, logger);
	}

	public void login(String id, String pw) throws Exception {
		driver.get(baseUrl + "/");
		tool.wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("userId"))));
		tool.wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("userPwd"))));
		driver.findElement(By.id("userId")).clear();
		driver.findElement(By.id("userId")).sendKeys(id);
		driver.findElement(By.id("userPwd")).clear();
		driver.findElement(By.id("userPwd")).sendKeys(pw);
		// 비밀번호 입력 안될 시 사용
		/*
		while(true)
		{
			driver.findElement(By.id("userPwd")).sendKeys("vnfms2357!");
			if (ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.id("userPwd")), "value").apply(driver))
				break;
		}
		 */
		tool.wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.id("userPwd")), "value"));
		driver.findElement(By.id("funfunBtnLogin")).click();
		tool.wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("btn_logout"))));
		Thread.sleep(2000);
	}

	public void logout() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("로그아웃")).click();
	}

	public void findID_ByMobile() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("아이디 찾기")).click();
		driver.findElement(By.id("name1")).clear();
		driver.findElement(By.id("name1")).sendKeys("안승찬");
		driver.findElement(By.name("mobile_middle")).clear();
		driver.findElement(By.name("mobile_middle")).sendKeys("2959");
		driver.findElement(By.name("mobile_end")).clear();
		driver.findElement(By.name("mobile_end")).sendKeys("1783");
		driver.findElement(By.id("findIdBtn")).click();
		driver.findElement(By.linkText("‘ * ’ 부분 확인하기")).click();
		driver.findElement(By.linkText("휴대폰으로 받기")).click();
		tool.waitFor_alert();
		assertEquals("선택하신 수단으로 아이디를 전달하였습니다.", tool.closeAlert_andGetItsText());
	}

	public void findID_ByEmail() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("아이디 찾기")).click();
		driver.findElement(By.id("email_sel")).click();
		driver.findElement(By.id("name2")).clear();
		driver.findElement(By.id("name2")).sendKeys("안승찬");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("issea1015");
		driver.findElement(By.name("emailDomain")).clear();
		driver.findElement(By.name("emailDomain")).sendKeys("gmail.com");
		driver.findElement(By.id("findIdBtn")).click();
		driver.findElement(By.linkText("‘ * ’ 부분 확인하기")).click();
		driver.findElement(By.linkText("이메일로 받기")).click();
		tool.waitFor_alert();
		assertEquals("선택하신 수단으로 아이디를 전달하였습니다.", tool.closeAlert_andGetItsText());
	}

	public void findID_ByIdentity() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("아이디 찾기")).click();
		driver.findElement(By.linkText("본인 인증 수단으로 찾기")).click();
		driver.findElement(By.id("kmcRequest")).click();
		tool.goTo_PopUp();
		driver.findElement(By.cssSelector("li.kt > a")).click();
		driver.findElement(By.linkText("취소")).click();
		tool.goTo_main();
	}

	public void findPW_ByMobile() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("비밀번호 찾기")).click();
		driver.findElement(By.id("id1")).clear();
		driver.findElement(By.id("id1")).sendKeys("issea1015");
		driver.findElement(By.id("name1")).clear();
		driver.findElement(By.id("name1")).sendKeys("안승찬");
		driver.findElement(By.name("mobile_middle")).clear();
		driver.findElement(By.name("mobile_middle")).sendKeys("2959");
		driver.findElement(By.name("mobile_end")).clear();
		driver.findElement(By.name("mobile_end")).sendKeys("1783");
		driver.findElement(By.id("findPasswordBtn")).click();
		driver.findElement(By.id("requestAuthCode")).click();
		driver.findElement(By.id("requestBtn")).click();
		tool.waitFor_alert();
		assertEquals("인증번호 입력", tool.closeAlert_andGetItsText());
		driver.findElement(By.linkText("뒤로")).click();
	}

	public void findPW_ByEmail() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("비밀번호 찾기")).click();
		driver.findElement(By.id("email_sel")).click();
		driver.findElement(By.id("id2")).clear();
		driver.findElement(By.id("id2")).sendKeys("issea1015");
		driver.findElement(By.id("name2")).clear();
		driver.findElement(By.id("name2")).sendKeys("안승찬");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("issea1015");
		driver.findElement(By.name("emailDomain")).clear();
		driver.findElement(By.name("emailDomain")).sendKeys("gmail.com");
		driver.findElement(By.id("findPasswordBtn")).click();
		driver.findElement(By.id("requestAuthCodeByEmail")).click();
		tool.waitFor_alert();
		assertEquals("인증메일을 발송했습니다.\n메일로 발송된 주소를 통해 접속하시면 비밀번호를 새로 설정하실 수 있습니다.", tool.closeAlert_andGetItsText());
	}

	public void findPW_ByIdentity() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("비밀번호 찾기")).click();
		driver.findElement(By.linkText("본인 인증 수단으로 재설정")).click();
		driver.findElement(By.id("id_sel3")).clear();
		driver.findElement(By.id("id_sel3")).sendKeys("issea1015");
		driver.findElement(By.id("funRequestKmcParamByPassword")).click();
		tool.goTo_PopUp();
		driver.findElement(By.cssSelector("li.kt > a")).click();
		driver.findElement(By.linkText("취소")).click();
		tool.goTo_main();
	}

	public void editUserInfo() throws Exception {
		logger.debug("Member - editUserInfo() : start"); 
		
		driver.get(baseUrl + "/user/edit");
		//driver.findElement(By.cssSelector("img[alt=\"AD뿌리오\"]")).click();
		//driver.findElement(By.linkText("마이페이지")).click();
		driver.findElement(By.id("info_pw")).clear();
		driver.findElement(By.id("info_pw")).sendKeys(Variables_User.pw);
		driver.findElement(By.id("confirmBtn")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("1");
		
		tool.observe_click(By.id("editBtn"));
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("");
		
		tool.observe_click(By.id("editBtn"));
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("issea1015@gmail.com");
		
		tool.observe_click(By.id("editBtn"));
		
		driver.findElement(By.id("address2")).clear();
		driver.findElement(By.id("address2")).sendKeys("ㅇㅇ");
		
		tool.observe_click(By.id("editBtn"));
		
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys("0");
		
		tool.observe_click(By.id("editBtn"));
		
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys("01011111111");
		
		tool.observe_click(By.id("editBtn"));
		
//		driver.findElement(By.id("phone")).clear();
//		driver.findElement(By.id("phone")).sendKeys("010-1111-1111");
//		
//		tool.observe_click(By.id("editBtn"));
//		
//		driver.findElement(By.id("phone")).clear();
//		driver.findElement(By.id("phone")).sendKeys("0321111111");
//		
//		tool.observe_click(By.id("editBtn"));
//		
//		driver.findElement(By.id("fax")).clear();
//		driver.findElement(By.id("fax")).sendKeys("0321111111");
//		
//		tool.observe_click(By.id("editBtn"));
		
		driver.findElement(By.name("companyName")).clear();
		driver.findElement(By.name("companyName")).sendKeys("다우");
		driver.findElement(By.name("emailReceiveType")).click();
		
		tool.observe_click(By.id("editBtn"));
		
		driver.findElement(By.linkText("취소")).click();
		driver.findElement(By.id("info_pw")).clear();
		driver.findElement(By.id("info_pw")).sendKeys(Variables_User.pw);
		driver.findElement(By.id("confirmBtn")).click();
		driver.findElement(By.name("companyName")).clear();
		driver.findElement(By.name("companyName")).sendKeys("");
		driver.findElement(By.id("fax")).clear();
		driver.findElement(By.id("fax")).sendKeys("");
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys("");
		driver.findElement(By.xpath("(//input[@name='emailReceiveType'])[2]")).click();
		driver.findElement(By.id("address2")).clear();
		driver.findElement(By.id("address2")).sendKeys("");
		driver.findElement(By.id("address1")).clear();
		driver.findElement(By.id("address1")).sendKeys("");
		driver.findElement(By.id("zipCode")).clear();
		driver.findElement(By.id("zipCode")).sendKeys("");
		driver.findElement(By.id("mes_sendname")).clear();
		driver.findElement(By.id("mes_sendname")).sendKeys("");
		
		tool.observe_click(By.id("editBtn"));
		
		logger.debug("Member - editUserInfo() : end"); 
	}

	public void editPW(String new_pw) throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("마이페이지")).click();
		driver.findElement(By.linkText("비밀번호변경")).click();
		driver.findElement(By.id("now_pw")).clear();
		driver.findElement(By.id("now_pw")).sendKeys("vnfms2357!");
		driver.findElement(By.id("inputPassword")).clear();
		driver.findElement(By.id("inputPassword")).sendKeys(new_pw);
		driver.findElement(By.id("new_pw_entry")).clear();
		driver.findElement(By.id("new_pw_entry")).sendKeys(new_pw);
		driver.findElement(By.id("updateBtn")).click();
		tool.waitFor_alert();
		assertEquals("정보를 수정 하였습니다.", tool.closeAlert_andGetItsText());
	}

	public void registerSendNumber() throws Exception {
		driver.get(baseUrl + "/user/sendnumber/sms/list");
		driver.findElement(By.linkText("발신번호사전등록")).click();
		driver.findElement(By.id("funfunSendNumber")).clear();
		driver.findElement(By.id("funfunSendNumber")).sendKeys("01011111111");
		driver.findElement(By.id("funfunBtnAskDevice")).click();
		tool.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#funfunSelectDeviceFile > input[name=\"sendNumberMainDto.device\"]")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#funfunSelectDeviceFile > input[name=\"sendNumberMainDto.device\"]")).click();
		driver.findElement(By.name("uploadFile")).clear();
		driver.findElement(By.name("uploadFile")).sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
		driver.findElement(By.linkText("각 통신사 고객센터 정보 보기")).click();
		driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.name("funfunAuthFileRequestForm.mobile")).clear();
		driver.findElement(By.name("funfunAuthFileRequestForm.mobile")).sendKeys("01011111111");
		driver.findElement(By.name("funfunAuthFileRequestForm.memo")).clear();
		driver.findElement(By.name("funfunAuthFileRequestForm.memo")).sendKeys("1");
		driver.findElement(By.id("funfunBtnRegisterFile")).click();
		tool.waitFor_alert();
		assertEquals("서류인증이 신청되었습니다.", tool.closeAlert_andGetItsText());
		Thread.sleep(2000);
		driver.findElement(By.name("btnDelete")).click();
		tool.waitFor_alert();
		assertTrue(tool.closeAlert_andGetItsText().matches("^01011111111 번호를 삭제하시겠습니까[\\s\\S]$"));
		tool.waitFor_alert();
		assertEquals("삭제 되었습니다.", tool.closeAlert_andGetItsText());
		driver.findElement(By.id("funfunSendNumberSearch")).clear();
		driver.findElement(By.id("funfunSendNumberSearch")).sendKeys("01011111111");
		driver.findElement(By.id("funfunBtnSendNumberSearch")).click();
		driver.findElement(By.id("funfunSendNumberSearch")).clear();
		driver.findElement(By.id("funfunSendNumberSearch")).sendKeys("01029591783");
		driver.findElement(By.id("funfunBtnSendNumberSearch")).click();
		driver.findElement(By.linkText("전체리스트 보기")).click();
	}

	public void pay_ByMobile() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("결제관리")).click();
		driver.findElement(By.id("saved_money")).clear();
		driver.findElement(By.id("saved_money")).sendKeys("1");
		driver.findElement(By.id("pay_cellphone")).click();
		driver.findElement(By.id("btnPayment")).click();
		tool.waitFor_alert();
		assertEquals("1,000원 이상의 금액에 이용하실 수 있습니다 ", tool.closeAlert_andGetItsText());
		driver.findElement(By.id("saved_money")).clear();
		driver.findElement(By.id("saved_money")).sendKeys("1000");
		driver.findElement(By.id("btnPayment")).click();

		tool.goTo_PopUp();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | DAOUPAY_WINDOW | 30000]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=DAOUPAY_WINDOW | ]]
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[2]/a")).click();

		driver.switchTo().frame(driver.findElement(By.id("view")));
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | view | ]]
		driver.findElement(By.id("DstAddr1")).clear();
		driver.findElement(By.id("DstAddr1")).sendKeys("2959");
		driver.findElement(By.id("DstAddr2")).clear();
		driver.findElement(By.id("DstAddr2")).sendKeys("1783");
		driver.findElement(By.id("Iden0")).clear();
		driver.findElement(By.id("Iden0")).sendKeys("921015");
		driver.findElement(By.id("Iden1")).clear();
		driver.findElement(By.id("Iden1")).sendKeys("1");
		driver.findElement(By.id("chk00")).click();
		driver.findElement(By.cssSelector("p > label")).click();
		driver.findElement(By.cssSelector("img[alt=\"다음\"]")).click();
		tool.waitFor_alert();
		assertEquals("이용약관을 읽고 동의하셔야 합니다.", tool.closeAlert_andGetItsText());
		driver.findElement(By.cssSelector("img[alt=\"취소\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"확인\"]")).click();
		tool.goTo_main();
	}

	public void pay_ByBank() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("결제관리")).click();
		driver.findElement(By.id("saved_money")).clear();
		driver.findElement(By.id("saved_money")).sendKeys("1000");
		driver.findElement(By.id("pay_account")).click();
		driver.findElement(By.id("btnPayment")).click();
		tool.goTo_PopUp();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | DAOUPAY_WINDOW | 30000]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=DAOUPAY_WINDOW | ]]
		Thread.sleep(5000);
		driver.findElement(By.id("assentAll")).click();
		driver.findElement(By.linkText("다음")).click();
		driver.findElement(By.cssSelector("#LGD_NEXT > a")).click();
		driver.close();
		tool.goTo_main();
	}

	public void bankBook() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("결제관리")).click();
		driver.findElement(By.cssSelector("ul.menu_low > li > a")).click();
		driver.findElement(By.id("calendarStart")).click();
		driver.findElement(By.linkText("3")).click();
		driver.findElement(By.id("calendarEnd")).click();
		driver.findElement(By.linkText("4")).click();
		driver.findElement(By.id("btnPaymentHistorySearch")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'월간사용 명세서 조회')])[2]")).click();
		driver.findElement(By.id("btnPaymentHistorySearch")).click();
	}
}