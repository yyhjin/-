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
#app {
}
</style>
