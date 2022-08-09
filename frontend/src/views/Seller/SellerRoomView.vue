<template>
    <div class="root_div">
<div class="main"><div class="video">화면 송출
        </div>
        <ChatComp /></div>
        

        <div class="customers">
            <el-card :class="'user '+user.status" v-for="(user,idx) in users" :key="idx" @click="connectUser(idx)">
                <div>{{user.id}}</div>
                <div>{{user.name}}</div>
            </el-card>
        </div>

        <div class="btns">
            <el-button type="primary" style="margin-left: 16px" @click="drawer_bills = true">
                주문서
            </el-button>
            <el-button type="primary" style="margin-left: 16px" @click="drawer_menues = true">
                메뉴수정
            </el-button>
        </div>


        <el-drawer v-model="drawer_menues" title="메뉴수정" size="50%">
            <!-- 메뉴수정 drawer -->
            <div>메뉴 수정</div>

        </el-drawer>

        <el-drawer v-model="drawer_bills" title="주문서" size="50%">
            <div>
                <!-- 주문서 drawer -->
                <el-card class="bill_root" v-for="bill in bills" :key="bill">
                    <div class="bill_header">
                        <div class="order_date">
                            주문일자:{{ bill.orderdate }}
                        </div>
                        <div class="order_no">
                            주문번호:{{ bill.order_no }} 상태: {{ bill.status }}
                        </div>
                    </div>
                    <div class="custmer_name">
                        {{ bill.customer_id }}
                    </div>
                    <div class="order_items" v-for="(item, idx) in bill.order_items" :key="idx">
                        <div class="item">{{ item.item_name }} x {{ item.count }} | {{ item.price * item.count }}원</div>
                    </div>
                    <el-button @click="innerDrawer = true">덤 추가!</el-button>
                </el-card>

                <!-- <div v-for="bill in bills" :key="bill">
                    <BillComp :bill="bill" />
                    <el-button @click="innerDrawer = true">덤 추가!</el-button>
                </div> -->


                <el-drawer v-model="innerDrawer" title="I'm inner Drawer" :append-to-body="true"
                    :before-close="handleClose">
                    <!-- inner drawer  -->
                    <el-button v-for="item in items" :key="item" @click="addService()">
                        {{ item }}
                    </el-button>
                </el-drawer>
            </div>
        </el-drawer>

    </div>

</template>

<script setup>
import ChatComp from '@/components/Room/ChatComp.vue'
import { ref } from 'vue'
// import BillComp from '@/components/Mystore/BillComp.vue';

//bills
const drawer_bills = ref(false)
const bills = ref([{
    order_no: 1,
    customer_id: 1,
    order_items: [{
        item_name: "사과",
        count: 3,
        price: 2000
    },
    {
        item_name: "배",
        count: 2,
        price: 3000
    },
    {
        item_name: "수박",
        count: 1,
        price: 10000
    }],
    orderdate: "20220803",
    status: 0
},
{
    order_no: 2,
    customer_id: 1,
    order_items: [{
        item_name: "사과",
        count: 3,
        price: 2000
    },
    {
        item_name: "배",
        count: 2,
        price: 3000
    },
    {
        item_name: "수박",
        count: 1,
        price: 10000
    }],
    orderdate: "20220803",
    status: 1
},
])
//users
let users = ref([{id:"1",name:"재승",status:"connected",},{id:"2",name:"경무",status:"disconnected",},{id:"1",name:"재승",status:"disconnected",},{id:"1",name:"재승",status:"disconnected",}])
const connectUser=function(idx){
    for(let i =0; i< this.users.length;i++){
        this.users[i].status="disconnected";
    }
    this.users[idx].status="connected";
}
//menues
const drawer_menues = ref(false)
// inner drawer(덤)
const innerDrawer = ref(false)
const items = ref(['사과', '포도', '수박', '배'])

</script>

<style scoped>
.user{
    width:150px;
    height:120px;
    margin:10px;
}
.connected{
    border-color:red;
}
.main {
    display: flex;
    flex-wrap:wrap;

}
.root_div{
    display:flex; 
    flex-direction: column;
    margin:auto;
}
.customers{
    display:flex;
}

.video {
    width: 400px;
    height: 300px;
    border: 2px;
    border-style: groove;
    margin-right: 5px;
}

.el-card__body {
    display: flex;
}
</style>