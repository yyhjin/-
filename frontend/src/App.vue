<template>
  <div>
    <NavBar/>
    <hr style="border: solid 1px #3CBD92"/>
    <router-view/>
  </div>    
</template>

<script>
import NavBar from '@/components/common/NavBar.vue';

export default {
  components : {NavBar},
};
</script>


<style>

#app {
  text-align: center;
}

nav {
  padding: 30px;
  text-align: center;
}

nav a {
  font-weight: bold;
  color: #FF6F61;
}

nav a.router-link-exact-active {
  color: #3CBD92;
}
</style>
