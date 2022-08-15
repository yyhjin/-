<template>
    <div class="firstDiv">
        <el-space wrap>
            <el-card class="card">
                <h2>구매자 회원가입</h2>
                <div class="registerMenu">
                    <div class="inputRegister">
                        <h5>아이디</h5>
                        <el-input placeholder="아이디 (이메일 형식)" v-model="id" class="input_id" />
                        <el-button color="#FF6F61" round class="btn_idCheck" @click="cl_idCheck">중복 확인</el-button>
                        <h5 style="padding-top: 20px">닉네임</h5>
                        <el-input placeholder="닉네임" v-model="nick" class="input_nick" />
                        <h5>비밀번호</h5>
                        <el-input placeholder="패스워드" v-model="password" class="input_pass" type="password" />
                        <h5>비밀번호 확인</h5>
                        <el-input placeholder="패스워드 확인" v-model="password_double" class="input_pass" type="password" />
                        <h5 style="text-align: right; color: red">{{ same }}</h5>
                        <h5>이름</h5>
                        <el-input placeholder="이름" v-model="name" class="input_name" />
                        <h5>연락처</h5>
                        <el-input placeholder="연락처" v-model="phone_number" class="input_phone" />
                        <h5 style="float: left">주소</h5>
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
import { getId, joinCustomer } from "@/api/customer.js";
export default {
    name: "JoinView",

    data() {
        return {
            id: "",
            nick: "",
            password: "",
            password_double: "",
            same: "",
            name: "",
            phone_number: "",
            address: "",
            detailAddress: "",
            extraAddress: "",
        };
    },

    mounted() {
        this.userType = this.$route.params.userType;
    },
    watch: {
        password_double() {
            if (this.password != this.password_double) {
                this.same = "다름";
            } else if (this.password == this.password_double) {
                this.same = "같음";
            }
        },
    },

    methods: {
        cl_idCheck() {
            if (this.id) {
                getId(
                    this.id,
                    (response) => {
                        if (response.data.check) {
                            console.log(response);
                            alert("사용 가능합니다.");
                        } else if (!response.data.check) {
                            alert("중복된 아이디입니다.");
                        } else {
                            alert("아이디 입력!!");
                        }
                    },
                    (error) => {
                        console.log(error);
                    }
                );
            } else {
                alert("아이디 입력하세요");
            }
        },
        cl_register() {
            if (this.id != "" && this.name != "" && this.password != "" && this.nick != "" && this.phone_number != "") {
                if (this.same == "같음") {
                    const params = {
                        customerId: this.id,
                        customerName: this.name,
                        customerPwd: this.password,
                        customerNickname: this.nick,
                        customerPhone: this.phone_number,
                        customerAddr: this.address + " " + this.detailAddress + " " + this.extraAddress,
                    };
                    joinCustomer(
                        params,
                        (response) => {
                            if (response.data.response == "success") {
                                alert("회원가입이 완료되었습니다.");
                                this.$router.push({ name: "home" });
                            } else {
                                alert("회원가입에 실패하였습니다.");
                            }
                        },
                        (error) => {
                            console.log(error);
                        }
                    );
                } else {
                    alert("비밀번호가 다름니다");
                }
            } else {
                alert("칸을 모두 채워주세요");
            }
        },
        cl_cancle() {
            this.$router.push({ name: "selectjoin" });
        },
        execDaumPostcode() {
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

.btn_idCheck {
    color: white;
    margin-top: 10px;
    float: right;
    width: 80px;
}

.btn_nickCheck {
    margin-top: 10px;
    float: right;
    width: 80px;
}

.btn_address {
    color: white;
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
    color: white;
    height: 40px !important;
    margin-top: 40px;
}

.btn_cancle {
    color: black !important;
    height: 40px !important;
    margin-top: 40px;
}
</style>
