<template>
    <div class="Div1">
        <el-input placeholder="시장 이름 검색" v-model="market_name" class="input_name" style="margin-top: 50px; width: 220px" /><br />
        <el-button color="#FF6F61" round class="btn_name" @click="cl_name" style="margin-top: 50px; margin-bottom: 20px">시장 검색</el-button>
    </div>
</template>

<script>
//import axios from "axios";

export default {
    name: "SearchName",

    data() {
        return {
            market_name: "",
        };
    },

    mounted() {},

    methods: {
        cl_name() {
            console.log(this.market_name);
            // axios.get({"url/" :+ this.market_name}).then((response) => {}).catch((error) => {});
            this.$router.push({ name: "searchStore", params: { market_name: this.market_name } });
        },
    },
};
</script>

<style></style>
