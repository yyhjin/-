<template>
    <div class="div_div">
        <div class="div_big1">
            <div>
                <el-button circle class="btn_back" @click="cl_btn" :icon="Back" style="float: left; margin-top: 5px"></el-button>
            </div>
            <div style="text-align: center">
                <h2 style="display: inline; margin-left: -50px">{{ this.marketname }}</h2>
            </div>

            <el-card class="box-card searchBar">
                <el-input class="search_type" v-model="sell_type" placeholder="상점 검색" @keyup.enter="enterKey" />
                <el-button :icon="Search" size="large" circle @click="enterKey" />
            </el-card>
            <!-- <div class="adjustC radio_gr">
                <el-checkbox-group v-model="checkList">
                    <el-checkbox label="정육점" />
                    <el-checkbox label="수산물" />
                    <el-checkbox label="청과물" />
                    <el-checkbox label="철물점" />
                    <el-checkbox label="음식점" />
                    <el-checkbox label="잡화점" />
                </el-checkbox-group>
            </div>-->
            <search-type></search-type>
        </div>
    </div>
</template>

<script>
import SearchType from "@/components/SearchType.vue";
import { useStore } from "vuex";
import { computed } from "vue";
import { Back, Search } from "@element-plus/icons-vue";

export default {
    components: { SearchType },
    name: "SearchedMarketView",

    data() {
        return {
            sell_type: "",
        };
    },
    setup() {
        const store = useStore();

        const marketname = computed(() => store.state.storeInMarket.market_name);
        const marketno = computed(() => store.state.storeInMarket.market_no);

        const stores = () => {
            store.dispatch(`storeInMarket/getStore`, marketno);
        };

        return { Back, Search, stores, marketno, marketname };
    },

    created() {
        this.stores();
    },

    mounted() {},
    methods: {
        cl_btn() {
            this.$router.push({ name: "search" });
        },
        enterKey() {
            console.log(this.sell_type);
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
    margin-top: 10px;
}

.searchBar {
    margin-top: 30px;
    margin-bottom: 20px;
    width: 300px;
}

.btn_back {
    margin-right: 20px;
}
.search_type {
    height: 40px !important;
    width: 190px !important;
    margin-right: 30px;
}
.adjustC {
    --el-color-primary: #ff6f61;
}
.radio_gr {
    padding-left: 10px;
}
</style>
