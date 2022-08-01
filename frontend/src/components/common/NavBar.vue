<template>
    <router-link to="/"
        ><div class="div_img"><img src="@/assets/logo2.png" alt="logo" class="logo" /></div
    ></router-link>
    <nav>
        <router-link to="/search">시장검색</router-link> | <router-link to="/selectjoin">회원가입</router-link> | <router-link to="/login">로그인</router-link> |
        <router-link :to="{ name: 'mypage', params: { id: 1 } }">마이페이지</router-link> | <router-link to="/test">소셜테스트</router-link>
    </nav>
</template>

<script>
export default {
    name: "SearchAddress",
    setup() {},
};
</script>

<style>
.logo {
    max-width: 100%;
    max-height: 100%;
}

.div_img {
    width: 100px;
    float: left;
}

.nav {
    float: right;
}
</style>
