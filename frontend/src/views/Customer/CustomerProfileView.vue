<template>
  <!-- TODO:
  1.탈퇴기능 추가여부? 
  -->
  <div class="root_div">
    <el-card class="card">
      <!-- <h2>회원정보 수정</h2> -->
      <div class="modifyMenu">
        <div class="modifyForm">
          <h5>아이디</h5>
          <el-input
            placeholder="연락처"
            v-model="userinfo.id"
            class="input_id"
            disabled
          />
          <h5>닉네임</h5>
          <el-input
            placeholder="닉네임"
            v-model="userinfo.nick"
            class="input_nick"
          />
          <!-- <h5>비밀번호</h5>
          <el-input
            placeholder="패스워드"
            v-model="password"
            class="input_pass"
            type="password"
          />
          <h5>비밀번호 확인</h5>
          <el-input
            placeholder="패스워드 확인"
            v-model="password_double"
            class="input_pass"
            type="password"
          />
          <h5 style="text-align: right; color: red">{{ this.same }}</h5> -->
          <h5>이름</h5>
          <el-input
            placeholder="이름"
            v-model="userinfo.name"
            class="input_name"
          />
          <h5>연락처</h5>
          <el-input
            placeholder="연락처"
            v-model="userinfo.phone_number"
            class="input_phone"
          />
          <h5 style="float: left">주소</h5>
          <div class="searchAddress">
            <el-button
              round
              color="#e07c49"
              style="color: white"
              @click="execDaumPostcode()"
              class="btn_address"
              size="small"
              >주소 검색</el-button
            ><br />
            <!--<el-input type="text" v-model="postcode" placeholder="우편번호"/>-->
            <el-input
              type="text"
              v-model="userinfo.address"
              id="address"
              placeholder="기본 주소"
              style="margin-top: 10px"
              disabled
            /><br />
            <el-input
              type="text"
              v-model="userinfo.extraAddress"
              id="extraAddress"
              placeholder="참고 항목"
              style="margin-top: 10px"
              disabled
            />
          </div>
          <el-input
            type="text"
            v-model="userinfo.detailAddress"
            id="detailAddress"
            placeholder="상세 주소"
            @keyup.enter="cl_modify"
            style="margin-top: 10px"
          />
        </div>

        <div style="margin-top: 30px">
          <el-button
            color="#42413e"
            style="width: 100px; color: white"
            class="btn_modify"
            @click="cl_modify"
            >수정</el-button
          >
          <el-button style="width: 100px" class="btn_cancle" @click="cl_cancle"
            >취소</el-button
          >
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
import { getCustomer, updateCustomer } from "@/api/customer.js";
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import { ElMessage } from "element-plus";

export default {
  setup() {
    const store = useStore();
    const userinfo = reactive({
      id: "",
      nick: "",
      name: "",
      phone_number: "",
      address: "",
    });

    const userType = computed(() => store.state.userInfo.userType);
    const userNo = computed(() => store.state.userInfo.userNo);

    const open = () => {
      ElMessage({
        message: "수정되었습니다.",
        type: "success",
      });
    };
    const open2 = () => {
      ElMessage({
        message: "수정에 실패했습니다.",
        type: "error",
      });
    };
    return {
      userNo,
      userType,
      userinfo,
      open,
      open2,
    };
  },

  watch: {
    password_double() {
      if (this.password != this.password_double) {
        this.same = "비밀 번호 확인 필요!!";
      } else if (this.password == this.password_double) {
        this.same = "사용 가능합니다.";
      }
    },
    password() {
      if (this.password != this.password_double) {
        this.same = "비밀 번호 확인 필요!!";
      } else if (this.password == this.password_double) {
        this.same = "사용 가능합니다.";
      }
    },
  },
  created() {
    getCustomer(
      this.userNo,
      (response) => {
        console.log(response);
        console.log(this.userNo);
        this.userinfo.id = response.data.customerId;
        this.userinfo.name = response.data.customerName;
        this.userinfo.nick = response.data.customerNickname;
        this.userinfo.phone_number = response.data.customerPhone;

        var addr = response.data.customerAddr;
        var array = addr.split("(");
        this.userinfo.address = array[0];
        //this.userinfo.detailAddress = array[1];
        if (array[1] != undefined) {
          this.userinfo.extraAddress = "(" + array[1];
        }
      },
      (error) => {
        console.log(error);
      }
    );
  },

  methods: {
    cl_modify() {
      //alert("등록성공");
      console.log(this.userNo);
      var custaddr = this.userinfo.address;
      if (this.userinfo.detailAddress != undefined) {
        custaddr = custaddr + " " + this.userinfo.detailAddress;
      }
      if (this.userinfo.extraAddress != undefined) {
        custaddr = custaddr + " " + this.userinfo.extraAddress;
      }
      //console.log("등록 정보: " + JSON.stringify(this.userinfo));
      const params = {
        customerAddr: custaddr,
        customerName: this.userinfo.name,
        customerNickname: this.userinfo.nick,
        customerPhone: this.userinfo.phone_number,
      };
      console.log(params);
      updateCustomer(
        this.userNo,
        params,
        (response) => {
          console.log(response);
          this.open();
          this.$router.push({
            name: "mypage",
            param: { id: this.userinfo.id },
          });
        },
        (error) => {
          console.log(error);
          this.open2();
        }
      );

      //this.$router.go(-1);
    },
    cl_cancle() {
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
                this.userinfo.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
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
    },
  },
};
</script>
<style scoped>
.card {
  margin: auto;
}
.adjustC {
  --el-color-primary: #42413e;
}
.card {
  width: 300px !important;
  margin-top: 30px;
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
