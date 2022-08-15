<template>
    <div class="root_div">
        <div class="header">
            <h2>판매 내역<el-button @click=" this.$router.go(-1)" plain>뒤로</el-button></h2>
            <hr> 
        </div>
        <div>
            <div class="option_btns">
                <el-radio-group v-model="radio">
                    <el-radio-button label="3" >
                    전체
                    </el-radio-button>
                    <el-radio-button label="0" >
                    결제완료
                    </el-radio-button>
                    <el-radio-button label="1" >
                    결제취소
                    </el-radio-button>
                    <el-radio-button label="2" >
                    배달완료
                    </el-radio-button>
                </el-radio-group>
            </div>


            <div class="order_bills">
                <h3 v-if="bills.length==0">
                     들어온 주문이 없습니다! ㅠㅠ
                </h3>
                <div v-for="(bill, idx) in filtedBills" :key="idx">
                    <BillComp :bill="bill" />
                </div>
            </div>

        </div>



    </div>
</template>

<script>
import BillComp from '@/components/Mystore/BillComp.vue'
import {sellerOrderList} from '@/api/order.js'
export default {
    components: { BillComp },
    //버튼에따라 주문내역 필터링.
    watch:{
        radio: function(){
            this.reloadFilter()
        }

    },
    
    mounted(){
        //마운트 시 주문서 재로딩
        this.loadBills(this.$route.params.storeNo)

    },
    data() {
        return {
            radio: "3",
            //dummy
            bills :[],
            //필터기능 위한 배열.
            filtedBills:[],
        }
    },
    methods:{
        reloadFilter(){
            this.filtedBills=this.bills.filter(
                (val)=>{
                    if(this.radio=="3")
                    return true;
                    else if (val.status==this.radio)
                    return true;
                    else  return false;
                }
            )
        },
            //  bill:{
            //         "customerId": "string",
            //         "customerNo": 0,
            //         "marketName": "string",
            //         "marketNo": 0,
            //         "orderDate": "2022-08-15T09:30:47.114Z",
            //         "orderItems": [
            //         {
            //             "count": 0,
            //             "itemName": "string",
            //             "orderItemNo": 0,
            //             "orderNo": 0,
            //             "price": 0
            //         }
            //         ],
            //         "orderNo": 0,
            //         "status": "string",
            //         "storeName": "string",
            //         "storeNo": 0
            //     } 


        
        loadBills(storeNo){
            sellerOrderList(storeNo,
            (res)=>{
               console.log(`주문내역loaded data:${JSON.stringify(res.data)}`)
                
                //dummydummydummydummydummydummydummydummydummydummydummydummydummy
                res.data=[{orderNo:1,customerId:"재승",orderItems:[{itemName:"사과",count:3,price:2000 },{itemName:"배",count:2,price:3000 },{itemName:"수박",count:1,price:10000 }],orderDate:"20220803",status:0 },
                {orderNo:1,customerId:"재승",orderItems:[{itemName:"사과",count:3,price:2000 },{itemName:"배",count:2,price:3000 },{itemName:"수박",count:1,price:10000 }],orderDate:"20220803",status:1 }]
                
                console.log(`주문내역loaded data:${JSON.stringify(res.data)}`)
                this.bills=res.data
                this.reloadFilter()
            },
            (err)=>{
                console.log(err)
            })

        }
            
    }
}

</script>


<style scoped>

.header{
    display:flex;
}
.option_btns {
    display: flex;
}

.order_bills {
    display: grid;
    row-gap: 10px;
}
</style>