<template>
  <div class="root_div">
    <div class="input_item" style="width:100%;max-width: 500px; margin:auto; margin-bottom:10px;">
        <el-row :gutter="10">
            <el-col :span="12">
            <el-input
            v-model="input.itemName"
            placeholder="품목 이름 ex)사과 1상자"
            :suffix-icon="Calendar"
            /></el-col>
            <el-col :span="7">
            <el-input
                v-model="input.price"
                placeholder="가격 ex)1000"
                :prefix-icon="Search"
                 @keyup.enter="addItem(this.$route.params.storeNo,input)"
            />
            </el-col>
            <el-col :span="2" >
                <el-button link size="large" :icon="CirclePlus" style="font-size:24px" @click="addItem(this.$route.params.storeNo,input)"></el-button>
            </el-col>
    </el-row>
    </div>
    
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
   <el-card>
    <el-table :data="itemList" style="width: 355px; margin:auto;" max-height="250">
    <el-table-column fixed label="" width="40" >
        <!-- v-slot:default="scope" -->
     <template #default="scope">
        {{scope.$index+1}}
      </template>
     </el-table-column>
    <el-table-column prop="itemName" label="품목"  width="96"/>
    <el-table-column prop="price" label="가격" width="74" />
    <el-table-column prop="recent" label="오픈 메뉴" width="90" >
        <template #default="scope">
            <!-- slot 내부에서 속성 접근 시 scope 사용. -->
            <el-checkbox v-model="itemList[scope.$index].recent" :label="scope.row.recent? '등록됨':'해제됨'" size="large" @click="clickUsed(scope.row.itemNo,{recent:!scope.row.recent})"/>
        </template>
    </el-table-column>
    <el-table-column  label="삭제" width="55">
      <template #default="scope">
        <el-button link circle @click="delItem(scope.row.itemNo)" :icon="Delete">
           
  </el-button>
      </template>
    </el-table-column>
  </el-table>
  </el-card>
    
  </div>    

</template>

<script>
import {menuList,delelteItem,addItem,checkUsed} from "@/api/item.js"
import { ArrowLeft ,Delete,CirclePlus} from '@element-plus/icons-vue'
import {ref} from 'vue';
export default {
    setup(){
        //아이템리스트.
        let itemList =ref([])
        return{itemList,ArrowLeft,Delete,CirclePlus}
    },
    mounted(){
    //마운트 시 메뉴 재로딩
    this.loadItems(this.$route.params.storeNo)

    },
    data(){
        return{
            input:{
                itemName: "",
                price: "",
                recent: true,
                storeNo: this.$route.params.storeNo
            }
        }
    }
    ,
    methods:{
        whatsthis(what){
            console.log(what)
        },
        addItem(storeNo,input){
            addItem(storeNo,input,
            ()=>{
            console.log("등록")
            this.loadItems(storeNo)},
            (err)=>{err})
        },
        delItem(itemNo){
            delelteItem(itemNo,
            ()=>{
            this.loadItems(this.$route.params.storeNo)
            console.log("삭제")},
            (err)=>{err})
        },
        loadItems(storeNo){
            menuList(storeNo,
            (res)=>{
                //dummy
                // res.data=[{item_name:"사과",price:3000,recent:0 },{item_name:"배",price:2000,recent:0 },{item_name:"수박",price:10000,recent:0}]
                this.itemList=res.data
                console.log(JSON.stringify(this.itemList))
            },
            (err)=>{
                console.log(err)
            })
        },
        clickUsed(itemNo,req){
            checkUsed(itemNo,req,
            ()=>{
            this.loadItems(this.$route.params.storeNo)
            },
            (err)=>{err})
        }
    }

}
</script>

<style scoped>
.el-card{
    margin:auto;
    width:100%;
    max-width: 500px;
}
.el-input__wrapper{
    width:200px;
}

</style>