# 📗Tag Diary
> 쉽게 쓰고 함께 공유해요<br>
[데모 서비스 링크](http://ec2-13-125-116-130.ap-northeast-2.compute.amazonaws.com:8080/)

# 1. 소개

😆<strong>손쉽게 로그인하고 다른사람들과 일상을 나눠요</strong>

![손쉽게 로그인하고 일상을 나눠요](https://user-images.githubusercontent.com/81150979/165908503-d48aaeed-524b-4068-8ca2-cddf5e9e448e.gif)

✒<strong>태그를 골라서 간단하게 적어요</strong>

![쉽게 사용해요](https://user-images.githubusercontent.com/81150979/165909210-75192e0e-8187-4b27-84d0-913373bb73f7.gif)



# 2. 제작 기간 & 참여 인원
* 2022. 4.13 ~ 4.28
* 개인 프로젝트

# 3. 기술 스택
## Front-End  
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
<img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">

## Back-End
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring data jpa-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> 
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=spring Boot&logoColor=white">
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">


## DevOps
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"> <img src="https://img.shields.io/badge/amazon aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> 
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">


# 4. ERD 설계
![image](https://user-images.githubusercontent.com/81150979/165895897-538caebb-f00a-4ebd-8b4e-70f9e9e33970.png)

# 5. 개선한 문제점
## 1. 회원 테이블의 PK를 String 타입에서 Long 타입으로 변경
- 회원 정보 테이블의 PK를 String 타입으로 지정시 발생하는 문제는 많습니다.(회원탈퇴, 퍼포먼스 문제 등)
- PK를 Long타입으로 변경하고 name와 email을 Unique Key로 지정하여 문제를 해결하였습니다.
## 2. Querydsl을 활용하여 검색 기능 프로그래밍을 단순화
- 다양한 검색 조건을 처리하려면 많은 쿼리를 짜야 합니다.
- 쿼리는 프로그래밍 언어와 달리 동적으로 변경할 수 없기 때문에, 결국 검색 조건이 10개라면 10개의 쿼리를 만들어야 합니다.
- Querydsl을 이용하여, 파라미터값에 따라 동적으로 쿼리를 변경해서 수행하는 메소드를 만들어 프로그래밍을 단순화 했습니다.

# 6. 앞으로 개선해야 할 점
## 1. TagService의 불필요한 참조
- 가장 인기있는 태그를 찾는 로직이 WriteUp 에서 한번의 쿼리, Tag에서 한번의 쿼리 를 수행합니다.
- 이는 WriteUp 엔티티가 Tag 엔티티를 참조하고 있기 때문에 발생합니다.
- Tag가 WriteUp을 @ManyToOne으로 참조하게 한다면 결과적으로 Tag에서만 쿼리를 수행하도록 문제를 해결할 수 있습니다.
## 2. Tag 카테고리를 column으로 두고, 문자열로 구분한 점
- Tag의 카테고리 테이블을 따로 두지 않고, Tag 테이블의 컬럼에 카테고리를 문자열로 두어 구분합니다.
- 지금은 각 카테고리마다 중복되는 태그가 없고, 태그의 갯수도 적기때문에 문제가 발생하지 않지만 나중에 중복 카테고리('일상' 카테고리에도 '여행', '취미'카테고리에도 '여행')가 생긴다면 문제가 발생합니다.
- 태그의 카테고리를 따로 테이블로 두어 관리한다면 이 문제를 해결 가능합니다. 뿐만 아니라 계층형으로 카테고리를 나눌 수도 있습니다.
## 3. 모달 로그인 성공후, 원래 요청했던 페이지로 갈 수 없는 문제
- 커스텀 로그인 페이지를 만들어서 로그인을 처리하는 경우, LoginSuccesseHandler에서 RequestCache를 이용하면 로그인 이후 원래 요청했던 페이지로 Redirect시킬 수 있습니다.
- 하지만 지금 배경지식으로는 모달 로그인후 홈페이지로 바로가는 기능 구현만 가능합니다.
- 이 문제의 명쾌한 솔루션은 아직 찾지 못했습니다.
## 4. 독립적이지 않은 서버와 클라이언트
- 시큐리티 기능 구현의 난이도 때문에, JWT를 활용한 REST 방식이 아닌 세션방식을 이용한 MVC 방식으로 프로그램을 만들었습니다.
- 이는 서버 개발과 프론트엔드 개발이 분리되어 있지 않은 방식입니다.
- 후에 React.js나 Vue.js 와 같은 프론트엔드 프레임워크로 웹 페이지를 구성하고, 서버에서는 REST Controller를 이용하여 API만을 제공하는 방식으로 이를 해결합니다.
