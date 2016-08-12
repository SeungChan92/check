### 개발목적 (depth 1)
+ 자동화가능한 테스트를 자동화한다.
	+ 자동화 가능한 테스트란??
		+ 정의하기 어렵다.
+ 테스트 결과를 가독성 있게 출력한다.
+ 추가로 필요한 테스트를 분석하고 알려준다.

### 개발목표 (depth 2)
1. [from] 자동화 가능한 테스트를 자동화한다.
	+ input validation check
		1. Input에 값들을 넣어본다.
		2. Submit 한다.
		3. Alert message가 적합한지 확인한다.
2. [from] 테스트 결과를 가독성 있게 출력한다.
	+ [from] input validation check
		+ 테스트로 수집된 alert message 출력
		+ 기대했던 alert message 출력
		+ 두 message의 일치여부 출력

### 개발 목표 (depth 3)
1. [from] input에 값들을 넣어본다.
	+ 어떤 input이 있는지 파악한다.
	+ 어떤 값들을 넣어볼지 결정한다.
2. [from] submit한다.
	+ 해당 input이 속한 form을 전송하는 button을 찾는다.
3. [from] alert message가 적합한지 확인한다.
	+ 기대하는 message를 각 입력값 별로 유지한다.
	+ 같은 form에 있는 다른 input을 테스트하기 위해서, 올바른 입력 값을 유지한다.

### 개발 방법 (depth 4)
+ [from] 어떤 input이 있는 지 파악한다.
	+ crawler 사용
	+ 관련 자료구조 : Page
+ [from] 어떤 값들을 넣어볼 지 결정한다.
	+ 테스터에게 물어보고 기억한다.
		1. input tag의 id, name을 보여준다.
		2. 입력 값 유형을 복수선택 받는다.
		3. DB에 update한다.
+ [from] 해당 input이 속한 form을 전송하는 button을 찾는다.
	1. page에 있는 모든 form을 찾는다.
		+ [사용] org.jsoup.nodes.Document
	2. form 하위에 있는 버튼들을 찾는다.
		+ [사용] org.jsoup.nodes.Element
			+ [method] getElementsByTag
	3. 테스터에게 물어보고 기억한다.
		1. 버튼으로 추정되는 tag들의 tagName, attributes를 보여준다.
		2. 선택 받는다.
		3. DB에 update한다.
+ [from] 기대하는 message를 각 입력 값 별로 유지한다.
	+ 테스터에게 물어본다.
		1. 각 페이지 별로 input tag의 id, name, 입력값 유형을 보여준다.
		2. 선택 받는다.
		3. DB에 update한다.
+ [from] 같은 form에 있는 다른 input을 테스트하기 위해서, 올바른 입력값을 유진한다.
	+ 테스터에게 제공할 정보 : page URL, input tag [@id, name]
	+ DB table : inputTag