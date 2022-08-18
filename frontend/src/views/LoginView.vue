<template>
  <!-- <router-link to="/">home</router-link> -->
  <div class="firstDiv">
    <!-- <el-space wrap> -->
    <el-card class="box-card" shadow="always">
      <!-- <h1>Login</h1> -->
      <div class="selectType" style="margin-top: 10px">
        <el-radio-group class="adjustC" v-model="userType" size="large">
          <el-radio-button label="구매자" />
          <el-radio-button label="판매자" />
        </el-radio-group>
      </div>
      <div class="loginMenu">
        <div class="inputLogin">
          <div
            style="text-align: left; margin: 25px 0px 10px 8px; font-size: 15px"
          >
            아이디
          </div>
          <el-input
            placeholder="아이디를 입력하세요"
            v-model="id"
            class="input_id"
            style="margin-bottom: 10px"
          />
          <div
            style="text-align: left; margin: 20px 0px 10px 8px; font-size: 15px"
          >
            비밀번호
          </div>
          <el-input
            placeholder="비밀번호를 입력하세요"
            @keyup.enter="ck_login"
            v-model="password"
            class="input_pass"
            type="password"
          />
        </div>

        <div>
          <el-button
            @click="ck_login()"
            color="#42413e"
            style="width: 100px"
            class="buttonLogin"
            >로그인</el-button
          >
        </div>
      </div>
    </el-card>
    <!-- </el-space> -->
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
  //로그인하고 진입 시 홈으로 리디렉션.
  mounted() {
    if (this.$store.getters["userInfo/isAuthenticated"] != "") {
      this.$router.push("/");
    }
  },

  setup() {
    // var router = this.$router;
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

  // watch: {
  //     userno() {
  //         console.log(this.userno);
  //         if (this.userType == "구매자") {
  //             this.$router.push({ name: "search" });
  //         } else {
  //             this.$router.push({ name: "mystore", params: { id: this.userno } }); //여기서 버그나요.
  //         }
  //     },
  // },

  methods: {
    ck_login() {
      if (this.id == undefined) {
        alert("아이디를 입력해주세요.");
      } else if (this.password == undefined) {
        alert("비밀번호를 입력해주세요.");
      } else {
        // const params = {
        //     username: this.id,
        //     password: this.password,
        // };

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
                this.$router.push({ name: "search" });
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
            { username: this.id, password: this.password },
            (response) => {
              console.log(response);
              if (response.data.response == "success") {
                this.setUserNo(response.data.data);
                this.setUserId(this.id);
                this.open();
                this.$router.push({
                  name: "mystore",
                  params: { id: response.data.data },
                });
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
  --el-color-primary: #e07c49;
}

.firstDiv {
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
  margin-top: 40px;
  margin-bottom: 20px;
}

@media all and (min-width: 768px) {
  .box-card {
    margin: auto;
    width: 350px;
  }
}
</style>
