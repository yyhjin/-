<template>
    <div class="root_div">
        <div>
            <el-page-header :icon="ArrowLeft" content="가게 정보 관리" @back="this.$router.go(-1)" />
        </div>
        <el-card class="register_card" style="margin-top: 50px">
            <el-form class="register_form" label-position="top" label-width="100px" style="max-width: 460px; margin: auto" :size="size">
                <div class="register_pic">
                    <el-card style="margin-top: 20px">
                        <el-row>
                            <div class="pic_img" style="margin-right: 10px">
                                <!-- 프로필 사진이 없다면 기본이미지로 : 백엔드에서 부탁하는게 좋을 듯함.. -->
                                <el-image style="width: 100px; height: 100px" :src="this.imgsrc" fit="fill" />
                            </div>
                            <div class="pic_reg" style="margin-left: 10px">
                                <h3 style="margin-top: 5px">{{ img_message }}</h3>
                                <input type="file" accept="image/*" @change="imgChange" ref="input_tag" hidden />
                                <el-button @click="clickInput" type="info">사진 수정</el-button>
                            </div>
                        </el-row>
                    </el-card>
                </div>
                <el-form-item label="상점 이름" style="margin-top: 30px">
                    <el-input v-model="form.storeName" />
                </el-form-item>
                <el-form-item label="업종">
                    <el-select v-model="form.storeCategory" class="m-2" placeholder="Select" size="large">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
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
                <div style="margin-top: 50px; text-align: right">
                    <el-button size="large" @click="commitProfile(this.$route.params.storeNo, this.form)" style="margin-right: 20px; background-color: #42413e; color: white"> 수정 </el-button>
                    <el-button size="large" @click="goBack()"> 취소 </el-button>
                </div>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import { StoreDetail, modifyStore, updateImg } from "@/api/store.js";
import { ElMessage } from "element-plus";

export default {
    mounted() {
        StoreDetail(
            this.$route.params.storeNo,
            (res) => {
                this.storeDetail = res.data;
                //새로 mount
                this.form.storeName = this.storeDetail.storeName;
                this.form.storeCategory = this.storeDetail.storeCategory;
                this.form.storePhone = this.storeDetail.storePhone;
                this.form.marketNo = this.storeDetail.market.marketNo;
                this.form.storeAddr = this.storeDetail.storeAddr;
            },
            (err) => {
                console.log(err);
            }
        );
        this.imgsrc = this.$route.params.img;
    },
    data() {
        return {
            img: "",
            imgsrc: "",
            img_message: "프로필 사진을 등록하세요!",
            img_valiation: true,
            img_change: false,

            size: "default",
            storeDetail: {},
            form: {
                storeName: "",
                storeCategory: "",
                storePhone: "",
                marketNo: "",
                storeAddr: "",
            },
            options: [
                { value: "채소/청과", label: "채소/청과" },
                { value: "방앗간", label: "방앗간" },
                { value: "생선/건어물", label: "생선/건어물" },
                { value: "축산", label: "축산" },
                { value: "의류/신발", label: "의류/신발" },
                { value: "이불/커튼", label: "이불/커튼" },
                { value: "음식점", label: "음식점" },
                { value: "떡집", label: "떡집" },
                { value: "반찬", label: "반찬" },
                { value: "마트", label: "마트" },
                { value: "그 외 기타", label: "그 외 기타" },
            ],
        };
    },
    methods: {
        open() {
            ElMessage({
                message: "수정 성공",
                type: "success",
            });
        },
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
                this.img_message = `등록 가능한 사진입니다!.`;
                this.img = file[0];
                //미리보기 띄우기위해.
                this.imgsrc = URL.createObjectURL(this.img);
                this.img_change = true;
            }
        },
        //수정버튼
        commitProfile(storeNo, form) {
            modifyStore(
                storeNo,
                form,
                (response) => {
                    console.log(response);

                    if (this.img_change == true) {
                        let formdata = new FormData();
                        formdata.append("file", this.img);
                        updateImg(
                            storeNo,
                            formdata,
                            (response) => {
                                response;
                                this.open();
                                this.goBack();
                            },
                            (error) => {
                                console.log(error);
                            }
                        );
                    }

                    this.open();
                    this.goBack();
                },
                (err) => {
                    err;
                    //console.log(err);
                }
            );
        },
    },
};
</script>

<style scoped>
.size_control {
    margin-bottom: 10px;
}
.register_card {
    width: 100%;
    max-width: 550px;
    margin: auto;
}
.register_pic {
    margin-bottom: 15px;
}
</style>
