<template>
  <div class="firstDiv">
    <el-space wrap>
      <el-card shadow="hover">
        <el-button link @click="cl_customer">
          <svg
            viewBox="0 0 1024 1024"
            xmlns="http://www.w3.org/2000/svg"
            data-v-029747aa=""
            style="width: 80px; margin-top: 10px; display"
          >
            <path
              fill="currentColor"
              d="M432 928a48 48 0 1 1 0-96 48 48 0 0 1 0 96zm320 0a48 48 0 1 1 0-96 48 48 0 0 1 0 96zM96 128a32 32 0 0 1 0-64h160a32 32 0 0 1 31.36 25.728L320.64 256H928a32 32 0 0 1 31.296 38.72l-96 448A32 32 0 0 1 832 768H384a32 32 0 0 1-31.36-25.728L229.76 128H96zm314.24 576h395.904l82.304-384H333.44l76.8 384z"
            ></path>
          </svg>
        </el-button>
        <h4 style="display: block">소비자</h4>
      </el-card>

      <el-card shadow="hover">
        <el-button link @click="cl_seller">
          <svg
            viewBox="0 0 1024 1024"
            xmlns="http://www.w3.org/2000/svg"
            data-v-029747aa=""
            style="width: 80px; margin-top: 10px"
          >
            <path
              fill="currentColor"
              d="M704 704h64v192H256V704h64v64h384v-64zm188.544-152.192C894.528 559.616 896 567.616 896 576a96 96 0 1 1-192 0 96 96 0 1 1-192 0 96 96 0 1 1-192 0 96 96 0 1 1-192 0c0-8.384 1.408-16.384 3.392-24.192L192 128h640l60.544 423.808z"
            ></path>
          </svg>
        </el-button>
        <h4>판매자</h4>
      </el-card>
    </el-space>
  </div>
</template>

<script>
export default {
  name: "SelectState",

  data() {
    return {
      userType: "",
    };
  },

  mounted() {},

  methods: {
    cl_customer() {
      console.log("소비자");
      this.userType = "Customer";
      this.$router.push({
        name: "joinCustomer",
        params: { userType: this.userType },
      });
    },

    cl_seller() {
      console.log("판매자");
      this.userType = "Seller";
      this.$router.push({
        name: "joinSeller",
        params: { userType: this.userType },
      });
    },
  },
};
</script>

<style scoped>
.card {
  margin-top: 60px;
}

.firstDiv {
  text-align: center;
}
.btn_group {
  margin-top: 100px;
}

.btn_group button {
  height: 150px;
  background-color: white;
}

.div_customer {
  margin-left: -10px;
  margin-right: 10px;
  width: 130px;
  float: left;
}

.div_seller {
  margin-right: -10px;
  width: 130px;
  float: right;
  margin-bottom: 100px;
}
</style>
