<template>
  <div class="root_div">
    <h3>가게 정보 관리</h3>
    <el-card class="register_card">
            <el-form class="register_form" 
            label-position="top"
            label-width="100px"
            style="max-width: 460px"
            :size="size"
            >
            <el-radio-group class="size_control" v-model="size" label="size control" size="small">
                <el-radio-button label="large">크게 보기</el-radio-button>
                <el-radio-button label="default">기본 보기</el-radio-button>
            </el-radio-group>
                <div class=register_pic>
                    <el-card>
                        <el-row>
                            <div class="pic_img" style="margin-right:10px;">
                                <!-- 프로필 사진이 없다면 기본이미지로 : 백엔드에서 부탁하는게 좋을 듯함.. -->
                                <el-image style="width: 100px; height: 100px" src="" fit="fill" />
                            </div>
                            <div class="pic_reg">
                                <div>프로필 사진을 등록하세요</div>
                                <el-button type="info">등록</el-button>
                            </div>
                        </el-row>
                    </el-card>
                </div>
                <el-form-item label="상점 이름">
                    <el-input v-model="form.store_name" />
                </el-form-item>
                 <el-form-item label="사업자등록번호">
                    <el-input v-model="form.store_id" />
                </el-form-item>
                 <el-form-item label="업종">
                    <el-input v-model="form.store_category" />
                </el-form-item>
                 <el-form-item label="전화번호">
                    <el-input v-model="form.store_phone" />
                </el-form-item>
                 <el-form-item label="위치">
                    <el-input v-model="form.store_location" />
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
        store_name: '재승이네 청과',
        store_id: '********',
        store_category: '청과',
        store_phone: '010-2222-3333',
        store_location: "xx시장 or 도로명주소",
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

<style scoped>
.size_control{
   margin-bottom:10px;
}
.register_card {
    width: 90%;
    max-width: 500px;
    margin:auto;
}
.register_pic{
    margin-bottom:15px;
    
}

</style>