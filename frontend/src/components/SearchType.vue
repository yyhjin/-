<template>
    <div class="div_big">
        <div class="div_market" v-for="item in storeList" v-bind:key="item">
            <el-card class="box-card" shadow="never">
                <div class="div_item">
                    <img class="mk_img" src="@/assets/defaultshop.png" alt="상점사진" @click="cl_detail(item.no)" style="margin-top: 5px; margin-left: 10px; width: 60px; height: 60px" />

                    <h4 class="mk_name">{{ item.name }}({{ item.item }})</h4>
                    <div style="text-align: left; margin-top: -10px; padding-left: 90px" v-if="item.subject">
                        <h5>{{ item.subject }}</h5>
                        <h6 style="margin-top: -20px">{{ item.intro }}</h6>
                    </div>
                    <div style="text-align: left; margin-top: 50px; padding-left: 90px" v-else>
                        <h5></h5>
                        <h5 style="margin-top: -20px">상점 CLOSE</h5>
                    </div>
                    <div class="mk_enter">
                        <el-button type="danger" @click="cl_enter(item)" style="margin-right: 10px; margin-bottom: 10px; margin-top: -100px" v-if="item.idx == true">입장</el-button>
                        <el-button type="info" @click="cl_no()" style="margin-right: 10px; margin-top: -90px" v-else>입장</el-button>
                    </div>
                </div>
            </el-card>
        </div>
    </div>

    <el-dialog v-model="dialogVisible" title="상점 정보" width="90%">
        <img src="@/assets/defaultshop.png" alt="상점사진" style="width: 200px; height: 200px" v-if="!this.img" />
        <img :src="img" alt="상점사진" style="width: 200px; height: 200px; margin-top: -20px; margin-bottom: 20px" v-else />

        <el-descriptions :column="1" border>
            <el-descriptions-item label="상점명" label-align="center" align="center" label-class-name="my-label" class-name="my-content" width="150px">{{
                this.one_market.storeName
            }}</el-descriptions-item>
            <el-descriptions-item v-if="this.one_market.storeSubject" label="방 제목" label-align="center" align="center">{{ this.one_market.storeSubject }}</el-descriptions-item>
            <el-descriptions-item v-if="this.one_market.storeSubject" label="소개글" label-align="center" align="center">{{ this.one_market.storeIntro }}</el-descriptions-item>

            <el-descriptions-item label="업종" label-align="center" align="center">
                <el-tag size="small">{{ this.one_market.storeCategory }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="주소" label-align="center" align="center">{{ this.one_market.storeAddr }}</el-descriptions-item>
            <el-descriptions-item label="전화 번호" label-align="center" align="center">{{ this.one_market.storePhone }}</el-descriptions-item>
        </el-descriptions>
    </el-dialog>
</template>

<script>
import { computed, reactive, ref } from "vue";
import { useStore } from "vuex";
import { StoreDetail, StoreRoomDetail, getIMG } from "@/api/store.js";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

export default {
    name: "SearchType",
    data() {
        return {
            item: "",
        };
    },

    setup() {
        const store = useStore();
        const router = useRouter();
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
        return {
            img,
            router,
            one_market,
            storeList,
            dialogVisible,
            handleClose,
            open,
        };
    },

    methods: {
        cl_no() {
            this.open("상점 CLOSE");
        },
        cl_enter(item) {
            //this.item = item;
            const params = { storeNo: item.no, storeName: item.name };
            console.log(params);
            this.router.push({
                name: "customer_room",
                params: params,
            });
        },
        cl_detail(no) {
            this.dialogVisible = true;
            console.log(no);
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

            getIMG(
                no,
                (response) => {
                    console.log(response);
                    this.img = response.data;
                },
                (error) => {
                    console.log(error);
                }
            );

            console.log("자세히");
        },
    },
};
</script>

<style scoped>
.div_item {
    margin: -20px;
}

.dialog-footer button:first-child {
    margin-right: 10px;
}

.box-card {
    height: 100px;
    margin-bottom: 10px;
}

.div_market {
    width: 300px;
    height: 120px;
}

.mk_img {
    float: left;
    width: 80px;
    height: 80px;
    margin-left: 5px;
}

.mk_name {
    text-align: left;
    margin-left: 90px;
    margin-top: 10px;
}

.mk_enter {
    text-align: right;
    margin-top: 10px;
}
</style>
