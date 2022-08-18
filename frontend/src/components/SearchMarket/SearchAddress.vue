<template>
  <div class="Div1">
    <div class="searchMenu">
      <el-select
        v-model="searchDo"
        filterable
        placeholder="시/도"
        style="width: 200px"
      >
        <el-option
          v-for="item in DoList"
          :key="item.value"
          :label="item.text"
          :value="item"
        />
      </el-select>

      <el-select
        v-model="searchGu"
        filterable
        placeholder="구/군"
        style="margin: 20px 0px; width: 200px"
      >
        <el-option
          v-for="item in GuList"
          :key="item.value"
          :label="item.text"
          :value="item.text"
        />
      </el-select>
    </div>
  </div>
</template>

<script>
import { computed, onMounted, ref } from "vue";
import { useStore } from "vuex";

export default {
  name: "SearchAddress",
  setup() {
    const store = useStore();
    const searchDo = ref();
    const searchGu = ref();

    const setDo = onMounted(() => store.dispatch("marketStore/getSido"));

    const marketList = computed(() => store.state.marketStore.markets);
    const DoList = computed(() => store.state.marketStore.sidos);
    const GuList = computed(() => store.state.marketStore.guguns);

    const reDo = () => store.commit("marketStore/CLEAR_SIDO_LIST");
    const reGu = () => store.commit("marketStore/CLEAR_GUGUN_LIST");
    const reMarket = () => store.commit("marketStore/CLEAR_MARKET_LIST");

    const setGuList = () => {
      store.dispatch(`marketStore/getGugun`, searchDo.value);
    };

    const getMarketList = () =>
      store.dispatch(
        `marketStore/getMarketList`,
        searchDo.value.text + " " + searchGu.value
      );

    return {
      setDo,
      DoList,
      searchDo,
      searchGu,
      reDo,
      reGu,
      reMarket,
      setGuList,
      GuList,
      marketList,
      getMarketList,
    };
  },
  created() {
    this.reDo;
    this.reMarket;
    this.setDo;
  },

  watch: {
    searchDo: function () {
      //console.log("도코드 " + this.searchDo);
      this.searchGu = "";
      this.setGuList();
    },

    searchGu: function () {
      console.log(this.searchDo.text + " " + this.searchGu);
      this.getMarketList();
    },
  },
};
</script>

<style></style>
