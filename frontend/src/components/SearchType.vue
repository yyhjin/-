<template>
    <div class="div_big">
        <div class="div_market" v-for="item in storeList" v-bind:key="item">
            <img class="mk_img" src="@/assets/defaultshop.png" alt="상점사진" @click="cl_detail" style="margin-top: 5px; margin-left: 5px" />

            <h3 class="mk_name">{{ item.name }}</h3>
            <h4 class="mk_items">{{ item.item }}</h4>
            <div style="float: right">
                <el-button type="success" round size="small" @click="cl_detail(item.no)">정보</el-button>
                <el-button type="danger" round size="small" @click="cl_enter(item)" style="margin-right: 10px" v-if="item.idx == true">입장</el-button>
                <el-button type="info" round size="small" @click="cl_no" style="margin-right: 10px" v-else>입장</el-button>
            </div>
        </div>
    </div>

    <el-dialog v-model="dialogVisible" title="방 정보" width="90%" :before-close="handleClose">
        <div>
            <div>
                <h3>{{ this.one_market.storeName }}</h3>
            </div>
            <div style="display: inline-block">
                <img class="mk_img" src="@/assets/defaultshop.png" alt="상점사진" />
            </div>
            <div>
                <h4>판매 품목 : {{ this.one_market.storeCategory }}</h4>
                <h4>주소 : {{ this.one_market.storeAddr }}</h4>
                <h4>전화 번호 : {{ this.one_market.storePhone }}</h4>
                <h3 style="color: red">오늘의 제목 : {{ this.one_market.storeSubject }}</h3>
                <h3 style="color: red">소개글 : {{ this.one_market.storeIntro }}</h3>
            </div>
        </div>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">닫기</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script>
import { computed, reactive, ref } from "vue";
import { useStore } from "vuex";
import { StoreDetail, StoreRoomDetail } from "@/api/store.js";
import { ElMessage } from "element-plus";

export default {
    name: "SearchType",
    data() {
        return {
            //items: [{ name: "OO수산" }, { name: "OO청과물" }],
            item: "",
        };
    },

    setup() {
        const store = useStore();
        const one_market = reactive({
            storeName: "",
            storeCategory: "",
            storePhone: "",
            storeAddr: "",
            storeSubject: "",
            storeIntro: "",
        });
        const img = ref();
        const dialogVisible = ref(false);
        const handleClose = (done) => {
            done();
        };
        const open = (message) => {
            ElMessage({
                showClose: true,
                message: message,
                type: "warning",
            });
        };
        const storeList = computed(() => store.state.storeInMarket.stores);
        return { img, one_market, storeList, dialogVisible, handleClose, open };
    },

    methods: {
        cl_no() {
            this.open("라이브 중이 아닙니다");
        },
        cl_enter(item) {
            this.item = item;
            console.log(this.item);
        },
        cl_detail(no) {
            this.dialogVisible = true;
            StoreDetail(
                no,
                (response) => {
                    console.log(response);
                    this.one_market.storeAddr = response.data.storeAddr;
                    this.one_market.storeName = response.data.storeName;
                    this.one_market.storePhone = response.data.storePhone;
                    this.one_market.storeCategory = response.data.storeCategory;
                },
                (error) => {
                    console.log(error);
                }
            );
            StoreRoomDetail(
                no,
                (response) => {
                    console.log(response);
                    this.one_market.storeSubject = response.data.storeSubject;
                    this.one_market.storeIntro = response.data.storeIntro;
                },
                (error) => {
                    console.log(error);
                }
            );
            /*
            getIMG(
                no,
                (response) => {
                    console.log(response);
                    this.img = response;
                },
                (error) => {
                    console.log(error);
                }
            );
            */
            console.log("자세히");
        },
    },
};
</script>

<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}

.div_big {
    margin: auto;
    padding-top: 10px;
}

.div_market {
    border: 1px solid #ff6f61;
    border-radius: 20px;
    width: 300px;
    height: 90px;
    margin: 20px 0px;
}

.mk_img {
    float: left;
    width: 80px;
    height: 80px;
}

.mk_name {
    float: left;
    margin-left: 10px;
    margin-top: 10px;
}

.mk_like {
    text-align: right;
    margin-right: 20px;
    margin-top: 10px;
}

.mk_items {
    margin-top: 10px;
}
</style>
