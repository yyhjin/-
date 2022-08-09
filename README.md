# 구현 진행 상황

노션 링크:https://sly-hexagon-e43.notion.site/74c6b74788fc45a9bb116f95732ec3b4

노션 - 구현 진행 상황 링크: https://sly-hexagon-e43.notion.site/f7e3003b0723431c8b69d20f3334df65

프론트앤드 코드: gitlab의 dev-front 브랜치에 올라가 있음

백앤드 코드: gitlabdml dev-back 브랜치에 올라가 있음

배포 계획 : 엔진엑스로 통신하면서 도커위에 프론트 백엔드 올릴 예정입니다(젠킨스 도입은 미정)



[TOC]



## Front

프론트 작업물은 노션 - 구현 상황에 업로드 하였음

링크:  https://sly-hexagon-e43.notion.site/f7e3003b0723431c8b69d20f3334df65

백앤드는 README.md에 전체 작성함




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

![스크린샷 2022-08-07 18_40_58](README.assets/2022-08-07 18_40_58.png)



**회원가입 응답**

ㅡ 성공시

```
{
  "response": "success",
  "message": "소비자 회원가입을 성공적으로 완료했습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 18.41.41](README.assets/2022-08-07 18_41_41.png)

ㅡ 실패시

```
{
  "response": "error",
  "message": "소비자 회원가입에 실패하였습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 18.52.11](README.assets/2022-08-07 18_52_11.png)



**회원가입 후 SQL에 저장되는 상황**

![스크린샷 2022-08-07 18.43.10](README.assets/2022-08-07 18_43_10.png)



#### 아이디 중복 검사

**아이디 중복 검사 GET method로 url로 검색하고자 하는 아이디를 전달**

customer_id: 소비자 아이디

![스크린샷 2022-08-07 18.54.25](README.assets/2022-08-07 18_54_25.png)



**아이디 중복 검사 결과**

ㅡ 중복인 경우: {"idCheck": false} 반환

![스크린샷 2022-08-07 18.57.02 1](README.assets/2022-08-07 18_57_02 1.png)

ㅡ 중복이 아닌 경우: {"idCheck": true} 반환

![스크린샷 2022-08-07 19.01.08](README.assets/2022-08-07 19_01_08.png)



#### 로그인: JWT, Redis로 refreshToken, accessToken 구현

**로그인 데이터 전달**

```{
  "password": "parkjongsun", // 잘못된 경우를 예시로 함
  "username": "jongsunjjang1" // 잘못된 경우를 예시로 함
}
```

![스크린샷 2022-08-07 19.02.23](README.assets/2022-08-07 19_02_23.png)



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

![스크린샷 2022-08-07 19.05.06](README.assets/2022-08-07 19_05_06.png)

ㅡ 로그인에 성공한 경우

```
{
  "response": "success",
  "message": "로그인에 성공했습니다.",
  "data": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InBhcmtqb25nc3VuIiwiaWF0IjoxNjU5ODY2NzYxLCJleHAiOjE2NTk4NjY3NzF9.IJmJQWHV7c7NQ7iaV_UcMAzhXDJHJf2BquyAy7_qusM"
}
```

![스크린샷 2022-08-07 19.07.54](README.assets/2022-08-07 19_07_54.png)

ㅡ Token: refreshToken, accessToken 정상적으로 들어감 & HttpOnly

![스크린샷 2022-08-07 21.16.33](README.assets/2022-08-07 21_16_33.png)



#### 회원정보 조회 - 개인정보

![스크린샷 2022-08-07 19.09.35](README.assets/2022-08-07 19_09_35.png)



#### 회원정보 수정

**수정할 회원정보 입력**

![스크린샷 2022-08-07 19.21.35](README.assets/2022-08-07 19_21_35.png)



**DB 반영 결과**

![스크린샷 2022-08-07 19.24.35](README.assets/2022-08-07 19_24_35.png)



#### 비밀번호 수정

**데이터 입력**

![스크린샷 2022-08-07 19.38.28](README.assets/2022-08-07 19_38_28.png)

**응답 데이터**

ㅡ 성공시

```
{
  "response": "success",
  "message": "성공적으로 사용자의 비밀번호를 변경했습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 19.39.12](README.assets/2022-08-07 19_39_12.png)

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

![스크린샷 2022-08-07 19.41.54](README.assets/2022-08-07 19_41_54.png)

**응답**

ㅡ 성공시

```
{
  "response": "success",
  "message": "판매자 회원가입을 성공적으로 완료했습니다.",
  "data": null
}
```

![스크린샷 2022-08-07 19.44.15](README.assets/2022-08-07 19_44_15.png)

ㅡ 실패시

```
{
  "response": "error",
  "message": "판매자 회원가입에 실패하였습니다.",
  "data": null
}
```



**회원가입 후 DB**

![스크린샷 2022-08-07 19.53.23](README.assets/2022-08-07 19_53_23.png)



#### 아이디 중복 검사 - 소비자와 마찬가지

**아이디 중복 검사 GET method로 url로 검색하고자 하는 아이디를 전달**

customer_id: 소비자 아이디

**아이디 중복 검사 결과**

ㅡ 중복인 경우: {"idCheck": false} 반환

ㅡ 중복이 아닌 경우: {"idCheck": true} 반환



#### 로그인 - 소비자와 마찬가지

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

![스크린샷 2022-08-07 19.56.44](README.assets/2022-08-07 19_56_44.png)



#### 회원정보 수정

**입력 데이터**

![스크린샷 2022-08-07 20.02.12](README.assets/2022-08-07 20_02_12.png)



**DB 결과**

![스크린샷 2022-08-07 20.03.21](README.assets/2022-08-07 20_03_21.png)



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

  


### 지역 주소(sido, gugun)


#### 시, 도 조회

ㅡ 시,도 전체 리스트 반환

```
[
  {
    "sidoCode": "11",        //시,도 코드 앞 두글자
    "sidoName": "서울특별시" //시,도 이름
  },
  ...
]
```

![image](/uploads/730ab768efcd213b61ce6bd7a1a66fe7/image.png)


#### 구, 군 조회

ㅡ 시,도 코드(시,도 코드의 맨 앞 두글자)로 해당 시,도의 구,군 목록 반환

```
[
  {
    "sidoGugun": "서울특별시 강남구", //시,도 이름 + 구,군 이름
    "gugunCode": "1168000000",       //구,군 코드
    "gugunName": "강남구"            //구,군 이름
  },
  {
    "sidoGugun": "서울특별시 강동구",
    "gugunCode": "1174000000",
    "gugunName": "강동구"
  },
  ...
]
```

![image](/uploads/5a9dde8386785361e1d7b06f5a003279/image.png)

  


### 시장(market)


#### 시장번호로 시장정보 조회

```
{
  "marketNo": 1,
  "marketName": "강남시장",
  "marketAddr": "서울특별시 강남구 압구정로2길 46",
  "lat": 37.5188,
  "lng": 127.021
}
```

![image](/uploads/d700801ec062fc3e48d8a92e913c1a9f/image.png)


#### 주소로 시장목록 조회

ㅡ 시,도 이름 + 구,군 이름으로 해당 주소의 시장 목록 반환

```
[
  {
    "marketNo": 1,
    "marketName": "강남시장",
    "marketAddr": "서울특별시 강남구 압구정로2길 46",
    "lat": 37.5188,
    "lng": 127.021
  },
  {
    "marketNo": 2,
    "marketName": "신사상가",
    "marketAddr": "서울특별시 강남구 압구정로29길 72-1",
    "lat": 37.5323,
    "lng": 127.028
  },
  ...
```

![2022-08-07_223626](/uploads/fe08dd1fc32caf61fe24f382f02864d4/2022-08-07_223626.png)


#### 시장이름으로 시장목록 조회

ㅡ 해당 이름을 포함하고 있는 시장 목록 조회

```
[
  {
    "marketNo": 17,
    "marketName": "수유중앙시장",
    "marketAddr": "서울특별시 강북구 노해로17길 21",
    "lat": 37.6403,
    "lng": 127.021
  },
  {
    "marketNo": 28,
    "marketName": "신월중앙시장",
    "marketAddr": "서울특별시 강서구 강서로5나길 109",
    "lat": 37.528,
    "lng": 126.842
  },
  ...
```

![2022-08-07_224017](/uploads/6997a797ebd4a84a52b13bf3d3ea1ed1/2022-08-07_224017.png)

  


### 주문(order)


#### 주문서 생성

ㅡ 주문 아이템 1개에 대한 정보를 입력하여 주문서, 주문한 아이템 create 한 후 주문 번호 반환  
※추후 주문 아이템을 리스트로 입력 받도록 수정 예정

**입력 데이터**  
ㅡ 주문 아이템 번호, 주문 아이템 수량, 소비자 번호, 상점 번호  
![2022-08-07_231703](/uploads/bc600ae781609bb13cfe6334422fe28e/2022-08-07_231703.png)

**반환 데이터**  
ㅡ 주문 번호 반환  
![2022-08-07_225706](/uploads/f52832979a93807997d575fcec837347/2022-08-07_225706.png)

**MySQL**  
ㅡ 주문서(order)  
![2022-08-07_230101](/uploads/835894e827caa8fd12ae202a8d0b1aaa/2022-08-07_230101.png)  
ㅡ 주문 아이템(order_item)  
![2022-08-07_230125](/uploads/d37fd32dd995cff83d77a844d8120b14/2022-08-07_230125.png)


#### 주문상태 수정

ㅡ 해당 주문의 상태를 ORDER, CANCEL, DELIVERY 로 변경, 해당 주문정보 반환

**입력 데이터**  
ㅡ 주문 번호, 수정할 상태  
![2022-08-07_232007](/uploads/35daaeada6aa29c5b44d39dbaeb24341/2022-08-07_232007.png)

**반환 데이터**  
ㅡ 수정 후의 주문정보
```
{
  "orderNo": 22,
  "orderDate": "2022-08-07T14:10:22.000+00:00",
  "marketNo": 3,
  "storeNo": 2,
  "status": "수정TEST",
  "orderItems": [
    {
      "orderItemNo": 11,
      "count": 1,
      "price": 1000,
      "itemName": "사과",
      "orderNo": 22
    }
  ],
  "marketName": null,
  "customerNo": 1,
  "customerId": "아이디",
  "storeName": null
}
```

![2022-08-07_232207](/uploads/5e0091f7f6df639dbdff046778dace5f/2022-08-07_232207.png)


#### 주문목록 조회(소비자)

ㅡ 소비자 번호로 해당 소비자의 주문목록 반환 (주문날짜 내림차순)
```
[
  {
    "orderNo": 22,
    "orderDate": "2022-08-07T14:10:22.000+00:00",
    "marketNo": 3,
    "storeNo": 2,
    "status": "수정TEST",
    "orderItems": [
      {
        "orderItemNo": 11,
        "count": 1,
        "price": 1000,
        "itemName": "사과",
        "orderNo": 22
      }
    ],
    "marketName": "영동전통시장",
    "customerNo": 1,
    "customerId": "아이디",
    "storeName": "프룻프룻"
  },
  ...
]
```

![2022-08-08_002337](/uploads/988e7bc45503c9d40d23a9c01c1ffbf8/2022-08-08_002337.png)


#### 주문목록 조회(판매자)

ㅡ 상점 번호로 해당 상점의 주문목록 반환 (주문날짜 내림차순, 필요 없는 정보는 담지 않음)
```
[
  {
    "orderNo": 22,
    "orderDate": "2022-08-07T14:10:22.000+00:00",
    "marketNo": 3,
    "storeNo": 2,
    "status": "수정TEST",
    "orderItems": [
      {
        "orderItemNo": 11,
        "count": 1,
        "price": 1000,
        "itemName": "사과",
        "orderNo": 22
      }
    ],
    "marketName": null,
    "customerNo": 1,
    "customerId": "아이디",
    "storeName": null
  },
  ...
]
```

![2022-08-08_002444](/uploads/8c567da96e2a795fec444fa7b75d219a/2022-08-08_002444.png)


#### 첫 주문 여부 조회(소비자)

ㅡ 소비자 번호로 해당 소비자의 첫 주문 여부 반환
![2022-08-07_233203](/uploads/303d7b46bc18aa8fb0fd66303d0bf925/2022-08-07_233203.png)


### 상점관리

주로 상점 관리에 쓰이고 대기화면에서 소비자에게 보여지는 방목록들.
주문할 때 상품을 고를 수 있는 상품 목록 조회, 
판매자 입장에서는 상점 crud. 아이템 crud(수정은 필요없어서 지울 예정)
방을 만들 때 (장사시작할 때) 판매할 품목을 보다 쉽게 고르게 하기 위해 전날 판매했던 품목을 저장해두고 목록으로 미리 보여주는 최근판매여부가있다.

#### 상점등록 
-반환은 생성된 상점번호를 보내준다.
![22](https://user-images.githubusercontent.com/26956570/183316622-39b68200-7984-44a6-af34-fa68c8c03c27.png)
#### 상점정보수정
![image](https://user-images.githubusercontent.com/26956570/183306256-5ffdbf3a-eedb-4ca4-91d9-1c89e9eb5579.png)
![image](https://user-images.githubusercontent.com/26956570/183306281-efae2291-c338-4b52-84c3-ab933e220693.png)

#### 상점정보조회
- 상점 번호로 상점의 정보를 조회한다.     
![image](https://user-images.githubusercontent.com/26956570/183306027-ab5c1533-6901-4f82-aef9-43ddca31faf3.png)
#### 상점삭제
- 상점 번호로 상점의 정보를 삭제한다.      
불필요할 것 같아서 생략.    
#### 한 시장안의 상점목록
![image](https://user-images.githubusercontent.com/26956570/183306160-855f1efa-81bb-4bdc-b35c-6e22fac9642f.png)
#### 방 정보 조회 (라이브 상점정보)
- 아직 등록되지 않았을 경우에 널값을 보낸다.     
![image](https://user-images.githubusercontent.com/26956570/183306497-2d86b2fd-260c-4ad6-961c-34870e8a4339.png)
#### 방 정보 수정
![image](https://user-images.githubusercontent.com/26956570/183306474-220f8ae5-df77-4ba7-b086-17386fc71efc.png)
#### 상품 등록
- 여러차례 넣은 모습(이름이 중복이 되지않도록 수정필요 )    
![image](https://user-images.githubusercontent.com/26956570/183306634-493261e6-af05-4e95-b226-5a1197b47c4f.png)
#### 상품 수정(안쓸예정)
#### 상품 삭제 
생략
#### 상점에서 파는 목록
![image](https://user-images.githubusercontent.com/26956570/183306909-b0d49d57-df7b-4e69-b06c-957e3435b12b.png)
#### 상품의 최근 판매여부  
![image](https://user-images.githubusercontent.com/26956570/183306949-1156e953-b4d6-449b-ad86-5d35a0a54f04.png)
