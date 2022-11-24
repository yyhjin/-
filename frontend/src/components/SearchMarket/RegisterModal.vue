<template>
    <el-dialog visible="dialogVisible" title="시장 검색" width="90%">
        <!-- <el-descriptions :column="1" border> -->
        <search-name></search-name>
        <list-in-modal @market_register="marketData"></list-in-modal>
        <!-- </el-descriptions> -->
    </el-dialog>
</template>

<script>
// import { computed, ref } from "vue";
// import { useStore } from "vuex";
import SearchName from "@/components/SearchMarket/SearchName.vue";
import ListInModal from "@/components/SearchMarket/ListInModal.vue";

export default {
    name: "RegisterModal",
    components: {
        SearchName,
        ListInModal,
    },
    // props: {
    //   market: { name: "", no: "" }
    // },
    // setup() {
    //   const store = useStore();
    //   const market_name = ref("");

    //   const marketList = computed(() => store.state.marketStore.markets);

    //   const reMarket = () => store.commit("marketStore/CLEAR_MARKET_LIST");
    //   const setMarket = () => store.commit("marketStore/SET_MARKET_INIT");

    //   const getMarketByName = () =>
    //     store.dispatch("marketStore/getMarketListByName", market_name.value);

    //   return { market_name, reMarket, setMarket, marketList, getMarketByName };
    // },
    // created() {
    //   this.reMarket();
    //   this.setMarket();
    // },

    methods: {
        marketData(market) {
            this.dialogVisible = false;
            this.$emit("market_register", market);
        },
    },
};
</script>

<style scoped>
.el-button {
    color: white !important;
}
/* Modal */
.modal,
.overlay {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top: 100px;
}
.overlay {
    opacity: 0.5;
    background-color: black;
}
.modal-card {
    position: relative;
    display: block;
    max-width: 80%;
    max-height: 80%;
    margin: auto;
    margin-top: 30px;
    padding: 20px;
    background-color: white;
    min-height: 500px;
    z-index: 10;
    opacity: 1;
}
</style>
