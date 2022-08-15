<template>
    <nav class="nav_room" v-if="$route.path.substring(0, 5) == '/room'" style="display: flex; justify-content: space-between">
        <span>room 안에 진입했을때 변경된 navbar</span>
        <span>
            <el-button type="danger" size="large" @click="$router.push({ name: 'mystore', params: { id: $store.state.userInfo.userNo } })">가게 닫기</el-button>
        </span>
    </nav>

    <nav>
        <img src="@/assets/jang.png" alt="logo" style="width: 100px" />
        <div class="div_nav" style="float: right; margin-top: 30px">
            <router-link style="margin-right: 20px" to="/login" v-if="this.userNo == '9999'">로그인</router-link>
            <router-link style="margin-right: 20px" to="/selectjoin" v-if="this.userNo == '9999'">회원가입</router-link>
            <router-link style="margin-right: 10px" to="/search" v-if="this.userType == '구매자' && this.userNo != '9999'">시장검색</router-link>
            <router-link style="margin-right: 10px" :to="{ name: 'mypage', params: { id: $store.state.userInfo.userNo } }" v-if="this.userType == '구매자' && this.userNo != '9999'"
                >마이페이지</router-link
            >
            <router-link style="margin-right: 20px" :to="{ name: 'mystore', params: { id: $store.state.userInfo.userNo } }" v-if="this.userType == '판매자' && this.userNo != '9999'"
                >내 가게</router-link
            >
            <span style="margin-right: 10px" v-if="this.userNo != '9999'" @click="logout">로그아웃</span>
        </div>
    </nav>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "vue";
import { ElMessage } from "element-plus";

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

        return { userType, userNo, logOut, logOutMarket, out };
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

nav a {
    font-weight: bold;
    color: #ff6f61;
}

nav a.router-link-exact-active {
    color: #3cbd92;
}
</style>
