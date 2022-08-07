<template>
    <div class="firstDiv">
        <el-space wrap>
            <el-card class="card1">
                <div class="selectType" style="margin-top: 30px">
                    <el-radio-group class="adjustC" v-model="searchType" size="large">
                        <el-radio-button label="주소검색" />
                        <el-radio-button label="이름검색" />
                    </el-radio-group>
                    <div v-if="searchType == '주소검색'">
                        <search-address></search-address>
                    </div>
                    <div v-else>
                        <search-name></search-name>
                    </div>
                </div>
            </el-card>
        </el-space>
        <div style="margin-top: 50px">
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
            searchType: "주소검색",
        };
    },
    mounted() {},
    methods: {},
};
</script>

<style scoped>
.adjustC {
    --el-color-primary: #ff6f61;
}

.firstDiv {
    text-align: center;
}

.card1 {
    width: 300px !important;
    margin-top: 40px;
    text-align: center;
    border: 2px solid #ff6f61 !important;
    border-radius: 10% !important;
}
</style>
