# 차세대 ASP UI Test

By Seung-Chan An (com.daou.chasedae)

This project has purpose which makes ordinary web test automated.

## Citing

If you find 차세대 ASP UI Test useful in your project, please consider citing:

    Author = Seung-Chan Ahn
    Title = 차세대 ASP UI Test
    Year = 2016

## Contents
1. [Summary](#summary)
4. [Development Environment](#development-environment)
5. [Test Result Reporting](#test-result-reporting)
6. [Installation](#installation)
7. [Run](#run)
8. [Usage](#usage)
9. [Data Structure](#data-structure)
10. [Reference](#reference)

## Summary
1. 차세대-ASP-UI-테스트-Sheet에 나열된 testcase들을 자동화하여 프로젝트에 포함시킨다.
2. 자동화된 UI-테스트는 gradle task로 실행된다.
3. 차세대-ASP-프로젝트를 배포할 때, UI 테스트를 거친다.
	
## Development Environment
1. OS : windows7
2. Language : java

## Test Result Reporting
1. gradle task 실행 시, JUnit-XML과 JUnit-HTML 형식으로 보고서가 작성된다.
2. SonarQube가 JUnit-XML 형식의 보고서를 읽어서 SonarQube 보고서에 포함시킨다.

## Installation
1. src/IT

## Run

## Usage
### selenium server에 접근하기
#### basic
	1. [on server] run {driver}.exe
		+ [option] server에 접근 허용하는 아이피 기입
	2. [on client] use [class] RemoteWebDriver
#### advance
	keyword : selenium-server, hub, node

## Data Structure

It save crawling result as json format in a file

	{
		pages :
		[
			{
				path : "",
				alertMessages :
				[
					{
						message : "",
						snapshot :
						{
							formTags :
							[
								{
									attributes :
									{
										id : "",
										name : ""
									},
									inputTags :
									[
										{
											attributes :
											{
												id : "",
												name : "",
												value : "",
												type : ""
											}
										}
									],
								}
							]
						}
					}
				],
				formTags :
				[
					{
						attributes :
						{
							id : "",
							name : ""
						},
						submitButton_tagName : "",
						submitButton_id : "",
						submitButton_name : "",
						inputTags :
						[
							{
								attributes :
								{
									type : "",
									id : "",
									name : ""
								},
								valueTypes :
								[
									"", ""
								],
								validValue : ""
								alertMessages :
								[
									{
										valueType : "",
										message : ""
									}
								]
							}
						]
					}
				]
			}
		]
	}

## Reference

+ Selenium
+ ExtentReports
+ https://github.com/yasserg/crawler4j
+ jsoup