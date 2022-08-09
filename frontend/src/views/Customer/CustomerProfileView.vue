
<template>
 <!-- TODO:
  1.탈퇴기능 추가여부? 
  -->
  <div class="root_div">
      <el-card class="card">
        <h2>회원정보 수정</h2>
        <div class="modifyMenu">
          <div class="modifyForm ">
            <h5>아이디</h5>
            <el-input placeholder="연락처" v-model="userinfo.id" class="input_id" disabled/>
            <h5>이름</h5>
            <el-input placeholder="이름" v-model="userinfo.name" class="input_name" />
            <h5>연락처</h5>
            <el-input placeholder="연락처" v-model="userinfo.phone_number" class="input_phone" />
            <h5 style="float: left">주소</h5>
            <div class="searchAddress">
              <el-button round color="#FF6F61" @click="execDaumPostcode()" class="btn_address"
                >주소 검색</el-button
              ><br />
              <!--<el-input type="text" v-model="postcode" placeholder="우편번호"/>-->
              <el-input
                type="text"
                v-model="userinfo.address"
                id="address"
                placeholder="기본 주소"
                style="margin-top: 10px"
              /><br />
              <el-input
                type="text"
                v-model="userinfo.detailAddress"
                id="detailAddress"
                placeholder="상세 주소"
                style="margin-top: 10px"
              />
              <el-input
                type="text"
                v-model="userinfo.extraAddress"
                id="extraAddress"
                placeholder="참고 항목"
                style="margin-top: 10px"
              />
            </div>
            <h5>생년월일</h5>
            <el-input placeholder="YYYY-MM-DD" v-model="userinfo.birthday" class="input_birthday" />
            <h5>성별(선택)</h5>
            <div class="mb-2 flex items-center text-sm radioGroup adjustC">
              <el-radio-group v-model="userinfo.gender" class="ml-4">
                <el-radio label="남" size="large">남</el-radio>
                <el-radio label="여" size="large">여</el-radio>
              </el-radio-group>
            </div>
          </div>

          <div>
            <el-button
              color="#FF6F61"
              style="width: 100px;"
              round
              class="btn_modify"
              @click="cl_modify"
              >수정</el-button
            >
            <el-button style="width: 100px" round class="btn_cancle" @click="cl_cancle"
              >취소</el-button
            >
          </div>
        </div>
      </el-card>
  </div>
</template>
<script>
export default {
  data(){
    return{
      // data from vuex
      userinfo: this.$store.state.userinfo,
  }},

  methods:{

    cl_modify(){
      alert("등록성공")
      console.log("등록 정보: "+JSON.stringify(this.userinfo))
      this.$router.go(-1);
    },
    cl_cancle(){
      this.$router.go(-1);
    },
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.userinfo.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            this.userinfo.address = data.roadAddress;
          } else {
            this.userinfo.address = data.jibunAddress;
          }
          if (data.userSelectedType === "R") {
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.userinfo.extraAddress += data.bname;
            }
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.userinfo.extraAddress +=
                this.userinfo.extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
            }
            if (this.extraAddress !== "") {
              this.userinfo.extraAddress = `(${this.userinfo.extraAddress})`;
            }
          } else {
            this.userinfo.extraAddress = "";
          }
          this.userinfo.postcode = data.zonecode;
          this.userinfo.detailAddress = "";
        },
      }).open();
    }
  }
}
</script>
<style scoped> 
  .card{
    margin:auto;
  }
.adjustC {
  --el-color-primary: #ff6f61;
}
.card {
  width: 300px !important;
  margin-top: 40px;
  text-align: center;
}

.btn_address {
  float: right;
  margin-top: 20px;
  width: 80px;
}

.modifyMenu {
  display: inline-block;
}

.modifyMenu h5 {
  text-align: left;
  margin-bottom: 10px;
}

.radioGroup {
  text-align: left !important;
}

</style>