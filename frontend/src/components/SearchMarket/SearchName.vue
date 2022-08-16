<template>
    <div class="Div1">
        <el-input placeholder="시장 이름 검색" v-model="market_name" class="input_name" style="margin-top: 50px; width: 220px" /><br />
        <el-button color="#FF6F61" round class="btn_name" @click="cl_name" style="margin-top: 50px; margin-bottom: 20px">시장 검색</el-button>
    </div>
</template>

<script>
import { computed, ref } from "vue";
import { useStore } from "vuex";

export default {
    name: "SearchName",

    setup() {
        const store = useStore();
        const market_name = ref("");

        const marketList = computed(() => store.state.marketStore.markets);

        const reMarket = () => store.commit("marketStore/CLEAR_MARKET_LIST");
        const setMarket = () => store.commit("marketStore/SET_MARKET_INIT");

        const getMarketByName = () => store.dispatch("marketStore/getMarketListByName", market_name.value);

        return { market_name, reMarket, setMarket, marketList, getMarketByName };
    },
    created() {
        this.reMarket();
        this.setMarket();
    },

    methods: {
        cl_name() {
            console.log(this.market_name);
            this.getMarketByName();
        },
    },
};
</script>

<style scoped>
.el-button {
    color: white !important;
}
</style>
