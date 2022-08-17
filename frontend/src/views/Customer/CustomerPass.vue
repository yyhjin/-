<template>
  <div>
    <el-card class="box-card">
      <div style="margin-top: -10px">
        <h5 style="margin-left: 5px; margin-bottom: 10px">현재 비밀번호</h5>
        <el-input
          v-model="olds"
          type="password"
          placeholder="password"
          show-password
        />
        <h5 style="margin-left: 5px; margin-bottom: 10px">비밀번호 재입력</h5>
        <el-input
          v-model="news"
          type="password"
          placeholder="password"
          show-password
        />
      </div>
      <div style="margin-top: 30px; text-align: right">
        <el-button round color="#42413e" style="color: white" @click="cl_mo()"
          >수정</el-button
        >
        <el-button round class="btn_cancle" @click="cl_cancle()"
          >취소</el-button
        >
      </div>
    </el-card>
  </div>
</template>

<script>
import { setPass } from "@/api/customer";
import { ref, computed } from "vue";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";

export default {
  name: "CustomerPass",

  setup() {
    const store = useStore();
    const userNo = computed(() => store.state.userInfo.userNo);
    const olds = ref();
    const news = ref();

    const open1 = (message) => {
      ElMessage({
        showClose: true,
        message: message,
        type: "success",
      });
    };
    const open2 = (message) => {
      ElMessage({
        showClose: true,
        message: message,
        type: "error",
      });
    };

    return { userNo, olds, news, open1, open2 };
  },

  methods: {
    cl_mo() {
      console.log(this.userNo);
      setPass(
        this.userNo,
        { password: this.olds, passwordUpdate: this.news },
        (response) => {
          if (response.data.response == "success") {
            this.open1("비밀번호 변경 성공!!");
          } else {
            this.open2("비밀번호 변경 실패..");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    cl_cancle() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.box-card {
  width: 300px;
}
</style>
