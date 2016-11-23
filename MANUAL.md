## page class 새로 추가하기

**1번. page package 아래에 새로운 하위 package와 class를 추가한다.**
> naming rule : page.*PAGE*.*URL*.Page
>> ex : page.fax.mycover.saveView.Page

**2번. public static void goToPage() 구현**
> Tool.goToPage(String path) 사용

**3번. WebElement 나열**
> 형식 : 

	@FindBy(id = "uploadFileTop") // id 대신 다른 attribute 사용 가능
	private WebElement input_file_uploadFileTop;
	
**4번. Suite에서 사용할 함수 정의**
> 예 :

	public Page select_upperLogo() {
		this.input_file_uploadFileTop.sendKeys(Config.get_path("logo"));
		return this;
	}
	
> 특이사항 :
> + 함수 실행에 의해 페이지 이동이 있을 시, 해당 page object를 return
> + 페이지 이동이 없을 경우, return this

## suite class 새로 추가하기

**1번. suite package 아래에 새로운 하위 package와 class를 추가한다.**
> naming rule : page.*TestSheet_Depth0*.*TestSheet_Depth1*.**...**.Suite
>> ex : suite.member.join.certification.Suite

**2번. private Page firstPage = null 선언**

**3번. @Before public void goToFirstPage() 구현**
+ FirstPage.goToPage() 이용
+ firstPage 초기화

> 예 :

		this.firstPage = PageFactory.initElements(driver
				, page.FirstPage.class);

**4번. test case 구현**
> 예 :

	@Test
	public void register_lowerLogo() {
		this.page_fax_mycover_saveView.delete_lowerLogo_if_exist();
		this.page_fax_mycover_saveView.select_lowerLogo();
		this.page_fax_mycover_saveView.clickButton_save();
	}
