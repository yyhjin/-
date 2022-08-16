<template>
  <!-- <router-link to="/">home</router-link> -->
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
            <el-input
              placeholder="아이디"
              v-model="id"
              class="input_id"
              style="margin-bottom: 10px"
            />
            <h3>비밀번호</h3>
            <el-input
              placeholder="패스워드"
              v-model="password"
              class="input_pass"
              type="password"
            />
          </div>

          <div>
            <el-button
              @click="ck_login()"
              color="#FF6F61"
              style="width: 100px"
              round
              class="buttonLogin"
              >로그인</el-button
            >
          </div>
        </div>
      </el-card>
    </el-space>
  </div>
</template>

<script>
import { loginCustomer } from "@/api/customer";
import { loginSeller } from "@/api/seller";
import { ref, computed } from "vue";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import { useCookies } from "vue3-cookies";

export default {
  setup() {
    const store = useStore();
    const cookies = useCookies();
    const id = ref();
    const password = ref();
    const userType = ref("구매자");
    const userno = computed(() => store.state.userInfo.userNo);

    const setUserType = (type) => store.commit("userInfo/SET_USERTYPE", type);
    const setUserNo = (no) => store.commit("userInfo/SET_USERNO", no);
    const setUserId = (no) => store.commit("userInfo/SET_USERID", no);
    const open = () => {
      ElMessage({
        message: "로그인 성공",
        type: "success",
      });
    };
    const open2 = (message) => {
      ElMessage({
        message: message,
        type: "error",
      });
    };

    return {
      id,
      password,
      open,
      open2,
      userno,
      userType,
      setUserNo,
      setUserId,
      setUserType,
      cookies,
    };
  },

  watch: {
    userno() {
      console.log(this.userno);
      if (this.userType == "구매자") {
        this.$router.push({ name: "search" });
      } else {
        this.$router.push({ name: "mystore", params: { id: this.userno } });
      }
    },
  },

  methods: {
    ck_login() {
      if (this.id == undefined) {
        alert("아이디입력 필요");
      } else if (this.password == undefined) {
        alert("비밀번호 입력 필요");
      } else {
        const params = {
          username: this.id,
          password: this.password,
        };

        if (this.userType == "구매자") {
          this.setUserType(this.userType);
          loginCustomer(
            { username: this.id, password: this.password },
            (response) => {
              console.log(response);
              if (response.data.response == "success") {
                this.setUserNo(response.data.data);
                this.setUserId(this.id);
                this.open();
                //this.$cookies.set("hi", "hihi", 2, "/");
                // this.$cookies.get("accessToken");
                //console.log(this.$cookies.get("refreshToken"));
                //jwt 받아오기
              } else {
                this.open2(response.data.data);
              }
            },
            (error) => {
              console.log(error);
            }
          );
        } else {
          this.setUserType(this.userType);
          loginSeller(
            params,
            (response) => {
              console.log(response);
              if (response.data.response == "success") {
                this.setUserNo(response.data.data);
                this.open();
                //localStorage.setItem("test", "test");
                //this.$cookies.get("accessToken");
                //jwt 받아오기
              } else {
                this.open2(response.data.data);
              }
            },
            (error) => {
              console.log(error);
            }
          );
        }
      }
    },
  },
};
</script>

<style scoped>
.el-alert {
  margin: 20px 0 0;
}
.el-alert:first-child {
  margin: 0;
}
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
  border: 2px solid #ff6f61;
  border-radius: 20px;
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
