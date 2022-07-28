<template>
  <nav>
    <router-link to="/">Home</router-link> | <router-link to="/selectjoin">회원가입</router-link> |
    <router-link to="/login">로그인</router-link> |
    <router-link to="/test">소셜테스트</router-link> |
    <router-link to="/search">시장검색</router-link>
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
