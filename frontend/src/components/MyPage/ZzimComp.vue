<template>
    <div>
        <el-card class="card1" shadow="always">
            <el-row>
                <el-col :span="10">
                    <div style="display: inline-block">
                        <h3 @click="cl_zzim(zzimstore)">{{ zzimstore.storeName }}</h3>
                    </div>
                </el-col>
                <el-col :span="14">
                    <div class="zzimstore_description" style="text-align: right">
                        <div class="zzimstore_btn" style="margin-top: 15px">
                            <el-button style="background-color: #e07c49; color: white" round @click="cl_enter(zzimstore)" v-if="zzimstore.storeIdx == true">입장</el-button>
                            <el-button type="info" round @click="cl_no()" v-else>입장</el-button>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </el-card>
    </div>
    <el-dialog v-model="dialogVisible" title="상점 정보" width="90%">
        <el-descriptions :column="1" border>
            <el-descriptions-item label="상점명" label-align="center" align="center" label-class-name="my-label" class-name="my-content" width="150px">{{ this.store.name }}</el-descriptions-item>
            <el-descriptions-item label="전화 번호" label-align="center" align="center">{{ this.store.phone }}</el-descriptions-item>
            <el-descriptions-item label="업종" label-align="center" align="center">
                <el-tag size="small">{{ this.store.category }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="주소" label-align="center" align="center">{{ this.store.addr }}</el-descriptions-item>
        </el-descriptions>
    </el-dialog>
</template>
<script>
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { StoreDetail } from "@/api/store";
import { ref } from "vue";

export default {
    props: ["zzimstore"],

    setup() {
        const router = useRouter();
        const dialogVisible = ref(false);
        const open = (message) => {
            ElMessage({
                showClose: true,
                message: message,
                type: "warning",
            });
        };
        const store = ref({
            addr: "",
            category: "",
            name: "",
            phone: "",
            img: "",
        });
        return { open, router, dialogVisible, store };
    },
    methods: {
        cl_zzim(zzimstore) {
            this.dialogVisible = true;
            console.log(zzimstore);
            StoreDetail(
                zzimstore.storeNo,
                (response) => {
                    console.log(response);
                    this.store.addr = response.data.storeAddr;
                    this.store.category = response.data.storeCategory;
                    this.store.name = response.data.storeName;
                    this.store.phone = response.data.storePhone;
                    this.store.img = response.data.storeImg;
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        cl_no() {
            this.open("상점 CLOSE");
        },
        cl_enter(item) {
            console.log(item);
            const params = {
                storeNo: item.storeNo,
                storeName: item.storeName,
            };
            console.log(params);
            this.router.push({
                name: "customer_room",
                params: params,
            });
        },
    },
};
</script>
<style scoped>
.card1 {
    margin-bottom: 10px;
}
.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>
