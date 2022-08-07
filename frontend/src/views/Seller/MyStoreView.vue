<template>
    <div class="root_div">
        <h1>내 상점</h1>
        <div class="mystore_btns">
            <el-row>
                <el-col>
                    <el-button class="mystore_btn" type="danger" plain @click="market_open()">가게 오픈</el-button>
                </el-col>
                <el-col>
                    <el-button class="mystore_btn" plain>상점정보 관리</el-button>
                </el-col>
                <el-col>
                    <el-button class="mystore_btn" plain>메뉴 관리</el-button>
                </el-col>
                <el-col>
                    <el-button class="mystore_btn" plain>주문</el-button>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
export default {
    methods: {
        market_open() {
            this.$router.push({ name: "openStore", params: { seller_id: this.$route.params.id } });
        },
    },
};
</script>
<style scoped>
.mystore_btn {
    width: 300px;
    height: 70px !important;
    margin-top: 10px;
    color: black !important;
    font-size: larger;
}
</style>
