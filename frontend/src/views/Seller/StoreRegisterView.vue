<template>
    <div class="root_div">
        <!-- <h3 style="text-align:center">가게 등록하기</h3> -->
        <el-card class="register_card">
            <el-form class="register_form" label-position="top" label-width="100px" style="max-width: 460px" :size="size">
                <el-radio-group class="size_control" v-model="size" label="size control" size="small">
                    <el-radio-button label="large">크게 보기</el-radio-button>
                    <el-radio-button label="default">기본 보기</el-radio-button>
                </el-radio-group>
                <div class="register_pic">
                    <el-card shadow="never">
                        <h5 style="margin-top: 0px">프로필 사진</h5>
                        <el-row>
                            <div class="pic_img" style="text-align: center">
                                <!-- 프로필 사진이 없다면 기본이미지로 : 백엔드에서 부탁하는게 좋을 듯함.. -->
                                <el-image style="width: 70px; height: 70px" :src="imgsrc" fit="fill" />
                            </div>
                            <div class="pic_reg">
                                <div style="margin-left: 10px; margin-top: 15px">
                                    <!-- input type="file" 은 커스터마이징이 되지 않는다. hidden 해놓고 쓰자. -->
                                    <input type="file" accept="image/*" @change="imgChange" ref="input_tag" hidden />
                                    <el-button @click="clickInput" plain size="small">사진 등록</el-button>
                                    <div style="font-size: 10px">{{ img_message }}</div>
                                </div>
                            </div>
                        </el-row>
                    </el-card>
                </div>

                <el-form-item label="상점 이름">
                    <el-input v-model="form.storeName" />
                </el-form-item>
                <el-form-item label="업종">
                    <el-select v-model="form.storeCategory" class="m-2" placeholder="선택">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </el-form-item>
                <el-form-item label="전화번호">
                    <el-input v-model="form.storePhone" />
                </el-form-item>

                <el-form-item label="시장">
                    <div style="width: 100%">
                        <el-button @click="search()" color="#e07c49" round size="small" style="float: right; color: white; margin-bottom: 10px"> 시장 검색 </el-button>
                    </div>
                    <el-input v-model="form.marketName" disabled />
                    <el-input v-model="form.storeAddr" placeholder="상세 위치를 입력해주세요" style="margin-top: 10px" />
                </el-form-item>
                <!-- <el-form-item label="시장 상세 위치">
          <el-input v-model="form.storeAddr" />
        </el-form-item> -->

                <div style="text-align: center; margin-top: 30px">
                    <el-button @click="register()" color="#42413e"> 등록 </el-button>
                    <el-button @click="goBack()"> 취소 </el-button>
                </div>
            </el-form>
        </el-card>
    </div>

    <el-dialog v-model="dialogVisible" title="시장 검색" width="90%">
        <search-name></search-name>
        <list-in-modal @market_register="marketReceive"></list-in-modal>
    </el-dialog>
</template>

<script>
// import axios from "axios"
import { storeRegister } from "@/api/store.js";
import { ref } from "vue";
import { ElMessage } from "element-plus";

import SearchName from "@/components/SearchMarket/SearchName.vue";
import ListInModal from "@/components/SearchMarket/ListInModal.vue";

export default {
    components: { SearchName, ListInModal },
    data() {
        return {
            img: "",
            imgsrc: "",
            img_message: "사진을 등록해주세요.",
            img_valiation: true,

            size: "default",
            form: {
                marketName: "", //시장이름
                marketNo: "", //시장번호
                sellerNo: this.$store.state.userInfo.userNo,
                storeAddr: "", //상세위치
                storeCategory: "", //업종
                storeName: "", //이름
                storePhone: "", //번호
                storeImg: "",
            },
            options: [
                { value: "농/수산물", label: "농/수산물" },
                { value: "청과", label: "청과" },
                { value: "생선/건어물", label: "생선/건어물" },
                { value: "축산", label: "축산" },
                { value: "음식점", label: "음식점" },
                { value: "방앗간", label: "방앗간" },
                { value: "의류/신발", label: "의류/신발" },
                { value: "이불/커튼", label: "이불/커튼" },
                { value: "떡집", label: "떡집" },
                { value: "반찬", label: "반찬" },
                { value: "카페", label: "카페" },
                { value: "마트", label: "마트" },
                { value: "그 외 기타", label: "그 외 기타" },
            ],
        };
    },
    setup() {
        const dialogVisible = ref(false);

        const open = () => {
            ElMessage({
                message: "등록 성공",
                type: "success",
            });
        };
        const open2 = (message) => {
            ElMessage({
                message: message,
                type: "error",
            });
        };

        return { dialogVisible, open, open2 };
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        },
        clickInput() {
            this.$refs.input_tag.click();
        },
        imgChange(e) {
            const file = e.target.files;
            this.img_validation = true;
            if (file[0].size > 1024 * 1024 * 2) {
                this.img_validation = false;
                this.imgsrc = "";
                this.img_message = `2MB이하만 등록 가능합니다.`;
            }
            if (file[0].type.indexOf("image") < 0) {
                this.img_validation = false;
                this.imgsrc = "";
                this.img_message = `이미지 파일만 등록 가능합니다.`;
            }
            if (this.img_validation == true) {
                this.img_message = `등록 가능한 사진입니다!`;
                this.img = file[0];
                this.img_change = true;
                //미리보기 띄우기위해.
                this.imgsrc = URL.createObjectURL(this.img);
            }
        },

        register() {
            //form데이터의 각 아이템마다 type설정과 append 함. 전체 formdata의 content-type은 multipart/form-data
            console.log(JSON.stringify(this.form));
            let formdata = new FormData();
            formdata.append("storeRegisterPostReq", new Blob([JSON.stringify(this.form)], { type: "application/json" }));
            formdata.append("file", this.img);
            storeRegister(
                formdata,
                //then
                (response) => {
                    response;
                    //TODO:redirect
                    console.log("등록성공");
                    console.log(formdata.entries());
                    this.open();
                    this.$router.push(
                        {
                            name: "mystore",
                            params: { id: this.$store.getters["userInfo/isAuthenticated"] },
                        },
                        //catch
                        (error) => {
                            console.log(error);
                            this.open2("등록 실패");
                        }
                    );
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
        },
        search() {
            this.dialogVisible = true;
        },
        marketReceive(market) {
            this.form.marketName = market.name;
            this.form.marketNo = market.no;
            this.dialogVisible = false;
        },
    },
};
</script>

<style scoped>
.el-radio-button {
    --el-radio-button-checked-bg-color: #e07c49;
    --el-radio-button-checked-border-color: #e07c49;
}

.size_control {
    margin-bottom: 10px;
}
.register_card {
    width: 90%;
    max-width: 500px;
    margin: auto;
}
.register_pic {
    margin-bottom: 15px;
}
</style>
