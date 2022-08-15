<template>
    <div class="firstDiv">
        <el-space wrap>
            <el-card class="card">
                <h1>회원가입</h1>
                <div class="btn_group">
                    <div class="div_customer">
                        <button type="button" class="btn_customer" @click="cl_customer"><img src="@/assets/소비자.png" /></button>
                    </div>
                    <div class="div_seller">
                        <button type="button" class="btn_seller" @click="cl_seller"><img src="@/assets/판매자.png" /></button>
                    </div>
                </div>
            </el-card>
        </el-space>
    </div>
</template>

<script>
export default {
    name: "SelectState",

    data() {
        return {
            userType: "",
        };
    },

    mounted() {},

    methods: {
        cl_customer() {
            console.log("소비자");
            this.userType = "Customer";
            this.$router.push({ name: "joinCustomer", params: { userType: this.userType } });
        },

        cl_seller() {
            console.log("판매자");
            this.userType = "Seller";
            this.$router.push({ name: "joinSeller", params: { userType: this.userType } });
        },
    },
};
</script>

<style scoped>
.card {
    margin-top: 60px;
    border-radius: 30px;
    border: 2px solid #ff6f61;
}

.firstDiv {
    text-align: center;
}
.btn_group {
    margin-top: 100px;
}

.btn_group button {
    height: 150px;
    background-color: #ff6f61;
}

.div_customer {
    width: 100px;
    float: left;
}

.div_seller {
    width: 100px;
    float: right;
    margin-bottom: 100px;
}

img {
    max-width: 100%;
    max-height: 100%;
}
</style>
