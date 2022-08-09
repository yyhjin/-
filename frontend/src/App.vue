<template>
    <div>
        <NavBar />
        <hr style="border: solid 1px #3cbd92" />
        <router-view />
    </div>
</template>

<script>
import NavBar from "@/components/common/NavBar.vue";

export default {
    components: { NavBar },
};
</script>

<style>
nav {
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
