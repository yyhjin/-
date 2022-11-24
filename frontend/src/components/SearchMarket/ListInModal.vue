<template>
  <div style="width: 300px; margin: auto; display: block">
    <el-scrollbar max-height="600px">
      <div class="listDiv">
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
    </el-scrollbar>
  </div>
</template>

<script>
import { ref, computed } from "vue";
import { useStore } from "vuex";

export default {
  name: "ListInModal",

  setup() {
    const store = useStore();
    const market = ref({ name: "", no: "" });

    const marketList = computed(() => store.state.marketStore.markets);

    const setMarket = (no) => store.commit("storeInMarket/SET_MARKETNO", no);
    const setMarketName = (name) =>
      store.commit("storeInMarket/SET_MARKETNAME", name);

    const dialogVisible = ref(false);

    return { marketList, market, setMarket, setMarketName, dialogVisible };
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
        // this.$router.push({ name: "searchStore" });

        this.$emit("market_register", this.market);
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
</style>
