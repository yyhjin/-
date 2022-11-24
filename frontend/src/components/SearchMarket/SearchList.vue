<template>
  <el-scrollbar max-height="600px">
    <div class="box-card">
      <!-- <h1>시장 검색결과</h1> -->
      <!-- <div
        class="div_list"
        v-for="item in marketList"
        :key="item.name"
        @click="cl_market(item)"
      > -->
      <!-- <h3>{{ item.name }}</h3>
        <h5>{{ item.address }}</h5>
        <div v-if="false" v-bind="market">{{ item.no }}</div> -->
      <el-table :data="marketList" style="width: 100%" @row-click="cl_market">
        <el-table-column
          prop="name"
          label="이름"
          width="160"
          style="font-weight: bold"
        />
        <el-table-column prop="address" label="주소" />
      </el-table>
    </div>
    <!-- </div> -->
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

    const setMarket = (no) => store.commit("storeInMarket/SET_MARKETNO", no);
    const setMarketName = (name) =>
      store.commit("storeInMarket/SET_MARKETNAME", name);

    return { marketList, market, setMarket, setMarketName };
  },

  data() {
    return {};
  },

  mounted() {},

  methods: {
    cl_market(item) {
      if (item.no != 0) {
        this.market.no = item.no;
        this.market.name = item.name;
        this.setMarket(this.market.no);
        this.setMarketName(this.market.name);
        console.log(this.market);
        this.$router.push({ name: "searchStore" });
      }
    },
  },
};
</script>

<style scoped>
.div_list {
  margin: 10px 0px;
  border: 1px solid #ff6f61;
  border-radius: 10px;
  background-color: rgba(255, 111, 97, 0.4);
}

.listDiv {
  margin-top: 30px;
  display: inline-block;
  width: 300px;
}

@media all and (min-width: 768px) {
  .box-card {
    margin: auto;
    width: 350px;
    margin-top: 30px;
  }
}
</style>
