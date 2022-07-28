<template>
  <div class=root-div>
    <h2>{{dummy.name}}#{{$route.params.id}}님의 page</h2>
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

    <el-card class="box-card">
      <template #header>
        <div class="card-header"> 
          <span>구매 내역</span>
        </div>
      </template>
      <div>
        <!-- 주문 내역 component -->
        <OrderComp/>
      </div>
    </el-card>
  </div>
</template>
<script>
import OrderComp from '@/components/OrderComp.vue';
export default {
    components: {OrderComp},
    
  data(){
    return{
      // dummy data from vuex
      dummy : this.$store.state.userinfo
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
