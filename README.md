명륜당
======
### 🎇앞에 보이는 모든 책의 정보가 내 손 안에🎇
<!-- <img src="https://user-images.githubusercontent.com/90249177/148031177-748fb2dc-1626-42b5-aad4-543357d7d010.png" width="100%" height="60%" title="Pokemon" alt="Pokemon"></img> -->

## Abstract

 ### - 책의 바코드를 인식하여 책에 대한 정보를 제공합니다.</p>
 Step 1: 앱 기능을 이용하기 위해서 로그인/회원가입을 해야 합니다.</p>
 Step 2: 궁금한 책의 바코드를 카메라를 활용하여 스캔합니다.</p>
 Step 3: 책에 대한 정보(사진, 가격, 제목, 작가, 평점, 간단소개 등)을 확인합니다.</p>
 Step 4: 구매 링크를 클리하여 이동 후 책을 구매합니다.</p>

## 명륜당 어플리케이션 사용 예시
<ul>
  <h3>앱 사용 예시입니다! gif를 참고해서 앱을 이용해 주세요!</h3>
 <li>언제나 어디서나 책을 가지고 있다면 쉽게 책의 정보를 확인할 수 있다!</li>
</ul>
<!-- <img src="https://user-images.githubusercontent.com/90249177/148053968-865b9faf-0e07-48c7-9fb2-197bf7127b37.gif" width="270px" height="480px" title="Contact" alt="Contact"></img> -->

## Technology
<ul>
  <li>UI = Android Studio -> Java</li>
  <li>Server = Retrofit -> Java</li>
  <li>Database = MySQL</li>
  <li>Data = Crawling(https://book.naver.com/) -> Javascipt</li>
</ul>

<!-- ## Features
<ul>
 <li><h2>Step 1</h2></li>
 <ul>
  <li>정보를 입력하세요.</li>
 </ul>
</ul> -->

## Database
### - 데이터베이스 구조입니다. 데이터베이스는 MySQL을 이용했습니다.

### Table: users
|userid|userpw|nickname|
|---|---|---|
|KEY|VARCHAR|VARCHAR|


### Table: books
|num|userid|codenum|title|author|price|review|love|imgbook|payone|paytwo|paythree|payfour|aboutbook|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|KEY|VARCHAR|CHAR(13)|VARCHAR|VARCHAR|VARCHAR|VARCHAR|VARCHAR|VARCHAR|VARCHAR|VARCHAR|VARCHAR|VARCHAR|VARCHAR|


## Authority
### - 다음의 권한들을 앱의 구동을 위하여 반드시 허용하여야 합니다.</p>
<ol>
 <li>기기 카메라 엑세스</li>
  <ul>
    <li>[기기 사진 및 동영상 촬영 엑세스] ->  바코드를 스캔할 때 사용합니다.</li>
  </ul>
</ol>



## Contributers

- [윤태양](https://github.com/hotsunchip)
- [김수민](https://github.com/SeanKim37)
