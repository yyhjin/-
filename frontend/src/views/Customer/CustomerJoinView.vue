<template>
    <div class="firstDiv">
        <!-- <el-space wrap> -->
        <el-card shadow="always" style="margin: auto">
            <!-- <h2>구매자 회원가입</h2> -->
            <div class="registerMenu">
                <div class="inputRegister">
                    <div style="display: inline-block; width: 100%; text-align: left">
                        <h5 style="display: inline-block">아이디</h5>
                        <el-button link class="btn_idCheck" @click="cl_idCheck" type="danger" style="margin-top: 20px; font-weight: bold">중복 확인</el-button>
                    </div>
                    <el-input placeholder="아이디" v-model="id" class="input_id" />

                    <h5 style="padding-top: 20px">닉네임</h5>
                    <el-input placeholder="닉네임" v-model="nick" class="input_nick" />
                    <h5>비밀번호</h5>
                    <el-input placeholder="비밀번호" v-model="password" class="input_pass" type="password" />
                    <h5>비밀번호 확인</h5>
                    <el-input placeholder="비밀번호 확인" v-model="password_double" class="input_pass" type="password" />
                    <h5 style="text-align: right; color: red; margin-top: 2px; margin-right: 2px">
                        {{ same }}
                    </h5>
                    <h5>이름</h5>
                    <el-input placeholder="이름" v-model="name" class="input_name" />
                    <h5>연락처</h5>
                    <el-input placeholder="연락처" v-model="phone_number" class="input_phone" />
                    <h5 style="float: left">주소</h5>
                    <div class="searchAddress">
                        <el-button round color="#e07c49" @click="execDaumPostcode()" size="small" class="btn_address">주소 검색</el-button><br />
                        <!--<el-input type="text" v-model="postcode" placeholder="우편번호"/>-->
                        <el-input type="text" v-model="address" id="address" placeholder="기본 주소" style="margin-top: 10px" /><br />
                        <el-input type="text" v-model="extraAddress" id="extraAddress" placeholder="참고 항목" style="margin-top: 10px" />
                        <el-input type="text" v-model="detailAddress" id="detailAddress" placeholder="상세 주소" @keyup.enter="cl_register" style="margin-top: 10px" />
                    </div>
                </div>

                <div>
                    <el-button color="#42413e" style="width: 100px" class="btn_register" @click="cl_register">회원가입</el-button>
                    <el-button style="width: 100px" class="btn_cancle" @click="cl_cancle">취소</el-button>
                </div>
            </div>
        </el-card>
        <!-- </el-space> -->
    </div>
</template>

<script>
import { getId, joinCustomer } from "@/api/customer.js";
import { ElMessage } from "element-plus";
export default {
    name: "JoinView",

    data() {
        return {
            id: "",
            password: "",
            password_double: "",
            same: "숫자,문자를 포함한 8자 이상",
            nick: "",
            name: "",
            phone_number: "",
            address: "",
            detailAddress: "",
            extraAddress: "",
        };
    },
    setup() {
        const open = (message) => {
            ElMessage({
                showClose: true,
                message: message,
                type: "success",
            });
        };
        const open2 = (message) => {
            ElMessage({
                showClose: true,
                message: message,
                type: "error",
            });
        };
        return { open, open2 };
    },

    mounted() {
        this.userType = this.$route.params.userType;
    },
    watch: {
        password_double() {
            if (this.password != this.password_double) {
                this.same = "비밀번호가 다르거나 적합하지 않습니다.";
            } else if (this.password == this.password_double) {
                if (this.pass_check() == true) {
                    this.same = "사용 가능합니다.";
                }
            }
        },
    },

    methods: {
        //정규식
        pass_check() {
            var passwordRules = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
            console.log(passwordRules.test(this.password));
            return passwordRules.test(this.password);
        },
        //중복체크
        cl_idCheck() {
            if (this.id) {
                getId(
                    this.id,
                    (response) => {
                        if (response.data.check) {
                            console.log(response);
                            this.open("사용 가능합니다.");
                        } else if (!response.data.check) {
                            this.open2("중복된 아이디입니다.");
                        } else {
                            this.open2("아이디를 입력해주세요.");
                        }
                    },
                    (error) => {
                        console.log(error);
                    }
                );
            } else {
                this.open2("아이디를 입력해주세요.");
            }
        },
        //회원가입
        cl_register() {
            if (this.id != "" && this.name != "" && this.password != "" && this.nick != "" && this.phone_number != "" && this.address) {
                if (this.same == "사용 가능합니다.") {
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
                                this.open("회원가입이 완료되었습니다.");
                                this.$router.push({ name: "login" });
                            } else {
                                this.open2("회원가입에 실패하였습니다.");
                            }
                        },
                        (error) => {
                            console.log(error);
                        }
                    );
                } else {
                    this.open2("비밀번호가 다릅니다.");
                }
            } else {
                this.open2("모두 입력해주세요.");
            }
        },
        cl_cancle() {
            this.$router.push({ name: "selectjoin" });
        },
        //주소검색창
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
.firstDiv {
    text-align: center;
}

.card {
    width: 300px !important;
    margin-top: 40px;
    text-align: center;
}

.btn_idCheck {
    font-weight: bold;
    margin-top: 10px;
    float: right;
    width: 80px;
    text-align: right;
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
