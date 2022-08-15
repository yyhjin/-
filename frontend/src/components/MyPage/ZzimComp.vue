<template>
    <div>
        <el-card class="card1">
            <el-row>
                <el-col :span="6">
                    <div class="zzimstore_pic">
                        <el-avatar shape="square" :size="50" style="margin-top: 10px" />
                    </div>
                </el-col>
                <el-col :span="18">
                    <div class="zzimstore_description">
                        <div style="display: inline-block">
                            <h3 style="margin-left: 5px">{{ zzimstore.storeName }}</h3>
                        </div>
                        <div class="zzimstore_btn" style="float: right; margin-top: 15px">
                            <el-button type="danger" round @click="cl_enter(item)" style="margin-right: 10px" v-if="zzimstore.storeName == true">입장</el-button>
                            <el-button type="info" round @click="cl_no()" style="margin-right: 10px" v-else>입장</el-button>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </el-card>
    </div>
</template>
<script>
import { ElMessage } from "element-plus";

export default {
    props: ["zzimstore"],

    setup() {
        const open = (message) => {
            ElMessage({
                showClose: true,
                message: message,
                type: "warning",
            });
        };
        return { open };
    },
    methods: {
        cl_no() {
            this.open("상점 CLOSE");
        },
    },
};
</script>
<style scoped>
.card1 {
    margin-bottom: 10px;
    border: 2px solid rgb(60, 189, 146);
    border-radius: 10px;
}
</style>
