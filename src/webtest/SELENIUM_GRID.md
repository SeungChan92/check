## 참조
https://github.com/SeleniumHQ/selenium/wiki/Grid2

## 설정
#### Json file 사용
+ Hub Config 예시 : https://github.com/SeleniumHQ/selenium/blob/master/java/server/src/org/openqa/grid/common/defaults/DefaultHub.json
+ Node Config 예시 : https://github.com/SeleniumHQ/selenium/blob/master/java/server/src/org/openqa/grid/common/defaults/DefaultNodeWebDriver.json

#### 시스템 환경변수
path += ${webDriver(.exe) 들이 들어 있는 폴더}

## 실행
cmd 창에서 command 사용
+ Hub : "C:\Program Files\Java\jdk1.8.0_112\bin\java" -jar selenium-server-standalone-3.0.1.jar -role hub -hubConfig gridHubConfig.json
+ Node : "C:\Program Files\Java\jdk1.8.0_112\bin\java" -jar selenium-server-standalone-3.0.1.jar -role node -nodeConfig gridNodeConfig.json

## 모니터링
http://${Grid 실행 장비 ip}:4444/grid/console