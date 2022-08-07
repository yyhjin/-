<template>
    <div class="root_div">
        <h2>판매 내역</h2>
        <hr>
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
                <div v-for="(bill, idx) in filtedBills" :key="idx">
                    <BillComp :bill="bill" />
                </div>
            </div>

        </div>



    </div>
</template>

<script>
import BillComp from '@/components/Mystore/BillComp.vue'
export default {

    components: { BillComp },
    //버튼에따라 주문내역 필터링.
    watch:{
        radio: function(){
            this.filtedBills=this.bills.filter(
                (val)=>{
                    if(this.radio=="3")
                    return true;
                    else if (val.status==this.radio)
                    return true;
                    else  return false;
                }
            )
        }

    },
    
    mounted(){
        //watch옵션 작동시키기 위해 radio 초기화.
        this.radio=3

    },
    data() {
        return {
            radio: "",
            //dummy
            bills
                : [{
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
                    status: 2
                },

                ],
            //필터기능 위한 배열.
            filtedBills:[],
        }
    }
}
</script>


<style scoped>


.option_btns {
    display: flex;
}

.order_bills {
    display: grid;
    row-gap: 10px;
}
</style>