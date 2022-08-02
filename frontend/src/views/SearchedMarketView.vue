<template>
    <div class="div_big">
        <h2>{{ this.market_name }}</h2>
        <div class="searchBar">
            <el-button color="#FF6F61" round class="btn_back" @click="cl_btn">돌아가기</el-button>
            <el-input class="search_type" v-model="sell_type" placeholder="Please Input" @keyup.enter="enterKey" />
        </div>
        <div class="adjustC">
            <el-checkbox-group v-model="checkList">
                <el-checkbox label="정육점" />
                <el-checkbox label="수산물" />
                <el-checkbox label="청과물" />
                <el-checkbox label="철물점" />
                <el-checkbox label="음식점" />
                <el-checkbox label="잡화점" />
            </el-checkbox-group>
        </div>
        <search-type></search-type>
    </div>
</template>

<script>
import SearchType from "@/components/SearchType.vue";

export default {
    components: { SearchType },
    name: "SearchedMarketView",

    data() {
        return {
            market_name: "",
            sell_type: "",
            checkList: [],
        };
    },

    mounted() {
        this.market_name = this.$route.params.market_name;
    },
    methods: {
        cl_btn() {
            this.$router.push({ name: "search" });
        },
        enterKey() {
            console.log(this.sell_type);
        },
    },

    watch: {
        checkList() {
            console.log(this.checkList);
        },
    },
};
</script>

<style>
.div_big {
    display: inline-block;
}

.div_market {
    border: 1px solid #ff6f61;
    border-radius: 20px;
    width: 300px;
    height: 90px;
    margin: 20px 0px;
}

.searchBar {
    margin-top: 20px;
    margin-bottom: 20px;
}

.btn_back {
    width: 80px;
    height: 40px !important;
    margin-right: 20px;
}
.search_type {
    height: 30px !important;
    width: 190px !important;
}
.adjustC {
    --el-color-primary: #ff6f61;
}
.radioGroup {
    padding-left: 20px;
}
</style>
