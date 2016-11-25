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
### 1. src/integTest folder를 ASP 프로젝트에 포함시킨다.
### 2. build.gradle 수정
+ add configurations

```gradle
configurations {
	integTestCompile.extendsFrom testCompile
	integTestRuntime.extendsFrom testRuntime
}
```

+ add sourceSets

```gradle
sourceSets {
	integTest {
	   java {
		   srcDirs = ['src/integTest/java']
       }
	   resources {
		   srcDirs = ['src/integTest/resources']
	   }
	}
}
```

+ add task

```gradle
task integTest(type: Test) {
	//dependsOn startApp
	//finalizedBy stopApp
	testClassesDir = sourceSets.integTest.output.classesDir
	classpath = sourceSets.integTest.runtimeClasspath
	//mustRunAfter test
}
```
	
+ config encoding

```gradle
compileIntegTestJava.options.encoding = 'UTF-8'
```	

+ (check repository)

```gradle
repositories {
    jcenter()
}
```
	
+ add dependencies

```gradle
dependencies {
	// https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
	testCompile group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '2.41.0'
	// https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
	testCompile group: 'com.googlecode.json-simple', name: 'json-simple', version: '1.1.1'
	testCompile 'junit:junit:4.12'
}
```

## Usage
### 전체 테스트 케이스를 실행 - gradle 사용
gradle build task-chain에 포함시키기.
### 일부 테스트 케이스를 실행 - eclipse - JUnit runner 사용
#### 설정하기
1. eclisp

#### 실행하기

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