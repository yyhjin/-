<template>
  <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/selectjoin">회원가입</router-link> |
      <router-link to="/login">로그인</router-link> |
      <router-link :to="{ name:'mypage', params: { id:1 }}">마이페이지</router-link> |
      <router-link :to="{ name:'mystore', params: { id:1 }}">내 가게</router-link>
  </nav>
</template>

<script>
export default {
  name: "NavBar",

  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style></style>
