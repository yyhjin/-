<template>
    <div class="firstDiv">
        <!-- <el-space wrap> -->
        <el-card shadow="always" style="margin: auto">
            <!-- <h2>판매자 회원가입</h2> -->
            <div class="registerMenu">
                <div class="inputRegister">
                    <div style="display: inline-block; width: 100%; text-align: left">
                        <h5 style="display: inline-block">사업자 등록 번호</h5>
                        <el-button link class="btn_idCheck" @click="cl_busiCheck" type="danger" style="margin-top: 25px; font-weight: bold">중복 확인</el-button>
                    </div>

                    <el-input placeholder="사업자 등록 번호" v-model="seller_number" class="input_Snumber" />

                    <div style="display: inline-block; width: 100%; text-align: left">
                        <h5 style="display: inline-block">아이디</h5>
                        <el-button link class="btn_idCheck" @click="cl_idCheck" type="danger" style="margin-top: 25px; font-weight: bold">중복 확인</el-button>
                    </div>

                    <el-input placeholder="아이디" v-model="id" class="input_id" />

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
import { ElMessage } from "element-plus";
import { getId, joinSeller, getbusiId } from "@/api/seller.js";

export default {
    name: "JoinView",

    data() {
        return {
            seller_number: "",
            sellItem: "",
            id: "",
            password: "",
            password_double: "",
            same: "숫자,문자를 포함한 8자 이상",
            name: "",
            phone_number: "",
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
        password() {
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
        pass_check() {
            var passwordRules = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
            //console.log(passwordRules.test(this.password));
            return passwordRules.test(this.password);
        },
        cl_busiCheck() {
            //console.log(this.seller_number);

            if (!this.seller_number) {
                this.open2("번호를 입력해주세요.");
                return;
            }

            var valueMap = this.seller_number;
            // .replace(/-/gi, "")
            // .split("")
            // .map(function (item) {
            //   return parseInt(item, 10);
            // });

            if (valueMap.length === 10) {
                var multiply = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5);
                var checkSum = 0;

                for (var i = 0; i < multiply.length; ++i) {
                    checkSum += multiply[i] * valueMap[i];
                }

                checkSum += parseInt((multiply[8] * valueMap[8]) / 10, 10);
                if (!(Math.floor(valueMap[9]) === (10 - (checkSum % 10)) % 10)) {
                    this.open2("유효하지 않은 번호입니다.");
                    return;
                }
            } else {
                this.open2("10자리로 입력해주세요.");
                return;
            }
            getbusiId(
                this.seller_number,
                (response) => {
                    if (response.data.check) {
                        //console.log(response);
                        this.open("사용 가능합니다.");
                    } else if (!response.data.check) {
                        this.open2("이미 등록된 번호입니다.");
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        cl_idCheck() {
            //console.log(this.id);
            if (this.id) {
                getId(
                    this.id,
                    (response) => {
                        if (response.data.check) {
                            //console.log(response);
                            this.open("사용 가능합니다.");
                        } else if (!response.data.check) {
                            this.open2("중복된 아이디입니다.");
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
        cl_register() {
            if (this.seller_number != "" && this.id != "" && this.name != "" && this.password != "" && this.phone_number != "") {
                if (this.same == "사용 가능합니다.") {
                    const params = {
                        businessNumber: this.seller_number,
                        sellerId: this.id,
                        sellerName: this.name,
                        sellerPhone: this.phone_number,
                        sellerPwd: this.password,
                        //sellerAddr: this.address + " " + this.detailAddress + " " + this.extraAddress,
                    };
                    joinSeller(
                        params,
                        (response) => {
                            if (response.data.response == "success") {
                                this.open("회원가입이 완료되었습니다.");
                                this.$router.push({ name: "login" });
                            } else {
                                //console.log(response);
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
.btn_sellerCheck {
    color: white;
}
.btn_register {
    color: white;
}

.btn_idCheck {
    color: white;
}
.btn_address {
    color: white;
}
</style>
