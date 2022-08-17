<template>
<div>
  <h2>TEST ROOM</h2>
  <el-input placeholder="storeNo" v-model="inputform.storeNo" />
  <el-input placeholder="가게이름" v-model="inputform.storeName"/>
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
const menu = [{"itemNo":7,"itemName":"사과","price":1000,"recent":true},{"itemNo":8,"itemName":"배","price":1000,"recent":true},{"itemNo":9,"itemName":"공기밥","price":2000,"recent":true}]
const inputform = ref({
  userNo:1,
  userName:"",
  storeNo:1,
  isSeller:"",//0,1
  storeName:"",
  //webrtc 갖고 들어갈 메뉴.
  instoreMenu: menu.map((obj)=>({...obj,soldout:false}))//객체에 품절여부 속성 추가해서 push
})
const goRoom =function(){
  const params = {userNo: inputform.value.userNo, 
                  storeNo: inputform.value.storeNo,
                  isSeller: inputform.value.isSeller,
                  userName: inputform.value.userName,
                  storeName: inputform.value.storeName,
                  instoreMenu: JSON.stringify(inputform.value.instoreMenu) }
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