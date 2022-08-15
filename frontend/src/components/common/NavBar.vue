<template>
    <nav class="nav_room" v-if="$route.path.substring(0, 5) == '/room'"
    style="display:flex; justify-content:space-between">
    <span>room 안에 진입했을때 변경된 navbar</span> 
    <span>
        <el-button type="danger" size="large" @click="$router.go(-2)">가게 닫기</el-button>
    </span>
   
    </nav>
    <nav class="nav_default" v-else>
        <router-link to="/">Home</router-link> | <router-link to="/selectjoin">회원가입</router-link> | <router-link to="/login">로그인</router-link> |
        <router-link to="/search">시장검색</router-link> | <router-link :to="{ name: 'mypage', params: { id: 1 } }">마이페이지 </router-link> |
        <router-link :to="{ name: 'mystore', params: { id: 1 } }">내 가게</router-link> |
        <span @click="logout">로그아웃</span>
    </nav>
</template>

<script>
import { useStore } from "vuex";
import { ElMessage } from "element-plus";

export default {
    name: "SearchAddress",
    setup() {
        const store = useStore();

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
        return { logOut, logOutMarket, out };
    },
    methods: {
        logout() {
            console.log("logout");
            this.logOut();
            this.logOutMarket();
            this.out();
            this.$router.push({ name: "login" });
        },
    },
};
</script>

<style scoped>
.nav_room {
    height: 40px;
}
.logo {
    max-width: 100%;
    max-height: 100%;
}

.div_img {
    width: 100px;
    float: left;
}

.nav_default {
    padding: 30px;
    text-align: center;
}

nav a {
    font-weight: bold;
    color: #ff6f61;
}

nav a.router-link-exact-active {
    color: #3cbd92;
}
</style>
