# JSP 학습 노트 📚

JSP(JavaServer Pages)를 학습하며 정리한 노트입니다. 기본 문법부터 실습 예제까지 단계적으로 정리되어 있습니다.

## 📌 목차

1. [JSP란?](#jsp란)
2. [JSP 기본 문법](#jsp-기본-문법)
3. [지시자(Directive)](#지시자directive)
4. [액션 태그](#액션-태그)
5. [폼 처리 및 데이터 전달](#폼-처리-및-데이터-전달)
6. [세션/쿠키 활용](#세션쿠키-활용)
7. [MVC 패턴 예제](#mvc-패턴-예제)
8. [참고 자료](#참고-자료)

---

## JSP란?

- Java Servlet 기반의 웹 페이지 작성 기술
- HTML 내에 Java 코드를 삽입하여 동적인 웹 페이지 생성 가능

---

## JSP 기본 문법

- Scriptlet(스크립트릿) : `<% ... %>`
- Expression(표현식) : `<%= ... %>`
- Declaration(선언) : `<%! ... %>`

[예제 보기](./01_basic-syntax)

---

## 지시자(Directive)

- `page`, `include`, `taglib` 등의 명령어
- ex) `<%@ page language="java" contentType="text/html; charset=UTF-8" %>`

[예제 보기](./02_directives)

---

## 액션 태그

- `<jsp:include>`, `<jsp:useBean>`, `<jsp:setProperty>`, `<jsp:getProperty>` 등
- 자바빈을 이용한 데이터 처리

[예제 보기](./03_action-tags)

---

## 폼 처리 및 데이터 전달

- `request.getParameter()` 사용
- HTML Form과 JSP의 연동

[예제 보기](./04_form-handling)

---

## 세션/쿠키 활용

- 세션 객체: `request.getSession()`
- 쿠키: `Cookie` 클래스 활용

[예제 보기](./05_session-cookie)

---

## MVC 패턴 예제

- JSP + Servlet + JavaBeans
- 로그인 처리 예제 포함

[예제 보기](./06_mvc)


