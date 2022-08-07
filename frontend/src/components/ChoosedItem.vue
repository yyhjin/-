<template>
    <div>
        <el-table :data="SellList" height="280" style="width: 100%">
            <el-table-column type="index" />
            <el-table-column prop="name" label="품목" width="120" />
            <el-table-column prop="count" label="단위" width="120" />
            <el-table-column prop="price" label="금액" width="120" />
            <el-table-column>
                <template v-slot="scope">
                    <el-button color="#FF6F61" round @click="rmSellList(scope.$index)">삭제</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { useStore } from "vuex";

export default {
    name: "ChoosedItem",
    setup() {
        const store = useStore();
        const SellList = store.getters["root/getSellList"];

        const rmSellList = (value) => store.commit("root/setReSellList", value);

        return { SellList, rmSellList };
    },
};
</script>

<style scoped></style>
