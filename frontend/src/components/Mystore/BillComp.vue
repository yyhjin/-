<template>
    <el-card class="bill_root" >
        <div class="bill_header">
            <div class="order_date">
                주문일자:{{ bill.orderdate }}
            </div>
            <div class="order_no">
                    주문번호:{{ bill.order_no }} 상태: {{bill.status}}
            </div>
        </div>
        
        
        

        <div class="custmer_name">
            {{ bill.customer_id }}
        </div>
        <div class="order_items" v-for="(item,idx) in this.bill.order_items" :key="idx">
            <div class="item">{{ item.item_name }} x {{ item.count }} | {{ item.price*item.count }}원</div>
        </div>
 
    </el-card>

</template>

<script>
export default {
    props: ['bill'],
    data() {
        return {

        }
    }

}
</script>

<style>
.bill_root{
    width:400px;
    margin:auto;
}
.bill_header{
    display:flex;
    justify-content: space-between;
}

</style>