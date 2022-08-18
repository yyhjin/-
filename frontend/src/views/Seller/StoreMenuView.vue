<template>
    <div class="root_div">
        <div>
            <el-page-header :icon="ArrowLeft" content="메뉴 관리" @back="this.$router.go(-1)" />
        </div>
        <el-card class="box-card" style="margin-top: 30px">
            <div class="input_item" style="width: 100%; max-width: 500px; margin-left: 20px">
                <el-row :gutter="10">
                    <el-col :span="12"> <el-input v-model="input.itemName" placeholder="품목 이름 ex)사과 1상자" /></el-col>
                    <el-col :span="7">
                        <el-input v-model="input.price" placeholder="가격 ex)1000"  @keyup.enter="addItem(this.$route.params.storeNo, input)" />
                    </el-col>
                    <el-col :span="2">
                        <el-button link style="font-size: 33px; margin-left: 10px; margin-top: -4px" :icon="CirclePlus" @click="addItem(this.$route.params.storeNo, input)"></el-button>
                    </el-col>
                </el-row>
            </div>  
        </el-card>

        <!-- <div class="input_item">
        <input type="text" v-model="input.itemName" placeholder="품목 이름 ex)사과 1상자">
        <input type="text" v-model="input.price" placeholder="가격 ex)1000" @keyup.enter="addItem(this.$route.params.storeNo,input)">
        <button @click="addItem(this.$route.params.storeNo,input)">등록</button>
    </div> -->
        <!-- <ul>
        <li v-for="(item,idx) in itemList" :key="idx">
            {{ item.itemName }} | {{item.price}}원 <button @click="delItem(item.itemNo)">삭제</button>
        </li>
    </ul> -->
        <el-card class="box-card" style="margin-top: 30px">
            <el-table :data="itemList" style="width: 420px; margin: auto" max-height="400">
                <el-table-column fixed label="" width="40">
                    <!-- v-slot:default="scope" -->
                    <template #default="scope">
                        {{ scope.$index + 1 }}
                    </template>
                </el-table-column>
                <el-table-column prop="itemName" label="품목" width="100" />
                <el-table-column prop="price" label="가격" width="100" />
                <el-table-column prop="recent" label="오픈 메뉴" width="120">
                    <template #default="scope">
                        <!-- slot 내부에서 속성 접근 시 scope 사용. -->
                        <el-checkbox
                            v-model="itemList[scope.$index].recent"
                            :label="scope.row.recent ? '등록됨' : '해제됨'"
                            size="large"
                            @click="clickUsed(scope.row.itemNo, { recent: !scope.row.recent })"
                        />
                    </template>
                </el-table-column>
                <el-table-column label="삭제" width="55">
                    <template #default="scope">
                        <el-button link circle @click="delItem(scope.row.itemNo)" :icon="Delete"> </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>
import { menuList, delelteItem, addItem, checkUsed } from "@/api/item.js";
import { ArrowLeft, Delete, CirclePlus } from "@element-plus/icons-vue";
import { ref } from "vue";
export default {
    setup() {
        //아이템리스트.
        let itemList = ref([]);
        return { itemList, ArrowLeft, Delete, CirclePlus };
    },
    mounted() {
        //마운트 시 메뉴 재로딩
        this.loadItems(this.$route.params.storeNo);
    },
    data() {
        return {
            input: {
                itemName: "",
                price: "",
                recent: true,
                storeNo: this.$route.params.storeNo,
            },
        };
    },
    methods: {
        whatsthis(what) {
            console.log(what);
        },
        addItem(storeNo, input) {
            addItem(
                storeNo,
                input,
                () => {
                    console.log("등록");
                    this.loadItems(storeNo);
                },
                (err) => {
                    err;
                }
            );
        },
        delItem(itemNo) {
            delelteItem(
                itemNo,
                () => {
                    this.loadItems(this.$route.params.storeNo);
                    console.log("삭제");
                },
                (err) => {
                    err;
                }
            );
        },
        loadItems(storeNo) {
            menuList(
                storeNo,
                (res) => {
                    //dummy
                    // res.data=[{item_name:"사과",price:3000,recent:0 },{item_name:"배",price:2000,recent:0 },{item_name:"수박",price:10000,recent:0}]
                    this.itemList = res.data;
                    console.log(JSON.stringify(this.itemList));
                },
                (err) => {
                    console.log(err);
                }
            );
        },
        clickUsed(itemNo, req) {
            checkUsed(
                itemNo,
                req,
                () => {
                    this.loadItems(this.$route.params.storeNo);
                },
                (err) => {
                    err;
                }
            );
        },
    },
};
</script>

<style scoped>
.el-card {
    margin: auto;
    width: 100%;
    max-width: 500px;
}
.el-input__wrapper {
    width: 200px;
}
</style>
