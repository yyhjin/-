<template>
    <el-scrollbar max-height="600px">
        <div class="content">
            <div class="div_item">
                <h4>판매 품목</h4>
                <div>
                    <button class="btn_fruit" v-for="item in db_data" v-bind:key="item" @click="cl_fruit(item.item)">{{ item.item }}</button>
                </div>
            </div>
            <hr />
            <div class="div_select" style="margin-bottom: 30px">
                <!--<div class="hd_select">품목 단위 가격 수량</div>-->
                <div class="md_select">
                    <h2>{{ selectItem.name }}</h2>
                    <el-input class="input_select" v-model="selectItem.count" placeholder="단위" style="margin-left: 5\40px" />
                    <el-input class="input_select" v-model="selectItem.price" placeholder="가격" />

                    <el-button color="#FF6F61" round class="btn_add" @click="cl_add(selectItem)">메뉴 추가</el-button>
                </div>
            </div>
            <hr />
            <div class="div_readyItem" style="display: inline-block">
                <h4>메뉴판</h4>
                <div class="div_item">
                    <choosed-item></choosed-item>
                </div>
                <div style="margin-top: 80px">
                    <el-button color="#FF6F61" round class="btn_close" @click="$emit('close-modal')">확인</el-button>
                </div>
            </div>
        </div>
    </el-scrollbar>
</template>

<script>
import ChoosedItem from "@/components/ChoosedItem.vue";
import { useStore } from "vuex";
import { reactive } from "vue";

export default {
    name: "ContentView",
    components: {
        ChoosedItem,
    },
    props: {
        msg: String,
    },
    setup() {
        const store = useStore();
        //const selectItem = ref();
        const selectItem = reactive({
            name: "품목",
            count: "",
            price: "",
        });
        const SellList = store.getters["root/getSellList"];
        const addList = (value) => store.commit("root/setSellList", value);

        function cl_fruit(item) {
            this.selectItem.name = item;
            console.log(this.selectItem);
        }

        function cl_add(selectItem) {
            this.addList(selectItem);
            this.selectItem.count = "";
            this.selectItem.price = "";
            this.selectItem.max = "";
            this.selectItem.name = "품목";
        }

        return { selectItem, SellList, addList, cl_fruit, cl_add };
    },
    data() {
        return {
            db_data: [{ item: "사과" }, { item: "배" }, { item: "토마토" }, { item: "복숭아" }, { item: "수박" }, { item: "자두" }, { item: "메론" }],
        };
    },
    methods: {},
};
</script>

<style scoped>
.btn_add {
    margin-left: 30px;
}

.input_select {
    width: 100px;
    margin: 0px 10px;
}

.el-button {
    color: white !important;
}

.btn_fruit {
    width: 100px;
    height: 50px;
    background-color: white;
    border: 1px solid #ff6f61;
    border-radius: 10px;
    margin: 10px;
}

.btn_close {
    height: 40px !important;
    width: 80px;
}
</style>
