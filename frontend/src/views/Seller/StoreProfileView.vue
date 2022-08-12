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
                    <el-input v-model="form.storeName" />
                </el-form-item>
                 <el-form-item label="업종">
                    <el-input v-model="form.storeCategory" />
                </el-form-item>
                 <el-form-item label="전화번호">
                    <el-input v-model="form.storePhone" />
                </el-form-item>
                 <el-form-item label="시장">
                    <el-input v-model="form.marketNo" />
                </el-form-item>
                 <el-form-item label="상세위치">
                    <el-input v-model="form.storeAddr" />
                </el-form-item>

                <el-button @click="goBack()" type="danger">
                    뒤로
                </el-button>
                <el-button @click="commitProfile(this.$route.params.storeNo,this.form)" type="danger">
                    수정
                </el-button>
                

            </el-form>
        </el-card>
  </div>
</template>

<script>
import {StoreDetail,modifyStore} from "@/api/store.js"
export default {
  mounted(){
    StoreDetail(this.$route.params.storeNo,
    (res)=>{
      this.storeDetail=res.data
      //새로 mount
      this.form.storeName= this.storeDetail.storeName
      this.form.storeCategory= this.storeDetail.storeCategory
      this.form.storePhone= this.storeDetail.storePhone
      this.form.marketNo= this.storeDetail.market.marketNo
      this.form.storeAddr= this.storeDetail.storeAddr
    },
    (err)=>{
      console.log(err)
    })

    

  },
  data() {
    return {
      size:"default",
      storeDetail:{},
      form: {
        storeName:"",
        storeCategory:"",
        storePhone: "",
        marketNo: "",
        storeAddr:"" ,  
      }
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    //수정버튼
    commitProfile(storeNo,form){
      modifyStore(storeNo,form,
      (res)=>{
        res
    },
    (err)=>{
      console.log(err)
    })
    this.goBack()
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