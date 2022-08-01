<template>
  <div class=root-div>
    <h2>{{dummy.name}}#{{$route.params.id}}님의 page</h2>

<!--------------------- 회원 정보 ------------------------>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>회원 정보</span>
          <!-- 회원정보수정 버튼 -->
          <span class="button-chgprofile" @click="goProfile">회원 정보 수정</span>
        </div>
      </template>
       
       <div class="userinfo-brief">
        <!-- 간략한 회원정보  -->
        {{dummy.name}}{{dummy.id}}
       </div>
    </el-card>
<!--------------------- 찜 목록 ------------------------>
     <el-card class="box-card">
      <template #header>
        <div class="card-header"> 
          <span> 찜 목록</span>
        </div>
      </template>
      <div>
        <!-- zzimcomp -->
        <div v-if="zzimlist.length==0 ">
          <div>찜한 가게가 없어용</div>
        </div>
        <div v-else>
          <div v-for="(zzimstore,idx) in zzimlist" :key="idx">
          <ZzimComp :zzimstore="zzimstore"/>
        </div>

        </div>
        
      </div>
      </el-card>
      



<!--------------------- 구매내역 ------------------------>
    <el-card class="box-card">
      <template #header>
        <div class="card-header"> 
          <span>구매 내역</span>
        </div>
      </template>
      <!-- ordercomp -->
      <div v-for="(dummy,idx) in dummyorder" :key="idx">
        <OrderComp :order="dummy"/>
      </div>
    </el-card>


  </div>
</template>


<script>
import OrderComp from '@/components/MyPage/OrderComp.vue';
import ZzimComp from  '@/components/MyPage/ZzimComp.vue'

export default {
    components: { OrderComp, ZzimComp },
    
  data(){
    return{
      // dummy data from vuex
      dummy : this.$store.state.userinfo,
      zzimlist:[],
      dummyorder: [{
                order_no: 1,
                order_items: [
                    { item_no: 1, item_name: "사과", count: 3, orderprice: 2000 },
                    { item_no: 2, item_name: "포도", count: 2, orderprice: 3000 },
                    { item_no: 3, item_name: "수박", count: 1, orderprice: 10000 },
                    { item_no: 4, item_name: "샤인머스켓", count: 1, orderprice: 20000 }
                ],
                orderdate: "20220302",
                status: 1,
                store: { store_no: 1, store_name: "재승이네 청과", store_img: null }
            },
            {
                order_no: 1,
                order_items: [
                    { item_no: 1, item_name: "사과", count: 3, orderprice: 2000 },
                    { item_no: 2, item_name: "포도", count: 2, orderprice: 3000 },
                    { item_no: 3, item_name: "수박", count: 1, orderprice: 10000 },
                    { item_no: 4, item_name: "샤인머스켓", count: 1, orderprice: 20000 }
                ],
                orderdate: "20220302",
                status: 1,
                store: { store_no: 1, store_name: "재승이네 청과", store_img: null }
            },
            {
                order_no: 1,
                order_items: [
                    { item_no: 1, item_name: "사과", count: 3, orderprice: 2000 },
                    { item_no: 2, item_name: "포도", count: 2, orderprice: 3000 },
                    { item_no: 3, item_name: "수박", count: 1, orderprice: 10000 },
                    { item_no: 4, item_name: "샤인머스켓", count: 1, orderprice: 20000 }
                ],
                orderdate: "20220302",
                status: 1,
                store: { store_no: 1, store_name: "재승이네 청과", store_img: null }
            }
            ]
    }
  },
    methods: {
        goProfile() {
            this.$router.push({
                name: "profile",
                // params: { user:this.dummy }
            });
        },
    },
}

</script>
<style scoped>
.root-div{
  /* width 주고,중앙 정렬. */
  margin-left:auto; 
  margin-right:auto;
  width:390px;
  
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;

}
.button-chgprofile{
  font-size:x-small;
  text-decoration: underline;
  
}
</style>
