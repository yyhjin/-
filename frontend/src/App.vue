<template>
  <div>
    <NavBar style="margin: 0" />
    <!-- <hr style="border: solid 1px #3cbd92" /> -->
    <router-view style="margin: 30px" />
  </div>
</template>

<script>
import NavBar from "@/components/common/NavBar.vue";

export default {
  components: { NavBar },
};
</script>

<style>
@font-face {
  font-family: "InfinitySans-RegularA1";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

html,
body,
#app {
  margin: 0;
  padding: 0;
  background-color: #f4f7f8;
  font-family: "InfinitySans-RegularA1";
}
</style>
