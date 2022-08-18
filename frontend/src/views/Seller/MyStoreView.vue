<template>
  <div>
    <!-- 등록 여부<el-radio-group v-model="isRegistered" size="small">
      <el-radio-button label="true" />
      <el-radio-button label="false" />
    </el-radio-group> -->
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <h2>내 상점</h2>
        </div>
        <span class="button-chgprofile" @click="goProfile">회원 정보 수정</span>
      </template>

      <!---------------가게 등록 -------------------->
      <div class="mystore_registered" v-if="isRegistered">
        <div class="mystore_profile">
          <el-row>
            <el-col :span="6" class="profile_box">
              <!-- 프로필 사진이 없다면 기본이미지로 : 백엔드에서 부탁하는게 좋을 듯함.. -->
              <img class="profile_pic" :src="this.img" />
            </el-col>
            <el-col :span="18" class="profile_register">
              <div style="text-align: left">
                <h4 style="margin-top: -10px">
                  상점명 : {{ this.shopInfo.storeName }}
                </h4>
                <h4 style="margin-top: -10px">
                  업종 : {{ this.shopInfo.storeCategory }}
                </h4>
                <h4 style="margin-top: -10px; margin-bottom: -5px">
                  위치 : {{ this.shopInfo.storeAddr }}
                </h4>
              </div>
            </el-col>
          </el-row>
        </div>
        <!-- smartphone 사이즈 일 때. -->
      </div>
      <!---------------가게 미등록 -----------                            <el-button type="info" style="width: 200px; margin-left: 75px" @click="routerPush('store_profile', { storeNo: this.shopInfo.storeNo })">수정</el-button>
--------->
      <div class="mystore_unregistered" v-else>
        <h3 style="text-align: center">지금 가게를 등록하세요!</h3>
        <el-button
          round
          color="#e07c49"
          @click="routerPush('store_register')"
          style="color: white"
          >등록하기</el-button
        >
      </div>
    </el-card>
    <div v-if="isRegistered">
      <el-card
        class="box-card"
        @click="cl_open()"
        style="margin-top: 20px; text-align: center"
      >
        <h2>가게 오픈</h2>
      </el-card>
      <el-card
        class="box-card"
        @click="
          routerPush('store_profile', {
            storeNo: this.shopInfo.storeNo,
            img: this.img,
          })
        "
        style="margin-top: 10px; text-align: center"
      >
        <h2>상점정보 관리</h2>
      </el-card>
      <el-card
        class="box-card"
        @click="routerPush('store_menu', { storeNo: this.shopInfo.storeNo })"
        style="margin-top: 10px; text-align: center"
      >
        <h2>메뉴 관리</h2>
      </el-card>
      <el-card
        class="box-card"
        @click="routerPush('store_bills', { storeNo: this.shopInfo.storeNo })"
        style="margin-top: 10px; text-align: center"
      >
        <h2>판매 내역</h2>
      </el-card>
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
</template>
<script>
/*
data flow: 
seller id(from vuex)
=>storeNo ->없으면 등록화면으로. 
=>BillList,Menus
*/

import { getStoreBySellerNo, getIMG } from "@/api/store.js";
import { menuList } from "@/api/item.js";
import { sellerOrderList } from "@/api/order.js";
import { computed } from "vue";
import { useStore } from "vuex";

// import MyStoreBtnComp from '@/components/Mystore/MyStoreBtnComp.vue'
export default {
  mounted() {
    this.loadData(this.$store.state.userInfo.userNo);
  },
  setup() {
    const store = useStore();
    const storeNo = computed(() => store.state.orderStore.storeNo);

    const setStoreNo = (no) => {
      store.commit(`orderStore/SET_STORENO`, no);
    };

    return { storeNo, setStoreNo };
  },

  // components:{ MyStoreBtnComp },
  data() {
    return {
      isRegistered: true,
      //vuex에서 가져오기.
      shopInfo: {},
      menuList: [],
      billList: [],
      img: "",
    };
  },
  methods: {
    goProfile() {
      this.$router.push({
        name: "sellerProfile",
        // params: { user:this.dummy }
      });
    },

    cl_open() {
      this.setStoreNo(this.shopInfo.storeNo);
      console.log(this.storeNo);
      this.routerPush("openStore", { storeNo: this.shopInfo.storeNo });
    },
    routerPush(to, params) {
      console.log(params);
      this.$router.push({
        name: to,
        params: params,
      });
    },
    //this.$store.state.userInfo.userNo
    loadData(sellerNo) {
      console.log(sellerNo + "의 상점정보 로드");
      getStoreBySellerNo(
        sellerNo,
        (res) => {
          this.shopInfo = res.data;

          //상점정보가 없다면 등록페이지로 넘어가도록
          if (res.data == "") {
            this.isRegistered = false;
          }
          console.log(`상점정보: ${JSON.stringify(res.data)}`);
          const storeNo = res.data.storeNo;

          //상점이 등록된 상태라면 나머지 정보도 조회..
          if (res.data.length != 0) {
            console.log("상점정보 확인 완료.추가정보 조회");
            this.loadBillList(storeNo);
            this.loadMenuList(storeNo);
            getIMG(
              storeNo,
              (response) => {
                this.img = response.data;
              },
              (error) => {
                console.log(error);
              }
            );
          }
        },
        //catch
        (err) => {
          console.log(err);
        }
      );
    },
    loadBillList(storeNo) {
      sellerOrderList(
        storeNo,
        (res) => {
          this.billList = res.data;
          console.log(`주문서조회완료+${JSON.stringify(res.data)}`);
        },
        //catch
        (err) => {
          console.log(err);
        }
      );
    },
    loadMenuList(storeNo) {
      menuList(
        storeNo,
        (res) => {
          this.menuList = res.data;
          console.log(`메뉴조회완료+${JSON.stringify(res.data)}`);
        },
        //catch
        (err) => {
          console.log(err);
        }
      );
    },
  },
};
</script>
<style scoped>
.root_div {
  text-align: center;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 30px;
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

.card-box {
  width: 500px;
  margin: auto;
  margin-bottom: 20px;
}

.div_card {
  width: 500px;
  margin: auto;
  margin-bottom: 20px;
}

@media all and (min-width: 768px) {
  .box-card {
    margin: auto;
    width: 350px;
  }
}
.button-chgprofile {
  font-size: small;
  float: right;
  text-decoration: underline;
  margin-top: -20px;
}
</style>
