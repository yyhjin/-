<template>
  <div>
    <el-card>
      <div>회원정보 list</div>
      <div>
        <!-- el-button태그 속성. height: 40px고정, 양옆은 글자수에따라 자동 조절. -->
        <el-button class="btn">수정</el-button>
        <el-button class="btn">취소</el-button>
      </div>
    </el-card>

  </div>
</template>
<style scoped> 
/* el-button 기본 color가 white이므로 수정 필요,. */
.btn{
  color: black !important;
  
  
}
</style>