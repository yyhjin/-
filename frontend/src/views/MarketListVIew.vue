<template>
  <div class="firstDiv">
    <el-card shadow="always" style="margin: auto" class="box-card">
      <div style="font-weight: bold">시장을 검색하세요!</div>
      <div class="selectType" style="margin-top: 20px">
        <el-radio-group
          class="adjustC"
          v-model="searchType"
          size="large"
          style="margin-bottom: 20px"
        >
          <el-radio-button label="주소 검색" />
          <el-radio-button label="이름 검색" />
        </el-radio-group>
        <div v-if="searchType == '주소 검색'">
          <search-address></search-address>
        </div>
        <div v-else>
          <search-name></search-name>
        </div>
      </div>
    </el-card>
    <div style="margin-top: 30px">
      <h4 style="margin-top: 40px; margin-bottom: 10px">
        방문할 시장을 선택해주세요
      </h4>
      <search-list></search-list>
    </div>
  </div>
</template>

<script>
import SearchAddress from "@/components/SearchMarket/SearchAddress.vue";
import SearchName from "@/components/SearchMarket/SearchName.vue";
import SearchList from "@/components/SearchMarket/SearchList.vue";

export default {
  name: "MarketListView",
  components: { SearchAddress, SearchName, SearchList },
  data() {
    return {
      searchType: "주소 검색",
    };
  },
};
</script>

<style scoped>
.firstDiv {
  text-align: center;
}
.adjustC {
  --el-color-primary: #e07c49;
}
@media all and (min-width: 768px) {
  .box-card {
    margin: auto;
    width: 300px;
  }
}
</style>
