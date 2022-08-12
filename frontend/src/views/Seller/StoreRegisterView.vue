<template>
    <div class="root_div">
        <h3 style="text-align:center">가게 등록하기</h3>
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
                                <el-image style="width: 100px; height: 100px" :src="imgsrc" fit="fill" />
                            </div>
                            <div class="pic_reg">
                                <div>{{img_message}}</div>
                                 <div>
                                    <!-- input type="file" 은 커스터마이징이 되지 않는다. hidden 해놓고 쓰자. -->
                                    <input type="file" accept="image/*" @change="imgChange" ref="input_tag" hidden/>
                                    <el-button @click="clickInput" plain >사진등록</el-button>
                                </div>
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
                <el-button @click="register()" type="danger">
                    등록
                </el-button>


            </el-form>
        </el-card>
    </div>
</template>

<script>
// import axios from "axios"
import { storeRegister } from "@/api/store.js";

export default {
   
    data() {
        return {
            img:"",
            imgsrc:"",
            img_message:"프로필 사진을 등록하세요!",
            img_valiation:true,

            size:"default",
            form: {
                marketNo: '', //시장번호
                sellerNo: this.$store.state.userInfo.userNo,
                storeAddr: '',//상세위치
                storeCategory: '',//업종
                storeName: "", //이름
                storePhone: "", //번호
                storeImg:""
            }
        }

    },
    methods: {
        goBack() {
            this.$router.go(-1)
        },
        clickInput(){
            this.$refs.input_tag.click();
            
        },
        imgChange(e){
                const file=e.target.files;
                this.img_validation= true;
                if (file[0].size > 1024 * 1024 * 2){
                    this.img_validation= false;
                    this.imgsrc=''
                    this.img_message = `2MB이하만 등록 가능합니다.`
                }
                if (file[0].type.indexOf('image') < 0){
                    this.img_validation= false;
                    this.imgsrc=''
                    this.img_message = `이미지 파일만 등록 가능합니다.`
                }
                if(this.img_validation== true){
                    this.img_message = `등록 가능한 사진입니다!.`
                    this.img = file[0];
                    //미리보기 띄우기위해.
                    this.imgsrc=URL.createObjectURL(this.img)

                }
            },

        register(){
            //form데이터의 각 아이템마다 type설정과 append 함. 전체 formdata의 content-type은 multipart/form-data
            console.log(JSON.stringify(this.form))
            let formdata = new FormData()
            formdata.append('storeRegisterPostReq', new Blob( [JSON.stringify(this.form)], {type: "application/json"})
            );
            formdata.append("file",this.img)
             storeRegister(
                formdata,
                //then
                (response) => {
                    response
                    console.log("등록성공");
                    console.log(formdata.entries())
                },
                //catch
                (error) => {
                    console.log(error);
                }
            );
            // axios({method:'POST',
            // url:"http://localhost:8080/store",
            // headers:{
            //     'Content-Type':'multipart/form-data'
            // },
            // data:formdata}
            // )
            // .then((res)=>{
            //     console.log(res)
            // })
            // .catch((err)=>{
            //     console.log(err)
            // })
        }
    }}


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