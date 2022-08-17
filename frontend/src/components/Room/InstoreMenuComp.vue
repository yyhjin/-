<template>
<!-- instore menu component -->
    <div class="root_div"> 
        <el-card>
            <h2 style="teㄴxt-align:center">메뉴판</h2>
            <!-- instoremenu:{"itemNo","itemName","price","recent","soldout"} -->
        <el-table :data="instoreMenu" style="width: 355px; margin:auto;" max-height="500">
        <el-table-column fixed label="" width="40" >
            <!-- v-slot:default="scope" -->
        <template #default="scope">
            {{scope.$index+1}}
        </template>
        </el-table-column>
        <el-table-column prop="itemName" label="품목"  width="96"/>
        <el-table-column prop="price" label="가격" width="74" />
        <el-table-column prop="soldout" label="품절여부" width="90" >
            <template #default="scope">
                <!-- slot 내부에서 속성 접근 시 scope 사용. -->
            <el-button @click="updateSoldout(scope.$index)" :type="scope.row.soldout? 'danger':'success'">{{scope.row.soldout? '품절':'판매중'}}</el-button>
                <!-- <el-checkbox v-model="itemList[scope.$index].recent" :label="scope.row.recent? '등록됨':'해제됨'" size="large" @click="clickUsed(scope.row.itemNo,{recent:!scope.row.recent})"/> -->
            </template>
        </el-table-column>
        <el-table-column  label="삭제" width="55">
        <template #default="scope">
            <el-button link circle @click="delItem(scope.$index)" :icon="Delete">
            </el-button>
        </template>
                </el-table-column>
            </el-table>
            <!-- <div style="width: 100%"> <el-button class="mt-4" style="width: 100%" @click="onAddItem"
    >추가 하기</el-button></div> -->
            
        </el-card>
        
    </div> 
</template>

<script>
// import {delelteItem,addItem,checkUsed} from "@/api/item.js"
import { ArrowLeft ,Delete,CirclePlus} from '@element-plus/icons-vue'
import {ref} from 'vue';
export default {
    emits:['addItem','delItem','updatePrice','updateSoldout'],

    props:['instoreMenu'], //prop 과 emit으로 구현할것.
    setup(){
        //db메뉴리스트
        const menuList =ref([])
        return{menuList,ArrowLeft,Delete,CirclePlus}
    },

    data(){
        return{
            input:{
                
            }
        }
    },
    
    methods:{
        onAddItem(){

        },
        addItem(item){
            this.$emit('addItem',item)
        },
        delItem(idx){
            this.$emit('delItem',idx)
        },
        updatePrice(idx,price){
            console.log("comp실행")
            this.$emit('updatePrice',idx,price)
        },
        updateSoldout(idx){
            console.log(idx)
            this.$emit('updateSoldout',idx)

        }
        // loadItems(storeNo){
        //     menuList(storeNo,
        //     (res)=>{
        //         //dummy
        //         // res.data=[{item_name:"사과",price:3000,recent:0 },{item_name:"배",price:2000,recent:0 },{item_name:"수박",price:10000,recent:0}]
        //         this.itemList=res.data
        //         console.log(JSON.stringify(this.itemList))
        //     },
        //     (err)=>{
        //         console.log(err)
        //     })
        // },
        // clickUsed(itemNo,req){
        //     checkUsed(itemNo,req,
        //     ()=>{
        //     this.loadItems(this.$route.params.storeNo)
        //     },
        //     (err)=>{err})
        // }
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