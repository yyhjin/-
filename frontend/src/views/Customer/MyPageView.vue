<template>
  <div>
    <span>{{msg}}#{{$route.params.id}}님</span>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>회원 정보</span>
          <span class="button-chgprofile" @click="goProfile">회원 정보 수정</span>
        </div>
      </template>
       ...
    </el-card>

    <el-card class="box-card">
      <template #header>
        <div class="card-header"> 
          <span>구매 내역</span>
        </div>
      </template>
      <div>
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
        return {
            msg: "한재승",
        };
    },
    methods: {
        goProfile() {
            this.$router.push({
                name: "profile",
                params: { id: this.$route.params.id }
            });
        },
    },
}

</script>
<style scoped>
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
