# 📚 JSP 예제 모음

JSP 기반의 예제를 모아놓은 학습용 자료입니다.

---

## 📁 07_user-list/ - 회원 목록 조회 (MVC + DB 연동)

**설명:**  
MySQL 데이터베이스에 저장된 사용자 정보를 조회하여 JSP에서 출력하는 MVC 패턴 예제입니다.

**파일 설명:**
- `User.java` : 사용자 정보를 담는 DTO (id, name, email)
- `UserDAO.java` : DB와 연결하여 사용자 목록을 조회하는 DAO 클래스
- `UserServlet.java` : DAO에서 사용자 목록을 받아 JSP에 전달하는 서블릿
- `userList.jsp` : 전달받은 사용자 목록을 테이블 형태로 출력하는 JSP

---

## 📁 08_ajax-json/ - AJAX 요청 + JSON 응답

**설명:**  
JSP에서 JSON 데이터를 반환하고, JavaScript Fetch API를 사용하여 비동기적으로 데이터를 받아오는 예제입니다.

**파일 설명:**
- `ajax.html` : 버튼 클릭 시 AJAX 요청을 보내고, 응답으로 받은 시간 데이터를 출력
- `getTime.jsp` : 서버의 현재 시간을 JSON 형식으로 응답하는 JSP

---

## 📁 09_file-upload/ - 파일 업로드 처리

**설명:**  
사용자가 업로드한 파일을 서버에 저장하는 기능을 구현한 예제입니다. `Apache Commons FileUpload` 라이브러리를 사용합니다.

**파일 설명:**
- `upload.jsp` : 파일을 선택하여 업로드할 수 있는 폼 (multipart/form-data)
- `UploadServlet.java` : 업로드된 파일을 지정된 디렉토리에 저장하는 서블릿

---

## 📁 10_session-login/ - 로그인 세션 관리

**설명:**  
세션을 이용하여 로그인 상태를 유지하고, 로그아웃 시 세션을 종료하는 기본적인 로그인 처리 예제입니다.

**파일 설명:**
- `login.jsp` : 사용자로부터 아이디와 비밀번호를 입력받는 폼
- `loginCheck.jsp` : 입력 정보를 확인하고 세션에 로그인 정보 저장
- `logout.jsp` : 세션을 종료하고 로그인 페이지로 이동

---

> 💡 각 예제는 독립적으로 실행되며, 실제 실행을 위해서는 `web.xml` 설정 및 JDBC 연결 정보(MySQL 등)를 환경에 맞게 수정해 주세요.

