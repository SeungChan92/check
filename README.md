# Web Test Automation

By SeungChan An (com.daou.chasedae)

This project has purpose which makes ordinary web test automated.

### Citing

If you find Web Test Automation useful in your project, please consider citing:

    Author = SeungChan An
    Title = Web Test Automation
    Year = 2016

### Contents
1. [Summary] (#summary)
1. [Development Environment](#development-environment)
2. [Result Reporting] (#result-reporting)
3. [Installation](#installation)
4. [Run](#run)
5. [Usage](#usage)
6. [Reference](#reference)

### Summary

+ 여러 서비스에서 공통으로 뺄 수 있는 부분을 정의하고 자동으로 인식할 수 있는 시스템
	+ service dependent한 부분은 따로 기록해주어 테스터가 알 수 있도록한다.
	+ 완자동 인식은 아니더라도, config 파일을 이용해서 인식을 도와줄 수 있도록한다.

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