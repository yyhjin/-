<template>
    <div>
        <el-card>
            <el-row >
                <el-col :span="6">
                    <div class="order_pic">
                        <el-avatar shape="square" :size="50" />
                    </div>
                </el-col>
                <el-col :span="18">
                    <div class="order_discription">
                        <div>
                            {{ order.store.store_name }}
                        </div>
                        <div class="order_discription_item">
                            <span>{{order.order_items[0].item_name}}</span>&nbsp;
                            <span>{{order.order_items[0].count}}</span>&nbsp;
                            <span>{{order.order_items[0].orderprice*order.order_items[0].count}}</span>
                        </div>  
                    </div>
                </el-col>
            </el-row>

        </el-card>

    </div>
</template>
<script>
export default {
    name: 'OrderComp',
    props: ['order'],
    data() {
        return {
            

        }
    }


}
</script>
