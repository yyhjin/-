<template>
    <div class="root-div">
        <h2 style="text-align: center">{{ this.userinfo.name }}님의 마이페이지</h2>
        <!--------------------- 회원 정보 ------------------------>
        <div class="user_Info">
            <el-card class="box-card">
                <template #header>
                    <div class="card-header">
                        <span>회원 정보</span>
                        <!-- 회원정보수정 버튼 -->
                        <span class="button-chgprofile" @click="goProfile">회원 정보 수정</span>
                    </div>
                </template>

                <div>
                    <!-- 간략한 회원정보  -->
                    <el-descriptions>
                        <el-descriptions-item label="이름">{{ this.userinfo.name }}</el-descriptions-item>
                        <el-descriptions-item label="아이디">{{ this.userinfo.id }}</el-descriptions-item>
                    </el-descriptions>
                </div>
            </el-card>
        </div>

        <!--------------------- 찜 목록 ------------------------>

        <div class="user_zzim" v-if="userType == '구매자'">
            <el-card class="box-card">
                <template #header>
                    <div class="card-header">
                        <span> 찜 목록</span>
                    </div>
                </template>
                <el-scrollbar height="250px">
                    <div>
                        <!-- zzimcomp -->
                        <div v-if="zzimlist.length == 0" style="text-align: center">
                            <div><h2>찜한 가게가 없어용</h2></div>
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
            <el-card class="box-card">
                <template #header>
                    <div class="card-header">
                        <span>구매 내역</span>
                    </div>
                </template>
                <!-- ordercomp -->
                <el-scrollbar height="300px">
                    <div v-for="(order, idx) in orderList" :key="idx" @click="dialogVisible = true">
                        <OrderComp :order="order" />
                    </div>
                </el-scrollbar>
            </el-card>
        </div>

        <!--------------------- 구매내역/ ------------------------>
    </div>
</template>

<script>
import OrderComp from "@/components/MyPage/OrderComp.vue";
import ZzimComp from "@/components/MyPage/ZzimComp.vue";
import { getCustomer, getJJim } from "@/api/customer.js";
import { useStore } from "vuex";
import { computed, reactive, ref } from "vue";

export default {
    components: { OrderComp, ZzimComp },
    setup() {
        const store = useStore();
        const userNo = computed(() => store.state.userInfo.userNo);
        const userinfo = reactive({
            id: "",
            name: "",
        });
        const dialogVisible = ref(false);
        const handleClose = (done) => {
            done();
        };

        const orderList = computed(() => store.state.orderStore.orderList);
        const getOrder = (no) => {
            store.dispatch(`orderStore/getOrder`, no);
        };

        return { userNo, userinfo, orderList, getOrder, dialogVisible, handleClose };
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
                        this.zzimlist.push({ storeNo: store.storeNo, storeName: store.storeName, storeIdx: store.storeIdx });
                    });
                },
                (error) => {
                    console.log(error);
                }
            );
        //this.getOrder(1);
    },
    methods: {
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
.el-card {
    --el-card-border-color: #ff6f61 !important;
}
.dialog-footer button:first-child {
    margin-right: 10px;
}
.root-div {
    /* width 주고,중앙 정렬. */
    margin-left: auto;
    margin-right: auto;
    width: 340px;
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
    border: 1px solid #ff6f61;
    border-radius: 10px;
    margin-bottom: 10px;
}
</style>
