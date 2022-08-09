<template>
    <el-scrollbar max-height="600px">
        <div class="listDiv" @click="cl_market()">
            <h1>시장 검색결과</h1>
            <div v-for="item in marketList" :key="item.name">
                {{ item.name }}
                {{ item.address }}
            </div>
        </div>
    </el-scrollbar>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";

export default {
    name: "SearchList",

    setup() {
        const store = useStore();

        const marketList = computed(() => store.state.marketStore.markets);

        return { marketList };
    },

    data() {
        return {};
    },

    mounted() {},

    methods: {
        cl_market() {
            this.$router.push({ name: "searchStore", params: { market_name: this.market_name } });
        },
    },
};
</script>

<style>
.listDiv {
    margin-top: 30px;
    display: inline-block;
    width: 300px;
    background-color: rgba(255, 111, 97, 0.5);
}
</style>
