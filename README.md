# Web Test Automation

By SeungChan An (com.daou.chasedae)

This project has purpose which makes ordinary web test automated.

### Citing

If you find Web Test Automation useful in your project, please consider citing:

    Author = Seung-Chan Ahn
    Title = Web Test Automation
    Year = 2016

### Contents
1. [Summary] (#summary)
2. [Operating Method] (#operation-method)
2. [Todo] (#todo)
3. [Issue] (#issue)
4. [Development Environment](#development-environment)
5. [Result Reporting] (#result-reporting)
6. [Installation](#installation)
7. [Run](#run)
8. [Usage](#usage)
9. [Reference](#reference)

### Summary

+ 여러 서비스에서 공통으로 뺄 수 있는 부분을 정의하고 자동으로 인식할 수 있는 시스템
	+ service dependent한 부분은 따로 기록해주어 테스터가 알 수 있도록한다.
	+ 완자동 인식은 아니더라도, config 파일을 이용해서 인식을 도와줄 수 있도록한다.

### Operating Method

1. crawl pages
2. build testcode
3. run test
4. make report
	
### Todo

+ Test1
	1. [by crawler + html parser] 각 페이지의 input tag 목록 정리하기
		+ 모든 페이지에 대해 [xpath] //input[@type='text'] 뽑아내기
			+ 중복 페이지 거르기
				+ [maybe] crawler's tour method filters duplication.
		+ page tree 만들기
	2. [by person] 각 input tag에 대한 올바른 value 선택 
	3. [by selenium] many Values per [input] tag에 대한 alert 메세지 축적(기록)
		+ [lator] 서버코드와 동기화
	
### Issue

+ 같은 페이지에서 먼저 검사하는 input 폼에 잘못된 값이 입력되어 있을 경우, 다음 input 폼을 검사하지 않는다.
	+ 테스터가 (테스트할 가치가 있는 values set과) 올바른 value를 알려주어야한다.
+ Crawler
	+ crawler4j
		+ login
			+ It success with invalid id, pw
			+ [solution] use URL to /LoginAct
			+ [need] avoid /logout
	
### Development Environment

1. OS : windows7
2. Language : java

### Result Reporting

+ purpose
	+ exception이 발생한 code line을 찾을 수 있도록 한다.
		+ e.stactTrace
	+ 어떤 테스트가 이루어 졌는지를 기록한다.
		+ 성공/실패 여부도 기록한다.
		+ 테스트 기록의 (최소)단위를 정해야한다.
+ 테스트 기록의 최소단위 : 함수 or 명령어 한 라인
	+ 명령어 한라인마다 log를 찍는 건 가독성이 떨어지므로, 함수단위로 로그를 찍는다.
+ 계층구조
	+ test/browser/category/method

### Installation

### Run

### Usage

### Reference

+ Selenium
+ ExtentReports
+ https://github.com/yasserg/crawler4j
+ jsoup