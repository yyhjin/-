<template>
  <div class="root_div">
    <h1>내 상점</h1>
    <div class=mystore_profile>
      <el-card>
        <el-row>
        <el-col :span="6" class="profile_box">
          <!-- 프로필 사진이 없다면 기본이미지로 : 백엔드에서 부탁하는게 좋을 듯함.. -->
          <img class="profile_pic" :src="this.shopInfo.profileimg">
        </el-col>
        <el-col :span="18" class="profile_register">
          <div>프로필 사진을 등록하세요</div>
          <el-button type="info">등록</el-button>
        </el-col>
        </el-row>
      </el-card>
    </div>
    <div class="mystore_btns" size="small">
      <el-row>
        <el-col>
          <el-button class="mystore_btn" type="dangernpm" plain @click="routerPush('StoreOpenView')">가게 오픈</el-button>
        </el-col>
        <el-col>
          <el-button class="mystore_btn" plain @click="routerPush('StoreProfileView')">상점정보 관리</el-button>
        </el-col>
        <el-col>
          <el-button class="mystore_btn" plain @click="routerPush('StoreMenuView')">메뉴 관리</el-button>
        </el-col>
        <el-col>
          <el-button class="mystore_btn" plain @click="routerPush('StoreBillsView')">판매 내역</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
//백엔드에서 기본이미지 못 준다고 하면 이걸로 해결해보자.
import defaultimage from '@/assets/defaultshop.png'
export default {
  data(){
    return{
      //vuex에서 가져오기.
      shopInfo:{},
      defaultimage:defaultimage,
    }
  },
  methods:{
    routerPush(to){
      alert(to+"로 이동")
      this.$router.push({
                name: to,
                // params: { user:this.dummy }
            });
    }
    
    
  }
  

}
</script>
<style scoped>
.root_div{
  margin-left:auto; 
  margin-right:auto;
  width:80%;
  
}

.mystore_profile{
  width:320px;
  margin-left:auto; 
  margin-right:auto;
}
.profile_pic{
  width:60px;
  height:60px;
  object-fit: cover;
  border-radius: 70%;
}
.profile_register{
  display:flex;
  flex-direction:column;
  justify-content: space-evenly;
}
.el-card__body{ 
  width:100%;
  display:flex !important;
  flex-direction: row !important;
  
}
.mystore_btn {
  width: 320px;
  height: 70px !important;
  margin-top: 10px;
  color:black !important;
  font-size:larger;

}

</style>
