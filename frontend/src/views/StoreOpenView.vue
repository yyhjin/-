<template>
    <div class="div_div">
        <div class="div_big">
            <ModalView v-if="isModalViewed" @close-modal="isModalViewed = false">
                <Content msg="Hello Vue in CodeSandbox!" @close-modal="isModalViewed = false" />
            </ModalView>
            <div class="div_title">
                <h1>가게오픈 {{ this.$route.params.seller_id }}</h1>
            </div>
            <div class="div_intro">
                <h2>소개 문구 :</h2>
                <el-input v-model="introduce" :rows="3" type="textarea" placeholder="소개를 적어주세요." class="input_intro" v-if="!isModalViewed" />
            </div>
            <div class="div_img" v-if="!isModalViewed">
                <h2>소개 이미지 :</h2>
                <div>
                    <el-upload action="#" list-type="picture-card" :auto-upload="false" v-model:file-list="fileList">
                        <h3>추가</h3>

                        <template #file="{ file }">
                            <div>
                                <img class="el-upload-list__item-thumbnail" :src="file.url" alt="상점사진" />
                                <span class="el-upload-list__item-actions">
                                    <!--
                                <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                    <h5>크게</h5>
                                </span>-->
                                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="item_delete(file)">
                                        <h4>삭제</h4>
                                    </span>
                                </span>
                            </div>
                        </template>
                    </el-upload>
                </div>
            </div>
            <div class="div_menu">
                <div>
                    <h2>판매 목록 :</h2>
                    <el-button color="#FF6F61" round class="btn_menu" @click="isModalViewed = true">메뉴 추가</el-button>
                </div>
                <div style="display: inline-block">
                    <choosed-item v-if="!isModalViewed"></choosed-item>
                </div>
            </div>

            <div style="width: 200px; margin: auto">
                <el-button color="#FF6F61" round class="btn_ok" @click="cl_ok" style="height: 50px !important">가게 오픈</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import { useStore } from "vuex";
import { ref } from "vue";
import Content from "@/components/Content.vue";
import ModalView from "@/components/ModalView.vue";
import ChoosedItem from "@/components/ChoosedItem.vue";

export default {
    name: "StoreOpenView",
    components: {
        Content,
        ModalView,
        ChoosedItem,
    },
    setup() {
        const store = useStore();
        const SellList = store.getters["root/getSellList"];

        const disabled = ref(false);

        return { SellList, disabled };
    },
    data() {
        return {
            introduce: "",
            isModalViewed: false,
            fileList: [],
        };
    },

    mounted() {},

    methods: {
        cl_ok() {
            console.log(this.fileList);

            // 오픈버튼 임시로 만들어 둿어요.
            this.$router.push({
        name: 'seller_room',
        params: { storeNo:29 }
      })
        },
        cl_add() {
            console.log(this.file);
            
        },
        item_delete(file) {
            for (var i = 0; i < this.fileList.length; i++) {
                if (file.name == this.fileList[i].name) {
                    this.fileList.splice(i, 1);
                }
            }
        },
    },
};
</script>

<style scoped>
.div_div {
    text-align: center;
}

.div_big {
    width: 500px;
    display: inline-block;
}
.div_intro {
    margin-top: 50px;
}

.div_intro h2 {
    text-align: left;
    margin-bottom: 20px;
}

.div_img {
    margin-top: 50px;
    display: contents;
    text-align: left;
}

.div_img h2 {
    text-align: left;
    margin-bottom: 30px;
}

.demo-image__error .image-slot {
    font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
    font-size: 30px;
}
.demo-image__error .el-image {
    width: 100%;
    height: 200px;
}

.div_menu {
    margin-top: 30px;
}

.div_menu h2 {
    text-align: left;
    margin-bottom: 40px;
    float: left;
}

.btn_menu {
    margin-top: 20px;
    margin-bottom: 20px;
    margin-right: 10px;
    width: 80px;
    height: 40px !important;
    float: right;
}

.btn_ok {
    margin-top: 50px;
    margin-bottom: 30px;
    width: 100px;
}
.el-button {
    color: white !important;
}
</style>
