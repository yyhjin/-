<template>
  <div class="root_div">
    <h3>메뉴 등록</h3>
    <input type="text" v-model="input.name" placeholder="품목 이름 ex)사과 1상자">
    <input type="text" v-model="input.price" placeholder="가격 ex)1000" @keyup.enter="addItem()">
    <input type="checkbox">
    <button @click="addItem()">등록</button>

    <hr>
    <h4>등록 된 메뉴</h4>
    <ul>
        <li v-for="(item,idx) in list" :key="idx">
            {{ item.item_name }} | {{item.price}}원 <button @click="delItem(idx)">삭제</button>
        </li>
    </ul>
    
    <div>
    <button>수정</button>
    <button>초기화</button>
    <button @click="this.$router.go(-1)">뒤로</button>
    </div>
    
  </div>

</template>

<script>
export default {
    data(){
        return{
            //dummy
            input:{
                name: "",
                price: ""
            },
            list:[
                {item_name:"사과",
                price:3000,
                recent:0
                },
                 {item_name:"배",
                price:2000,
                recent:0
                },
                 {item_name:"수박",
                price:10000,
                recent:0}
            ]
        }
    }
    ,
    methods:{
        addItem(){
            this.list.push(
                {item_name:this.input.name,price:this.input.price
                })
        },
        delItem(idx){
            this.list.splice(idx,1)
        }
    }

}
</script>

<style>

</style>