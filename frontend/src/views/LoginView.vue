<template>
    <div class="firstDiv">
        <el-space wrap>
            <el-card class="card">
                <h1>Login</h1>
                <div class="selectType" style="margin: 30px 0px">
                    <el-radio-group class="adjustC" v-model="userType" size="large">
                        <el-radio-button label="구매자" />
                        <el-radio-button label="판매자" />
                    </el-radio-group>
                </div>
                <div class="loginMenu">
                    <div class="inputLogin">
                        <h3>아이디</h3>
                        <el-input placeholder="아이디" v-model="id" class="input_id" style="margin-bottom: 10px" />
                        <h3>비밀번호</h3>
                        <el-input placeholder="패스워드" v-model="password" class="input_pass" type="password" />
                    </div>

                    <div>
                        <el-button @click="ck_login()" color="#FF6F61" style="width: 100px" round class="buttonLogin">로그인</el-button>
                    </div>
                </div>
            </el-card>
        </el-space>
    </div>
</template>

<script>
import { loginCustomer } from "@/api/customer";
import { loginSeller } from "@/api/seller";
import { ref } from "vue";

export default {
    setup() {
        const id = ref();
        const password = ref();
        const userType = ref("구매자");

        function ck_login() {
            if (this.userType == "") {
                alert("구매자? 판매자?");
            } else if (this.id == "") {
                alert("아이디입력 필요");
            } else if (this.password == "") {
                alert("비밀번호 입력 필요");
            } else {
                const params = {
                    username: this.id,
                    password: this.password,
                };
                if (this.userType == "구매자") {
                    loginCustomer(
                        params,
                        (response) => {
                            console.log(response);
                            if (response.data.response == "success") {
                                alert("로그인 성공");
                                //jwt 받아오기
                            } else {
                                alert("아이디 또는 비밀번호 오류");
                            }
                        },
                        (error) => {
                            console.log(error);
                        }
                    );
                } else {
                    loginSeller(
                        params,
                        (response) => {
                            console.log(response);
                            if (response.data.response == "success") {
                                alert("로그인 성공");
                                //jwt 받아오기
                            } else {
                                alert("아이디 또는 비밀번호 오류");
                            }
                        },
                        (error) => {
                            console.log(error);
                        }
                    );
                }
                console.log("상태 " + this.userType);
                console.log("id " + this.id);
                console.log("pass " + this.password);
            }
        }

        return { id, password, userType, ck_login };
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

.loginMenu {
    display: inline-block;
}

.loginMenu h3 {
    text-align: left;
}

.inputLogin {
    width: 200px !important;
    display: block;
}

.el-button {
    color: white !important;
}
.buttonLogin {
    margin-top: 50px;
    margin-bottom: 20px;
}
</style>
