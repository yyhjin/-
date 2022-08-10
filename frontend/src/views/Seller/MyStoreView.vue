<template>
    <div class="root_div">
        등록 여부<el-radio-group v-model="isRegistered" size="small">
            <el-radio-button label="true" />
            <el-radio-button label="false" />
        </el-radio-group>
        <h1>내 상점</h1>
        <!---------------가게 등록 -------------------->
        <div class="mystore_registered" v-if="isRegistered == 'true'">
            <div class="mystore_profile">
                <el-card>
                    <el-row>
                        <el-col :span="6" class="profile_box">
                            <!-- 프로필 사진이 없다면 기본이미지로 : 백엔드에서 부탁하는게 좋을 듯함.. -->
                            <img class="profile_pic" :src="this.shopInfo.profileimg" />
                        </el-col>
                        <el-col :span="18" class="profile_register">
                            <div>안녕하세요 회원님!</div>
                            <el-button type="info" @click="goProfile">회원 정보 수정</el-button>
                        </el-col>
                    </el-row>
                </el-card>
            </div>
            <div class="mystore_btns">
                <el-button class="mystore_btn" type="danger" plain @click="routerPush('openStore')">가게 오픈</el-button>
                <el-button class="mystore_btn" plain @click="routerPush('store_profile')">상점정보 관리</el-button>
                <el-button class="mystore_btn" plain @click="routerPush('store_menu')">메뉴 관리</el-button>
                <el-button class="mystore_btn" plain @click="routerPush('store_bills')">판매 내역</el-button>
                <!-- <el-row>
        <el-col :span="12"><MyStoreBtnComp/></el-col>
         <el-col :span="12"><MyStoreBtnComp/></el-col>
      </el-row>
       <el-row>
        <el-col :span="12"><MyStoreBtnComp/></el-col>
         <el-col :span="12"><MyStoreBtnComp/></el-col>
      </el-row> -->
            </div>
        </div>
        <!---------------가게 미등록 -------------------->
        <div class="mystore_unregistered" v-else>
            <el-card>
                <h3 style="text-align: center">
                    지금 가게를 등록하세요!
                    <el-button size="small" plain @click="routerPush('store_register')">등록하러가기</el-button>
                </h3>
            </el-card>
        </div>
    </div>
</template>
<script>
//백엔드에서 기본이미지 못 준다고 하면 이걸로 해결해보자.
import defaultimage from "@/assets/defaultshop.png";
// import MyStoreBtnComp from '@/components/Mystore/MyStoreBtnComp.vue'
export default {
    // mounted: {
    //   //axios: vuex 데이터 갱신 할 것.

    // },

    // components:{ MyStoreBtnComp },
    data() {
        return {
            isRegistered: "true",
            //vuex에서 가져오기.
            shopInfo: {},
            menus: [],
            //dummy
            bills: [
                {
                    order_no: 2,
                    customer_id: 1,
                    order_items: [
                        {
                            item_name: "사과",
                            count: 3,
                            price: 2000,
                        },
                        {
                            item_name: "배",
                            count: 2,
                            price: 3000,
                        },
                        {
                            item_name: "수박",
                            count: 1,
                            price: 10000,
                        },
                    ],
                    orderdate: "20220803",
                    status: 1,
                },
            ],
            defaultimage: defaultimage,
        };
    },
    methods: {
        routerPush(to) {
            this.$router.push({
                name: to,
                params: { id: 1 },
            });
        },
        goProfile() {
            this.$router.push({
                name: "sellerProfile",
            });
        },
    },
};
</script>
<style scoped>
.root_div {
    width: 90%;
    max-width: 400px;
    margin: auto;
}

.profile_pic {
    width: 60px;
    height: 60px;
    object-fit: cover;
    border-radius: 70%;
}

/* root */
.profile_register {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
}

.el-card__body {
    width: 100%;
    display: flex !important;
    flex-direction: row !important;
}
.mystore_btn {
    width: 100%;
    height: 70px !important;
    margin-top: 10px;
    font-size: larger;
    margin-left: 0 !important;
}
</style>
