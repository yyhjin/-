<template>
  <div class="root_div">
    <h3>가게 정보 관리</h3>
    <el-card>
      <el-form>
        <el-form-item label="Activity name">
          <el-input v-model="form.name" />
        </el-form-item>

        <el-button @click="goBack()" type="danger">
          뒤로
        </el-button>


      </el-form>


    </el-card>



  </div>
</template>

<script>

export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
      }
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    }
  }

}
</script>

<style>
</style>