<template>
<div>
  <h2>TEST ROOM</h2>
  <el-input placeholder="storeNo" v-model="inputform.storeNo" />
  <el-input placeholder="userNo" v-model="inputform.userNo" />
  <el-input placeholder="userName" v-model="inputform.userName" />
  <el-input placeholder="판매자(0)or구매자(1)" v-model="inputform.isSeller"
  @keyup.enter="goRoom"/>
  
   <el-button @click="goRoom"></el-button>


</div>
  
</template>

<script setup>

import {ref} from "vue"
import { useRouter } from 'vue-router'
const router = useRouter()
const inputform = ref({
  userNo:1,
  userName:"",
  storeNo:1,
  isSeller:"",//0,1
  
})
const goRoom =function(){
  const params = {userNo: inputform.value.userNo, 
                  storeNo: inputform.value.storeNo,
                  isSeller: inputform.value.isSeller,
                  userName: inputform.value.userName }
  console.log(params)
  if(inputform.value.isSeller==0){
     router.push({
        name: 'seller_room',
        params: params
      })
  }else{
    router.push({
        name: 'customer_room',
        params: params
      })
  }
 
}

</script>

<style>

</style>