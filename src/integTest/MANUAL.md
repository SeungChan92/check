## testCase 추가하기

**1번. 테스트 케이스 sheet에서 자동화 할 testCase를 선택한다.**

**2번. 선택한 테스트가 진행되는 동안 거치는 page(url)들을 확인한다.**

**3번. 2번에서 확인한 page들이 모두 page object로 구현 되어있는지 확인한다.**

> case1. 구현되어 있지 않은 page를 위한 [page class를 추가한다](#page-class-추가하기).

> case2. 모두 구현되었다면 다음 단계를 진행한다.

**4번. testCase를 추가할 suite가 마련되어 있는지 확인하다.**

> suite 이름 : suite.*TestSheet_Depth0*.*TestSheet_Depth1*.**...**.Suite

> case1. suite가 없다면, [suite class를 추가한다](#suite-class-추가하기).

> case2. suite가 있다면, [suite에 testCase를 구현한다](#suite-class-추가하기).

<br>

## page class 추가하기

**1번. page package 아래에 새로운 하위 package와 class를 추가한다.**
> naming rule : page.*PAGE*.*URL*.Page
>> ex : page.fax.mycover.saveView.Page

**2번. public static void goToPage() 구현**
> Tool.goToPage(String path) 사용

**3번. WebElement 나열**
> 형식 : 

```java
@FindBy(id = "uploadFileTop") // id 대신 다른 attribute 사용 가능
private WebElement input_file_uploadFileTop;
```
	
**4번. Suite에서 사용할 함수 정의**
> 예 :

```java
public Page select_upperLogo() {
	this.input_file_uploadFileTop.sendKeys(Config.get_path("logo"));
	return this;
}
```
	
> 특이사항 :
> + 함수 실행에 의해 페이지 이동이 있을 시, 해당 page object를 return
> + 페이지 이동이 없을 경우, return this

<br>

## suite class 추가하기

**1번. suite package 아래에 새로운 하위 package와 class를 추가한다.**
> naming rule : suite.*TestSheet_Depth0*.*TestSheet_Depth1*.**...**.Suite
>> ex : suite.member.join.certification.Suite

**2번. private Page firstPage = null 선언**

**3번. @Before public void goToFirstPage() 구현**
+ FirstPage.goToPage() 이용
+ firstPage 초기화

> 예 :

```java
this.firstPage = PageFactory.initElements(driver
		, page.FirstPage.class);
```

**4번. test case 구현**
> 예 :

```java
@Test
public void register_lowerLogo() {
	this.page_fax_mycover_saveView.delete_lowerLogo_if_exist();
	this.page_fax_mycover_saveView.select_lowerLogo();
	this.page_fax_mycover_saveView.clickButton_save();
}
```