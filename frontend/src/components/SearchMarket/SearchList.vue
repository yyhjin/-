<template>
    <el-scrollbar max-height="600px">
        <div class="listDiv">
            <h1>시장 검색결과</h1>
            <div class="div_list" v-for="item in marketList" :key="item.name" @click="cl_market(item)">
                <h3>{{ item.name }}</h3>
                <h5>{{ item.address }}</h5>
                <div v-if="false" v-bind="market">{{ item.no }}</div>
            </div>
        </div>
    </el-scrollbar>
</template>

<script>
import { ref, computed } from "vue";
import { useStore } from "vuex";

export default {
    name: "SearchList",

    setup() {
        const store = useStore();
        const market = ref({ name: "", no: "" });

        const marketList = computed(() => store.state.marketStore.markets);

        return { marketList, market };
    },

    data() {
        return {};
    },

    mounted() {},

    methods: {
        cl_market(item) {
            this.market.no = item.no;
            this.market.name = item.name;
            console.log(this.market);
            this.$router.push({ name: "searchStore", params: { market_name: this.market.name, market_no: this.market.no } });
        },
    },
};
</script>

<style>
.div_list {
    margin: 10px 0px;
    border: 1px solid black;
    border-radius: 15%;
    background-color: rgba(255, 111, 97, 0.5);
}

.listDiv {
    margin-top: 30px;
    display: inline-block;
    width: 300px;
}
</style>
