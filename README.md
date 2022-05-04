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
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">

## Back-End
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring data jpa-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">


## DevOps
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">  <img src="https://img.shields.io/badge/amazon aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">  <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black">  <img src="https://img.shields.io/badge/Travis CI-3EAAAF?style=for-the-badge&logo=Travis CI&logoColor=black">


# 4. ERD 설계
![image](https://user-images.githubusercontent.com/81150979/165895897-538caebb-f00a-4ebd-8b4e-70f9e9e33970.png)

# 5. 핵심 기능


# 6. 트러블 슈팅
## Done
### 1. 회원 테이블의 PK를 String 타입에서 Long 타입으로 변경
- 회원 정보 테이블의 PK를 String 타입으로 지정시 발생하는 문제는 많습니다.
> 첫번째. 차후에 회원 탈퇴 후 개인정보는 없어져도 보유해야하는 DB 데이터가 있는데, 이때 탈퇴한 회원이 동일한 아이디 혹은 이메일로 재가입하는 경우 문제의 소지가 있다.<br>
> 두번째. PK는 다른 테이블에서도 쉽게 참조할 수 있어야 하는데, 정수가 아닌 String을 PK로 걸면 여러 테이블의 인덱스 용량이 늘어나는 것은 물론이고 회원의 메일 주소가 변경될 경우 여러 테이블을 업데이트해야 하는 문제가 생긴다.
- PK를 Long타입으로 변경하고 name와 email을 Unique Key로 지정하여 문제를 해결하였습니다.
### 2. Querydsl을 활용하여 검색 기능 프로그래밍을 단순화
- 다양한 검색 조건을 처리하려면 많은 쿼리를 짜야 합니다.
- 쿼리는 프로그래밍 언어와 달리 동적으로 변경할 수 없기 때문에, 결국 검색 조건이 10개라면 10개의 쿼리를 만들어야 하는 비효율적인 코드가 늘어나게 됩니다.
- Querydsl을 이용하여, 파라미터값에 따라 동적으로 쿼리를 변경해서 수행하는 메소드를 만들어 프로그래밍을 단순화 했습니다.
### 3. TagService의 불필요한 참조
- 가장 인기있는 태그를 찾는 로직(랭킹시스템)이 WriteUp 에서 한번의 쿼리, Tag에서 한번의 쿼리 를 수행합니다.
- 이는 WriteUp 엔티티가 Tag 엔티티를 참조하고 있기 때문에 발생합니다.
- TagRepository에서, join on 을 이용한 쿼리로 List(tno:count) 쌍을 얻는 방법으로 불필요한 참조 문제를 해결했습니다.
### 4. .gitignore 관리
- .properties 파일에는 민감한 정보가 들어갑니다
- application-oauth.properties 파일에는 소셜기능의 인가와 관련된 정보가 들어가며, application-real-db.properties 파일에는 RDS에 접속하기 위해 필요한 정보가 들어갑니다.
- 처음 프로젝트를 생성하고 Github에 push했을때 .gitignore 파일에 위의 두 .properties 파일을 등록하지 않았습니다.
- 이는 보안 문제로 이어질 수 있기 때문에 나중에 다시 .gitignore 파일에 등록하였지만 커밋 내역에 코드가 남아있어서 되돌릴 수 없는 상황이었습니다.
- 이를 해결하기 위해 보안 키를 재발급받고, 새로운 저장소를 만들어 코드를 다시 Github에 push 하였습니다.

## TODO
### 1. Tag 카테고리를 column으로 두고, 문자열로 구분한 점
- Tag의 카테고리 테이블을 따로 두지 않고, Tag 테이블의 컬럼에 카테고리를 문자열로 두어 구분합니다.
- 지금은 각 카테고리마다 중복되는 태그가 없고, 태그의 갯수도 적기때문에 문제가 발생하지 않지만 나중에 중복 카테고리('일상' 카테고리에도 '여행', '취미'카테고리에도 '여행')가 생긴다면 문제가 발생합니다.
- 태그의 카테고리를 따로 테이블로 두어 관리한다면 이 문제를 해결 가능합니다. 뿐만 아니라 계층형으로 카테고리를 나눌 수도 있습니다.
### 2. 모달 로그인 성공후, 원래 요청했던 페이지로 갈 수 없는 문제
- 커스텀 로그인 페이지를 만들어서 로그인을 처리하는 경우, LoginSuccesseHandler에서 RequestCache를 이용하면 로그인 이후 원래 요청했던 페이지로 Redirect시킬 수 있습니다.
- 하지만 지금 배경지식으로는 모달 로그인후 홈페이지로 바로가는 기능 구현만 가능합니다.
- 이 문제의 명쾌한 솔루션은 아직 찾지 못했습니다.
### 3. 독립적이지 않은 서버와 클라이언트
- 시큐리티 기능 구현의 난이도 때문에, JWT를 활용한 REST 방식이 아닌 세션방식을 이용한 MVC 방식으로 프로그램을 만들었습니다.
- 이는 서버 개발과 프론트엔드 개발이 분리되어 있지 않은 방식입니다.
- 후에 React.js나 Vue.js 와 같은 프론트엔드 프레임워크로 웹 페이지를 구성하고, 서버에서는 REST Controller를 이용하여 API만을 제공하는 방식으로 이를 해결합니다.
