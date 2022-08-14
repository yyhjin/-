<template>
    <!-- TODO:
  1.탈퇴기능 추가여부? 
  -->
    <div class="root_div">
        <el-card class="card">
            <h2>회원정보 수정</h2>
            <div class="modifyMenu">
                <div class="modifyForm">
                    <h5 style="padding-top: 20px">사업자 등록번호</h5>
                    <el-input placeholder="사업자 등록번호" v-model="userinfo.number" class="input_number" />
                    <h5>이름</h5>
                    <el-input placeholder="이름" v-model="userinfo.name" class="input_name" />
                    <h5>연락처</h5>
                    <el-input placeholder="연락처" v-model="userinfo.phone_number" class="input_phone" />
                </div>

                <div style="margin-top: 30px; margin-bottom: 10px">
                    <el-button color="#FF6F61" style="width: 100px" round class="btn_modify" @click="cl_modify">수정</el-button>
                    <el-button style="width: 100px" round class="btn_cancle" @click="cl_cancle">취소</el-button>
                </div>
            </div>
        </el-card>
    </div>
</template>
<script>
import { getSeller, updateSeller } from "@/api/seller.js";
import { useStore } from "vuex";
import { computed, ref, reactive } from "vue";

export default {
    setup() {
        const store = useStore();
        const password = ref("");
        const password_double = ref("");
        const same = ref("확인");
        const userinfo = reactive({
            name: "",
            number: "",
            phone_number: "",
        });

        const userType = computed(() => store.state.userInfo.userType);
        const userNo = computed(() => store.state.userInfo.userNo);

        return { password, password_double, same, userNo, userType, userinfo };
    },

    watch: {},
    created() {
        getSeller(
            this.userNo,
            (response) => {
                console.log(response);
                console.log(this.userNo);
                this.userinfo.number = response.data.businessNumber;
                this.userinfo.name = response.data.sellerName;
                this.userinfo.phone_number = response.data.sellerPhone;
            },
            (error) => {
                console.log(error);
            }
        );
    },

    methods: {
        cl_modify() {
            //alert("등록성공");
            //console.log(this.userNo);

            //console.log("등록 정보: " + JSON.stringify(this.userinfo));
            const params = {
                businessNumber: this.userinfo.number,
                sellerName: this.userinfo.name,
                sellerPhone: this.userinfo.phone_number,
            };
            console.log(params);
            updateSeller(
                this.userNo,
                params,
                (response) => {
                    console.log(response);
                },
                (error) => {
                    console.log(error);
                }
            );

            //this.$router.go(-1);
        },
        cl_cancle() {
            this.$router.go(-1);
        },
    },
};
</script>
<style scoped>
.card {
    margin: auto;
}
.adjustC {
    --el-color-primary: #ff6f61;
}
.card {
    width: 300px !important;
    margin-top: 40px;
    text-align: center;
}

.btn_address {
    float: right;
    margin-top: 20px;
    width: 80px;
}

.modifyMenu {
    display: inline-block;
}

.modifyMenu h5 {
    text-align: left;
    margin-bottom: 10px;
}

.radioGroup {
    text-align: left !important;
}
</style>
