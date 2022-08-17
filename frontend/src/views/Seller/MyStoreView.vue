<template>
  <div>
    <!-- 등록 여부<el-radio-group v-model="isRegistered" size="small">
      <el-radio-button label="true" />
      <el-radio-button label="false" />
    </el-radio-group> -->
    <h2>내 상점</h2>
    <!---------------가게 등록 -------------------->
    <div class="mystore_registered" v-if="isRegistered">
      <div class="mystore_profile">
        <el-card>
          <el-row>
            <el-col :span="6" class="profile_box">
              <!-- 프로필 사진이 없다면 기본이미지로 : 백엔드에서 부탁하는게 좋을 듯함.. -->
              <img class="profile_pic" :src="this.shopInfo.profileimg" />
            </el-col>
            <el-col :span="18" class="profile_register">
              <div>프로필 사진을 등록하세요</div>
              <el-button type="info">등록</el-button>
            </el-col>
          </el-row>
        </el-card>
      </div>
      <!-- smartphone 사이즈 일 때. -->
      <div class="mystore_btns">
        <el-button
          class="mystore_btn"
          type="danger"
          plain
          @click="routerPush('openStore')"
          >가게 오픈</el-button
        >
        <el-button
          class="mystore_btn"
          plain
          @click="
            routerPush('store_profile', { storeNo: this.shopInfo.storeNo })
          "
          >상점정보 관리</el-button
        >
        <el-button
          class="mystore_btn"
          plain
          @click="routerPush('store_menu', { storeNo: this.shopInfo.storeNo })"
          >메뉴 관리</el-button
        >
        <el-button
          class="mystore_btn"
          plain
          @click="routerPush('store_bills', { storeNo: this.shopInfo.storeNo })"
          >판매 내역</el-button
        >
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
          <el-button
            size="small"
            @click="routerPush('store_register')"
            style="margin-top: 20px"
            color="#42413e"
            >등록하기</el-button
          >
        </h3>
      </el-card>
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

import { getStoreBySellerNo } from "@/api/store.js";
import { menuList } from "@/api/item.js";
import { sellerOrderList } from "@/api/order.js";
// import MyStoreBtnComp from '@/components/Mystore/MyStoreBtnComp.vue'
export default {
  mounted() {
    this.loadData(this.$store.state.userInfo.userNo);
  },

  // components:{ MyStoreBtnComp },
  data() {
    return {
      isRegistered: true,
      //vuex에서 가져오기.
      shopInfo: {},
      menuList: [],
      billList: [],
    };
  },
  methods: {
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
