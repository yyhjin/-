<template>
    <div>
        <el-table :data="openList" height="280" style="width: 100%; margin: auto">
            <el-table-column type="index" width="80" />
            <el-table-column prop="itemName" label="품목/수량" width="170" />
            <el-table-column prop="price" label="금액" width="140" />
            <el-table-column label="삭제">
                <template v-slot="scope">
                    <el-button style="color: black !important; border: 0px" circle :icon="Delete" @click="resellList(scope.$index)"> </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "vue";
import { Delete } from "@element-plus/icons-vue";

export default {
    // props:[`sellList`,'openList'],
    name: "ChoosedItem",
    setup() {
        const store = useStore();
        const storeNo = computed(() => store.state.orderStore.storeNo);
        const sellList = computed(() => store.state.orderStore.sellList);
        const openList = computed(() => store.state.orderStore.openList);

        const setSellList = (no) => {
            store.dispatch(`orderStore/getSellList`, no);
        };

        const reopenlist = () => {
            store.commit(`orderStore/CLEAR_OPENLIST`);
        };

        const resellList = (value) => {
            store.commit(`orderStore/REMOVE_ITEM`, value);
        };

        return {
            storeNo,
            sellList,
            openList,
            setSellList,
            resellList,
            reopenlist,
            Delete,
        };
    },

    created() {
        console.log("hi");
        this.setSellList(this.storeNo);
        this.reopenlist();
        for (var i = 0; i < this.sellList.length; i++) {
            if (this.sellList[i].recent == true) {
                this.openList.push({
                    itemNo: this.sellList[i].itemNo,
                    itemName: this.sellList[i].itemName,
                    price: this.sellList[i].price,
                    recent: this.sellList[i].recent,
                });
            }
        }
    },
    methods: {
        cl() {
            console.log(this.openList);
        },
    },
};
</script>

<style scoped>
.el-button {
    color: white !important;
}
</style>
