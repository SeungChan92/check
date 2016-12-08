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
9. [Current Browsers in Window Server](#current-browsers-in-window-server)
9. [Current Drivers in Window Server](#current-drivers-in-window-server)
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
gradle build task-chain에 **integTest** 포함시키기.

<br>

### 일부 테스트 케이스를 실행 - eclipse - JUnit runner 사용
#### 1. 설정하기
> ##### 1. Run Configurations 창 열기
>> 위치 : eclipse 상단 - 플레이 버튼 오른쪽에 있는 아래를 향하는 화살표 클릭

> ##### 2. 왼쪽 리스트에서 JUnit 찾고, 새로운 설정 만들기
>> JUnit 위에서, 우클릭

> ##### 3. 테스트 클래스(Test-Suite) 정하기
> ##### 4. 테스트 매소드(Test-Case) 정하기
> ##### 5. click 'Apply'

#### 2. 실행하기
> ##### 1. Run Configurations 창 열기
>> 위치 : eclipse 상단 - 플레이 버튼 오른쪽에 있는 아래를 향하는 화살표 클릭

> ##### 2. 왼쪽 리스트에서 만들어 둔 JUnit 설정 찾기
> ##### 3. click 'Run'

<br>

### UI Test용 Window 서버 사용
#### basic
> ##### 1. Window 서버에서 WebDriver 실행
>> 실행시, option으로 Window 서버에 접근 허용하는 아이피 기입

> ##### 2. [on client] use [class] RemoteWebDriver
>> 현재 java source에서 설정 가능하고, config file에 설정할 수 있도록 업데이트 필요함.

#### advance
> use [Selenium Grid](SELENIUM_GRID.md)

## Current Browsers in Window Server
+ Chrome 55.0.2883.75 m (이후 버전 일 수도 있음)
+ (x) Internet Explorer 7, 9 : windows 7 에서 지원하지 않음
+ (x) Internet Explorer 10 : installer를 찾기 어려움
+ Internet Explorer 8, 11(서버장비 재시작 필요)
+ Firefox 50.0.2 이후 버전 (이후 버전 일 수도 있음)
+ Safari 5.1.7 이후 버전 (이후 버전 일 수도 있음)

## Current Drivers in Window Server
+ Chrome
+ Internet Explorer

## Reference

+ Selenium