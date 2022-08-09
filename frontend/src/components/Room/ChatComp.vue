<template>
  <el-card class="chat_card">
    <h3 style="margin-top:0px">채팅창!</h3>
    <div class="messages">
         <span>판매자: 안녕하세요!</span> 
    </div>
    <div class="input">
        <el-input placeholder="채팅채팅" v-model="input_chat" />
    <el-button>보내기</el-button>
    </div>
    
    
  </el-card>
</template>

<script>

export default {
    data(){
        return{
            input_chat:""
        
        }
    }
   

    

}
</script>

<style scoped>
.input{
    align-self:flex-end
  
}
 .chat_card{
    width:400px;
    height:300px;
 }
 /* 인풋을 바닥에붙이기위해 message height 하드코딩ㅎ */
 .messages{
    height:190px;
 }
 .input{
    display:flex;   
 }



</style>