<template>
    <div class="Div1">
        <div class="searchMenu">
            <el-select v-model="searchDo" filterable placeholder="시/도" style="margin-top: 60px">
                <el-option v-for="item in DoList" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>

            <el-select v-model="searchGu" filterable placeholder="구/군" style="margin: 30px 0px">
                <el-option v-for="item in GuList" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
        </div>
    </div>
</template>

<script>
import { useStore } from "vuex";

export default {
    name: "SearchAddress",
    setup() {
        const store = useStore();
        const DoList = store.getters["root/getDoList"];
        const GuList = store.getters["root/getGuList"];

        return { DoList, GuList };
    },

    data() {
        return {
            searchDo: "",
            searchGu: "",
        };
    },
    watch: {
        searchDo: function () {
            console.log(this.searchDo);
        },

        searchGu: function () {
            console.log(this.searchDo + " " + this.searchGu);
        },
    },
};
</script>

<style></style>
