<template>
    <div class="div_div">
        <div>
            <el-page-header :icon="ArrowLeft" content="가게 오픈" @back="this.$router.go(-1)" />
        </div>

        <div class="div_big">
            <div class="div_intro">
                <h2>방 제목 :</h2>
                <el-input v-model="this.intro" :rows="1" type="textarea" placeholder="제목을 적어주세요." class="input_intro" />
            </div>

            <div class="div_subject">
                <h2>소개 문구 :</h2>
                <el-input v-model="this.subject" :rows="2" type="textarea" placeholder="소개를 적어주세요." class="input_subject" />
            </div>

            <div class="div_menu">
                <div>
                    <h2>판매 목록 :</h2>
                    <el-button class="btn_menu" @click="dialogVisible = true">메뉴 추가</el-button>
                </div>

                <div style="display: inline-block">
                    <choosed-item></choosed-item>
                </div>
            </div>

            <div style="width: 200px; margin: auto">
                <el-button color="#FF6F61" class="btn_ok" @click="cl_add" style="">가게 오픈</el-button>
            </div>
        </div>
    </div>
    <el-dialog v-model="dialogVisible" title="판매 목록 수정" width="70%">
        <el-card class="box-card">
            <template #header>
                <el-scrollbar>
                    <div class="scrollbar-flex-content">
                        <el-button class="btn_item scrollbar-demo-item" v-for="item in sellList" :key="item" style="color: black !important" @click="btn_item(item)">
                            {{ item.itemName }}
                        </el-button>
                    </div>
                </el-scrollbar>
            </template>
            <div style="text-align: center">
                <el-input v-model="Item.itemName" placeholder="품목 / 수량" style="width: 100px; margin-right: 50px" />
                <el-input v-model="Item.price" placeholder="가격" style="width: 100px" />
                <el-button style="color: black !important" @click="cl_add(Item)">추가</el-button>
            </div>
        </el-card>
        <el-card style="margin-top: 30px">
            <div>
                <choosed-item></choosed-item>
            </div>
        </el-card>
    </el-dialog>
    <el-button style="color: black !important" @click="cl_ok()">확인</el-button>
</template>

<script>
import { useStore } from "vuex";
import { computed, ref } from "vue";
import ChoosedItem from "@/components/ChoosedItem.vue";
import { updateIdx, updateRoom } from "@/api/store";

export default {
    name: "StoreOpenView",
    components: {
        ChoosedItem,
    },
    setup() {
        const store = useStore();
        const intro = ref();
        const subject = ref();
        const Item = ref({
            itemNo: "",
            itemName: "",
            price: "",
            recent: "",
        });

        const sellList = computed(() => store.state.orderStore.sellList);

        //가게오픈할때 가져가기
        const openList = computed(() => store.state.orderStore.openList);

        const dialogVisible = ref(false);

        return { dialogVisible, intro, subject, sellList, openList, Item };
    },
    data() {
        return {
            fileList: [],
        };
    },
    created() {
        this.storeNo = this.$route.params.storeNo;
    },
    mounted() {},

    methods: {
        //아이템 선택
        btn_item(item) {
            this.Item.itemNo = item.itemNo;
            this.Item.itemName = item.itemName;
            this.Item.price = item.price;
            this.Item.recent = item.recent;
        },

        cl_ok() {
            console.log(this.fileList);

            updateRoom(
                this.storeNo,
                { storeIntro: this.intro, storeNo: this.storeNo, storeSubject: this.subject },
                (res) => {
                    console.log(res);
                },
                (err) => {
                    console.log(err);
                }
            );

            updateIdx(
                this.storeNo,
                (res) => {
                    console.log(res);
                },
                (err) => {
                    console.log(err);
                }
            );

            // 오픈버튼 임시로 만들어 둿어요.
            // this.$router.push({
            //     name: "seller_room",
            //     params: { storeNo: 29 },
            // });
        },
        // openlist에 추가
        cl_add(item) {
            this.openList.push({
                itemNo: item.itemNo,
                itemName: item.itemName,
                price: item.price,
                recent: true,
            });
        },

        item_delete(file) {
            for (var i = 0; i < this.fileList.length; i++) {
                if (file.name == this.fileList[i].name) {
                    this.fileList.splice(i, 1);
                }
            }
        },
    },
};
</script>

<style scoped>
.scrollbar-flex-content {
    display: flex;
}
.scrollbar-demo-item {
    flex-shrink: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100px;
    height: 50px;
    margin: 10px;
    text-align: center;
    border-radius: 4px;
    background: var(--el-color-danger-light-9);
    color: var(--el-color-danger);
}

.div_div {
    text-align: center;
}

.div_big {
    width: 500px;
    display: inline-block;
}
.div_intro {
    margin-top: 30px;
}

.div_intro h2 {
    text-align: left;
    margin-bottom: 20px;
}

.div_subject h2 {
    text-align: left;
    margin-bottom: 20px;
}

.div_menu {
    margin-top: 30px;
}

.div_menu h2 {
    text-align: left;
    margin-bottom: 40px;
    float: left;
}

.btn_menu {
    margin-top: 20px;
    margin-bottom: 20px;
    margin-right: 10px;
    width: 80px;
    height: 40px !important;
    float: right;
}

.btn_ok {
    margin-top: 50px;
    margin-bottom: 30px;
    width: 100px;
}
.el-button {
    color: white !important;
}
</style>
