<template>
    <div class="Div1">
        <div class="searchMenu">
            <el-select v-model="searchDo" filterable placeholder="시/도" style="margin-top: 60px">
                <el-option v-for="item in DoList" :key="item.value" :label="item.text" :value="item.value" />
            </el-select>

            <el-select v-model="searchGu" filterable placeholder="구/군" style="margin: 30px 0px">
                <el-option v-for="item in GuList" :key="item.value" :label="item.text" :value="item.value" />
            </el-select>
        </div>
    </div>
</template>

<script>
import { computed, ref } from "vue";
import { useStore } from "vuex";

export default {
    name: "SearchAddress",
    setup() {
        const store = useStore();
        const searchDo = ref();
        const searchGu = ref();
        const setDo = () => store.commit("marketStore/getSido");

        const marketList = computed(() => store.state.marketStore.markets);
        const DoList = computed(() => store.state.marketStore.sidos);
        const GuList = computed(() => store.state.marketStore.guguns);

        const reDo = () => store.commit("marketStore/CLEAR_SIDO_LIST");
        const reGu = () => store.commit("marketStore/CLEAR_GUGUN_LIST");

        const setGuList = () => store.dispatch("marketStore/getGugun");
        const getMarketList = () => store.dispatch("marketStore/getMarketList");

        return { setDo, DoList, searchDo, searchGu, reDo, reGu, setGuList, GuList, marketList, getMarketList };
    },

    watch: {
        searchDo: function () {
            this.reDo;
            console.log(this.searchDo);
            this.setGuList(this.searchDo);
        },

        searchGu: function () {
            this.reGu;
            console.log(this.searchDo + " " + this.searchGu);
            this.getMarketList(this.searchDo, this.searchGu);
        },
    },
};
</script>

<style></style>
