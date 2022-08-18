<template>
  <!-- <nav
    class="nav_room"
    v-if="$route.path.substring(0, 5) == '/room'"
    style="display: flex; justify-content: space-between"
  >
    <h3>room 안</h3>
    <span>
      <el-button
        type="danger"
        size="large"
        @click="
          $router.push({
            name: 'mystore',
            params: { id: $store.getters['userInfo/isAuthenticated'] },
          })
        "
        >닫기</el-button
      >
    </span>
  </nav> -->

  <nav v-if="$route.path.substring(0, 5) != '/room'">
    <img
      src="@/assets/jangbojang-logo.png"
      alt="logo"
      style="width: 100px; margin: 6px"
      @click="cl_img()"
    />
    <div class="div_nav" style="float: right; margin-top: 30px">
      <router-link
        style="margin-right: 20px"
        to="/login"
        v-if="this.userNo == ''"
        >로그인</router-link
      >
      <router-link
        style="margin-right: 20px"
        to="/selectjoin"
        v-if="this.userNo == ''"
        >회원가입</router-link
      >
      <div v-if="this.userNo != ''" style="display: inline-block">
        <el-dropdown>
          <el-button :icon="Menu" style="font-size: 30px" class="dropbtn">
          </el-button>

          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item
                v-if="this.userType == '구매자' && this.userNo != ''"
                ><router-link to="/search" class="router-link"
                  >시장검색</router-link
                ></el-dropdown-item
              >
              <el-dropdown-item
                v-if="this.userType == '판매자' && this.userNo != ''"
                ><router-link
                  class="router-link"
                  :to="{
                    name: 'mystore',
                    params: { id: $store.getters['userInfo/isAuthenticated'] },
                  }"
                  >내 가게</router-link
                ></el-dropdown-item
              >
              <el-dropdown-item
                v-if="this.userType == '구매자' && this.userNo != ''"
                ><router-link
                  class="router-link"
                  :to="{
                    name: 'mypage',
                    params: { id: $store.getters['userInfo/isAuthenticated'] },
                  }"
                  >마이페이지</router-link
                ></el-dropdown-item
              >
              <el-dropdown-item divided v-if="this.userNo != ''"
                ><span
                  style="margin-right: 10px; font-weight: bold"
                  @click="logout"
                  >로그아웃</span
                ></el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </nav>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "vue";
import { ElMessage } from "element-plus";
import { Menu } from "@element-plus/icons-vue";

export default {
  name: "SearchAddress",
  setup() {
    const store = useStore();
    const userType = computed(() => store.state.userInfo.userType);
    const userNo = computed(() => store.state.userInfo.userNo);

    const logOut = () => {
      store.dispatch(`userInfo/logout`);
    };
    const logOutMarket = () => {
      store.dispatch(`storeInMarket/logout`);
    };
    const out = () => {
      ElMessage({
        message: "로그아웃 완료",
        type: "warning",
      });
    };

    return { Menu, userType, userNo, logOut, logOutMarket, out };
  },
  methods: {
    logout() {
      console.log("logout");
      this.logOut();
      this.logOutMarket();
      this.out();
      this.$router.push({ name: "login" });
    },
    cl_img() {
      return this.$router.push("/");
    },
  },
};
</script>

<style scoped>
nav {
  background-color: white;
}

.nav_room {
  height: 40px;
}
.logo {
  max-width: 100%;
  max-height: 100%;
}

nav a {
  font-weight: bold;
  color: #e07c49;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #e2b9a5;
}

.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: #3cbd92;
  display: flex;
  align-items: center;
}

.router-link {
  color: black;
  text-decoration: none;
}

.dropbtn {
  /* background-color: #e07c49;
  color: white; */
  color: #e07c49;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
  margin-top: -10px;
  margin-right: 10px;
}

.dropbtn:hover {
  background-color: #df9874;
  color: white;
}
</style>
