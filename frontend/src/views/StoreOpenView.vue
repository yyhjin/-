<template>
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
        <div class="div_img">
            <h2>소개 이미지 :</h2>
            <div class="demo-image__preview" v-if="!isModalViewed">
                <el-image style="width: 150px; height: 150px" :src="url" :preview-src-list="srcList" :initial-index="4" fit="cover" />
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
</template>

<script>
import { useStore } from "vuex";
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

        return { SellList };
    },
    data() {
        return {
            introduce: "",
            url: "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
            srcList: [
                "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg",
                "https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
                "https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg",
                "https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg",
                "https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg",
                "https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg",
                "https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg",
            ],
            isModalViewed: false,
        };
    },

    mounted() {},

    methods: {
        cl_ok() {
            console.log("ok");
        },
    },
};
</script>

<style scoped>
.div_big {
    width: 500px;
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
</style>
