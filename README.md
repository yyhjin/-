# 구현 진행 상황



[TOC]



## Front






## Back
### 소비자(customer)

#### 회원가입

**회원가입 데이터 전달**

```{
  "customerAddr": "서울 어딘가",//소비자 주소
  "customerId": "parkjongsun",//소비자 ID - 소비자 아이디에서는 중복될 수 없는 유일한 값.
  "customerName": "박종선",//소비자 이름
  "customerNickname": "종선쓰",//소비자 닉네임
  "customerPhone": "010-9998-9202",//소비자 핸드폰번호
  "customerPwd": "jongsunjjang"//소비자 비밀번호
}
```

![스크린샷 2022-08-07 18.40.58](README.assets/스크린샷 2022-08-07 18.40.58.png)



**회원가입 응답**

ㅡ 성공시

```
{
  "response": "success",
  "message": "소비자 회원가입을 성공적으로 완료했습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 18.41.41](README.assets/스크린샷 2022-08-07 18.41.41.png)

ㅡ 실패시

```
{
  "response": "error",
  "message": "소비자 회원가입에 실패하였습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 18.52.11](README.assets/스크린샷 2022-08-07 18.52.11.png)



**회원가입 후 SQL에 저장되는 상황**

![스크린샷 2022-08-07 18.43.10](README.assets/스크린샷 2022-08-07 18.43.10.png)



#### 아이디 중복 검사

**아이디 중복 검사 GET method로 url로 검색하고자 하는 아이디를 전달**

customer_id: 소비자 아이디

![스크린샷 2022-08-07 18.54.25](README.assets/스크린샷 2022-08-07 18.54.25.png)



**아이디 중복 검사 결과**

ㅡ 중복인 경우: {"idCheck": false} 반환

![스크린샷 2022-08-07 18.57.02 1](README.assets/스크린샷 2022-08-07 18.57.02 1.png)

ㅡ 중복이 아닌 경우: {"idCheck": true} 반환

![스크린샷 2022-08-07 19.01.08](README.assets/스크린샷 2022-08-07 19.01.08.png)



#### 로그인

**로그인 데이터 전달**

```{
  "password": "parkjongsun", // 잘못된 경우를 예시로 함
  "username": "jongsunjjang1" // 잘못된 경우를 예시로 함
}
```

![스크린샷 2022-08-07 19.02.23](README.assets/스크린샷 2022-08-07 19.02.23.png)



**로그인 응답**

ㅡ 데이터가 틀린 경우

```
{
  "response": "error",
  "message": "로그인에 실패했습니다.",
  "data": "아이디가 틀립니다."
}
```

```
{
  "response": "error",
  "message": "로그인에 실패했습니다.",
  "data": "비밀번호가 틀립니다."
}
```

![스크린샷 2022-08-07 19.05.06](README.assets/스크린샷 2022-08-07 19.05.06.png)

ㅡ 로그인에 성공한 경우

```
{
  "response": "success",
  "message": "로그인에 성공했습니다.",
  "data": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InBhcmtqb25nc3VuIiwiaWF0IjoxNjU5ODY2NzYxLCJleHAiOjE2NTk4NjY3NzF9.IJmJQWHV7c7NQ7iaV_UcMAzhXDJHJf2BquyAy7_qusM"
}
```

![스크린샷 2022-08-07 19.07.54](README.assets/스크린샷 2022-08-07 19.07.54.png)



#### 회원정보 조회 - 개인정보

![스크린샷 2022-08-07 19.09.35](README.assets/스크린샷 2022-08-07 19.09.35.png)



#### 회원정보 수정

**수정할 회원정보 입력**

![스크린샷 2022-08-07 19.21.35](README.assets/스크린샷 2022-08-07 19.21.35.png)



**DB 반영 결과**

![스크린샷 2022-08-07 19.24.35](README.assets/스크린샷 2022-08-07 19.24.35.png)



#### 비밀번호 수정

**데이터 입력**

![스크린샷 2022-08-07 19.38.28](README.assets/스크린샷 2022-08-07 19.38.28.png)

**응답 데이터**

ㅡ 성공시

```
{
  "response": "success",
  "message": "성공적으로 사용자의 비밀번호를 변경했습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 19.39.12](README.assets/스크린샷 2022-08-07 19.39.12.png)

ㅡ 실패시

```
{
  "response": "error",
  "message": "사용자의 비밀번호를 변경할 수 없었습니다.",
  "data": null
}
```






### 판매자(seller)

#### 회원가입 - DB를 별도로 관리하기 때문에 소비자 아이디와 같아도 된다.

**입력 데이터**

```{
  "businessNumber": "사업자등록번호1",//판매자 사업자등록번호 - 중복될 수 없음
  "sellerId": "parkjongsun",//판매자 ID - 판매자 아이디에서는 중복될 수 없는 유일한 값. 
  "sellerName": "박종선",//판매자 이름
  "sellerPhone": "01099989202",//판매자 핸드폰번호
  "sellerPwd": "jongsunjjang"//판매자 비밀번호
}
```

![스크린샷 2022-08-07 19.41.54](README.assets/스크린샷 2022-08-07 19.41.54.png)

**응답**

ㅡ 성공시

```
{
  "response": "success",
  "message": "판매자 회원가입을 성공적으로 완료했습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 19.44.15](README.assets/스크린샷 2022-08-07 19.44.15.png)

ㅡ 실패시

```
{
  "response": "error",
  "message": "판매자 회원가입에 실패하였습니다.",
  "data": null
}
```



**회원가입 후 DB**

![스크린샷 2022-08-07 19.53.23](README.assets/스크린샷 2022-08-07 19.53.23.png)



#### 아이디 중복 검사 - 소비자와 마찬가지

**아이디 중복 검사 GET method로 url로 검색하고자 하는 아이디를 전달**

customer_id: 소비자 아이디

**아이디 중복 검사 결과**

ㅡ 중복인 경우: {"idCheck": false} 반환

ㅡ 중복이 아닌 경우: {"idCheck": true} 반환



#### 첫화면 - 로그인 - 소비자와 마찬가지

**성공시**

```
{
  "response": "success",
  "message": "로그인에 성공했습니다.",
  "data": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InBhcmtqb25nc3VuIiwiaWF0IjoxNjU5ODY5NTU3LCJleHAiOjE2NTk4Njk1Njd9.Y2x_5LXJFDWR3eNXPeFuKQdGsDtvYLSB6iBWEezv0tQ"
}
```

**실패시**

ㅡ 아이디가 틀린 경우

```
{
  "response": "error",
  "message": "로그인에 실패했습니다.",
  "data": "아이디가 틀립니다."
}
```

ㅡ 비밀번호가 틀린 경우

```
{
  "response": "error",
  "message": "로그인에 실패했습니다.",
  "data": "비밀번호가 틀립니다."
}
```



#### 회원정보 조회 - GET method로 seller_no 전달

**응답**

```
{
  "businessNumber": "사업자등록번호1",
  "sellerName": "박종선",
  "sellerPhone": "01099989202"
}
```

![스크린샷 2022-08-07 19.56.44](README.assets/스크린샷 2022-08-07 19.56.44.png)



#### 회원정보 수정

**입력 데이터**

![스크린샷 2022-08-07 20.02.12](README.assets/스크린샷 2022-08-07 20.02.12.png)



**DB 결과**

![스크린샷 2022-08-07 20.03.21](README.assets/스크린샷 2022-08-07 20.03.21.png)



#### 비밀번호 수정 - 소비자와 마찬가지

**입력 데이터**

```{
  "password": "parkjongsun",
  "passwordUpdate": "parkjongsun1"
} // 실패 사례
```



**응답 결과**

ㅡ 실패시

```
{
  "response": "error",
  "message": "사용자의 비밀번호를 변경할 수 없었습니다.",
  "data": null
}
```

ㅡ 성공시

```
{
  "response": "success",
  "message": "성공적으로 사용자의 비밀번호를 변경했습니다.",
  "data": null
}
```

