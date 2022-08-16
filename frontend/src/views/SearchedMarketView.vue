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
                <el-input class="search_name" v-model="storeName" placeholder="상점 검색" @keyup.enter="enterKey" />
                <el-button :icon="Search" size="large" circle @click="enterKey" />
            </el-card>
            <search-type></search-type>
        </div>
    </div>
</template>

<script>
import SearchType from "@/components/SearchType.vue";
import { useStore } from "vuex";
import { computed } from "vue";
import { Back, Search } from "@element-plus/icons-vue";
import { searchName } from "@/api/store";

export default {
    components: { SearchType },
    name: "SearchedMarketView",

    data() {
        return {
            storeName: "",
        };
    },
    setup() {
        const store = useStore();

        const marketname = computed(() => store.state.storeInMarket.market_name);
        const marketno = computed(() => store.state.storeInMarket.market_no);

        const stores = () => {
            store.dispatch(`storeInMarket/getStore`, marketno);
        };

        const name_stores = (stores) => {
            store.commit(`storeInMarket/SET_STORE_LIST`, stores);
        };

        const clearlist = () => {
            store.commit(`storeInMarket/CLEAR_STORE_LIST`);
        };

        return { Back, Search, stores, marketno, marketname, name_stores, clearlist };
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
            console.log(this.storeName);
            searchName(
                this.marketno,
                this.storeName,
                (success) => {
                    console.log(success.data);
                    this.clearlist();
                    this.name_stores(success.data);
                },
                (error) => {
                    console.log(error);
                }
            );
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
.search_name {
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
