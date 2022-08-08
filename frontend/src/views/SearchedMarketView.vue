<template>
    <div class="div_div">
        <div class="div_big1">
            <h2>{{ this.market_name }}</h2>
            <div class="searchBar">
                <el-button color="#FF6F61" round class="btn_back" @click="cl_btn">돌아가기</el-button>
                <el-input class="search_type" v-model="sell_type" placeholder="Please Input" @keyup.enter="enterKey" />
            </div>
            <div class="adjustC radio_gr">
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
            market_no: "",
            sell_type: "",
            checkList: [],
        };
    },

    mounted() {
        this.market_name = this.$route.params.market_name;
        this.market_no = this.$route.params.market_no;
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

<style scoped>
.div_div {
    text-align: center;
}

.div_big1 {
    display: inline-block;
    width: 300px;
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
    width: 300px;
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
.radio_gr {
    padding-left: 10px;
}
</style>
