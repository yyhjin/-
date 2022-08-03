<template>
    <div class="firstDiv">
        <el-space wrap>
            <el-card class="card">
                <h2>판매자 회원가입</h2>
                <div class="registerMenu">
                    <div class="inputRegister">
                        <h5>사업자 등록 번호</h5>
                        <el-input placeholder="사업자 등록 번호" v-model="seller_number" class="input_Snumber" />
                        <el-button color="#FF6F61" round class="btn_sellerCheck" @click="cl_sellerCheck">확인</el-button>
                        <h5 style="padding-top: 20px">업종</h5>
                        <el-input placeholder="업종" v-model="sellItem" class="input_sellItem" />
                        <h5>아이디</h5>
                        <el-input placeholder="아이디 (이메일 형식)" v-model="id" class="input_id" />
                        <el-button color="#FF6F61" round class="btn_idCheck" @click="cl_idCheck">중복 확인</el-button>
                        <h5 style="padding-top: 20px">비밀번호</h5>
                        <el-input placeholder="패스워드" v-model="password" class="input_pass" type="password" />
                        <h5>비밀번호 확인</h5>
                        <el-input placeholder="패스워드 확인" v-model="password_double" class="input_pass" type="password" />
                        <h5>이름</h5>
                        <el-input placeholder="이름" v-model="name" class="input_name" />
                        <h5>연락처</h5>
                        <el-input placeholder="연락처" v-model="phone_number" class="input_phone" />
                        <h5 style="float: left">가게 주소</h5>
                        <div class="searchAddress">
                            <el-button round color="#FF6F61" @click="execDaumPostcode()" class="btn_address">주소 검색</el-button><br />
                            <!--<el-input type="text" v-model="postcode" placeholder="우편번호"/>-->
                            <el-input type="text" v-model="address" id="address" placeholder="기본 주소" style="margin-top: 10px" /><br />
                            <el-input type="text" v-model="detailAddress" id="detailAddress" placeholder="상세 주소" style="margin-top: 10px" />
                            <el-input type="text" v-model="extraAddress" id="extraAddress" placeholder="참고 항목" style="margin-top: 10px" />
                        </div>
                    </div>

                    <div>
                        <el-button color="#FF6F61" style="width: 100px" round class="btn_register" @click="cl_register">회원가입</el-button>
                        <el-button style="width: 100px" round class="btn_cancle" @click="cl_cancle">취소</el-button>
                    </div>
                </div>
            </el-card>
        </el-space>
    </div>
</template>

<script>
export default {
    name: "JoinView",

    data() {
        return {
            seller_number: "",
            sellItem: "",
            id: "",
            password: "",
            password_double: "",
            name: "",
            phone_number: "",
            address: "",
            postcode: "",
            detailAddress: "",
            extraAddress: "",
            birthday: "",
            gender: "",
            userType: "",
        };
    },

    mounted() {
        this.userType = this.$route.params.userType;
    },

    methods: {
        cl_sellerCheck() {
            console.log(this.seller_number);
        },
        cl_idCheck() {
            console.log(this.id);
        },
        cl_register() {
            console.log("업종:" + this.sellItem);
            console.log("상태 : " + this.userType + " 아이디:" + this.id + " 비밀번호:" + this.password + " 비밀번호검증:" + this.password_double);
            console.log("이름 :" + this.name + " 번호:" + this.phone_number);
            console.log("주소:" + this.address + " 우편번호:" + this.postcode + " 상세주소:" + this.detailAddress + " 추가:" + this.extraAddress);
            console.log("생일:" + this.birthday + " 성별:" + this.gender);
        },
        cl_cancle() {
            this.$router.push({ name: "selectjoin" });
        },
        execDaumPostcode() {
            console.log("hi");
            new window.daum.Postcode({
                oncomplete: (data) => {
                    if (this.extraAddress !== "") {
                        this.extraAddress = "";
                    }
                    if (data.userSelectedType === "R") {
                        // 사용자가 도로명 주소를 선택했을 경우
                        this.address = data.roadAddress;
                    } else {
                        // 사용자가 지번 주소를 선택했을 경우(J)
                        this.address = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if (data.userSelectedType === "R") {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                            this.extraAddress += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== "" && data.apartment === "Y") {
                            this.extraAddress += this.extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (this.extraAddress !== "") {
                            this.extraAddress = `(${this.extraAddress})`;
                        }
                    } else {
                        this.extraAddress = "";
                    }
                    // 우편번호를 입력한다.
                    this.postcode = data.zonecode;
                },
            }).open();
        },
    },
};
</script>

<style scoped>
.adjustC {
    --el-color-primary: #ff6f61;
}

.firstDiv {
    text-align: center;
}

.card {
    width: 300px !important;
    margin-top: 40px;
    text-align: center;
}

.btn_sellerCheck {
    margin-top: 10px;
    float: right;
}

.btn_idCheck {
    margin-top: 10px;
    float: right;
}

.btn_address {
    float: right;
    margin-top: 20px;
    width: 80px;
}

.registerMenu {
    display: inline-block;
}

.registerMenu h5 {
    text-align: left;
    margin-bottom: 10px;
}

.radioGroup {
    text-align: left !important;
}

.btn_register {
    height: 40px !important;
    margin-top: 40px;
}

.btn_cancle {
    color: black !important;
    height: 40px !important;
    margin-top: 40px;
}
</style>
