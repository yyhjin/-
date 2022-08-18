<template>
    <div class="root-div">
        <!--------------------- 회원 정보 ------------------------>
        <div class="user_Info">
            <el-card class="box-card" shadow="never">
                <template #header>
                    <div class="card-header">
                        <span>회원 정보</span>
                        <!-- 회원정보수정 버튼 -->
                        <span class="button-chgprofile" @click="goProfile">회원 정보 수정</span>
                    </div>
                </template>

                <div style="margin-bottom: 10px">
                    <!-- 간략한 회원정보  -->
                    <el-descriptions>
                        <el-descriptions-item label="아이디">{{ this.userinfo.id }}</el-descriptions-item>
                        <el-descriptions-item label="이름">{{ this.userinfo.name }}</el-descriptions-item>
                    </el-descriptions>
                </div>

                <el-row>
                    <el-col :span="7"> 비밀번호 </el-col>
                    <el-col :span="5">
                        <span style="font-size: x-small" class="button-chgprofile" @click="dialogVisibleb = true">변경하기</span>
                    </el-col>
                </el-row>
            </el-card>
        </div>

        <!--------------------- 찜 목록 ------------------------>

        <div class="user_zzim">
            <el-card class="box-card" shadow="never">
                <template #header>
                    <div class="card-header">
                        <span> 찜 목록</span>
                    </div>
                </template>
                <el-scrollbar height="250px">
                    <div>
                        <!-- zzimcomp -->
                        <div v-if="zzimlist.length == 0" style="text-align: center">
                            <el-empty description="찜한 가게가 없습니다." style="margin-top: -35px" />
                        </div>
                        <div v-else>
                            <div v-for="(zzimstore, idx) in zzimlist" :key="idx">
                                <ZzimComp :zzimstore="zzimstore" />
                            </div>
                        </div>
                    </div>
                </el-scrollbar>
            </el-card>
        </div>

        <!--------------------- 구매내역 ------------------------>
        <div class="user_orders">
            <el-card class="box-card" shadow="never">
                <template #header>
                    <div class="card-header">
                        <span>구매 내역</span>
                    </div>
                </template>
                <!-- ordercomp -->
                <el-scrollbar height="250px">
                    <div v-for="(order, idx) in orderList" :key="idx" @click="dialogVisible = true">
                        <OrderComp :order="order" />
                    </div>
                </el-scrollbar>
            </el-card>
        </div>

        <!--------------------- 구매내역/ ------------------------>
        <!--------------------- 비밀번호 확인 모달 ------------------------>
        <el-dialog v-model="dialogVisibleb" title="확인" width="80%">
            <h5 style="margin-left: 5px; margin-top: -10px">비밀번호 재입력</h5>
            <el-input v-model="pass" type="password" placeholder="password" show-password @keyup.enter="confirm()" />
            <template #footer>
                <span class="dialog-footer">
                    <el-button style="background-color: #42413e; color: white" @click="confirm()">확인</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import OrderComp from "@/components/MyPage/OrderComp.vue";
import ZzimComp from "@/components/MyPage/ZzimComp.vue";
import { loginCustomer } from "@/api/customer";
import { getCustomer, getJJim } from "@/api/customer.js";
import { useStore } from "vuex";
import { computed, reactive, ref } from "vue";
import { ElMessage } from "element-plus";

export default {
    components: { OrderComp, ZzimComp },
    setup() {
        const store = useStore();
        const pass = ref();
        const userNo = computed(() => store.state.userInfo.userNo);
        const userinfo = reactive({
            id: "",
            name: "",
        });
        const dialogVisibleb = ref(false);
        const handleClose = (done) => {
            done();
        };

        const orderList = computed(() => store.state.orderStore.orderList);
        const getOrder = (no) => {
            store.dispatch(`orderStore/getOrder`, no);
        };

        const open = (message) => {
            ElMessage({
                showClose: true,
                message: message,
                type: "error",
            });
        };

        return {
            userNo,
            userinfo,
            orderList,
            getOrder,
            dialogVisibleb,
            handleClose,
            pass,
            open,
        };
    },

    data() {
        return {
            // dummy data from vuex
            userType: this.$store.state.userinfo.userType,
            zzimlist: [],
        };
    },
    created() {
        getCustomer(
            this.userNo,
            (response) => {
                console.log(response);
                console.log(this.userNo);
                this.userinfo.id = response.data.customerId;
                this.userinfo.name = response.data.customerName;
            },
            (error) => {
                console.log(error);
            }
        ),
            getJJim(
                this.userNo,
                (response) => {
                    console.log(response.data);
                    response.data.forEach((store) => {
                        this.zzimlist.push({
                            storeNo: store.storeNo,
                            storeName: store.storeName,
                            storeIdx: store.storeIdx,
                        });
                    });
                },
                (error) => {
                    console.log(error);
                }
            );
        //this.getOrder(1);
    },
    methods: {
        confirm() {
            loginCustomer(
                { username: this.userinfo.id, password: this.pass },
                (response) => {
                    if (response.data.response == "success") {
                        this.goPass();
                    } else {
                        this.open("비밀번호가 다릅니다.");
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        goPass() {
            this.$router.push({
                name: "CustomerPass",
            });
        },
        goProfile() {
            this.$router.push({
                name: "profile",
                // params: { user:this.dummy }
            });
        },
    },
};
</script>
<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.button-chgprofile {
    font-size: x-small;
    text-decoration: underline;
}
.box-card {
    margin-bottom: 10px;
}
</style>
