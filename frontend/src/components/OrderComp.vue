<template>
    <div>
        <el-card v-for="(dummy, index) in dummyorder" :key="index">
            <el-row :glutter="20">
                <el-col :span="6">
                    <div class="order_pic">
                        <el-avatar shape="square" :size="50" />
                    </div>
                </el-col>
                <el-col :span="18">
                    <div class="order_discription">
                        <div>
                            {{ dummy.store.store_name }}
                        </div>
                        <div class="order_discription_item">
                            <span>{{dummy.order_items[0].item_name}}</span>&nbsp;
                            <span>{{dummy.order_items[0].count}}</span>&nbsp;
                            <span>{{dummy.order_items[0].orderprice*dummy.order_items[0].count}}</span>
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
    props: {},
    data() {
        return {
            dummyorder: [{
                order_no: 1,
                order_items: [
                    { item_no: 1, item_name: "사과", count: 3, orderprice: 2000 },
                    { item_no: 2, item_name: "포도", count: 2, orderprice: 3000 },
                    { item_no: 3, item_name: "수박", count: 1, orderprice: 10000 },
                    { item_no: 4, item_name: "샤인머스켓", count: 1, orderprice: 20000 }
                ],
                orderdate: "20220302",
                status: 1,
                store: { store_no: 1, store_name: "재승이네 청과", store_img: null }
            },
            {
                order_no: 1,
                order_items: [
                    { item_no: 1, item_name: "사과", count: 3, orderprice: 2000 },
                    { item_no: 2, item_name: "포도", count: 2, orderprice: 3000 },
                    { item_no: 3, item_name: "수박", count: 1, orderprice: 10000 },
                    { item_no: 4, item_name: "샤인머스켓", count: 1, orderprice: 20000 }
                ],
                orderdate: "20220302",
                status: 1,
                store: { store_no: 1, store_name: "재승이네 청과", store_img: null }
            },
            {
                order_no: 1,
                order_items: [
                    { item_no: 1, item_name: "사과", count: 3, orderprice: 2000 },
                    { item_no: 2, item_name: "포도", count: 2, orderprice: 3000 },
                    { item_no: 3, item_name: "수박", count: 1, orderprice: 10000 },
                    { item_no: 4, item_name: "샤인머스켓", count: 1, orderprice: 20000 }
                ],
                orderdate: "20220302",
                status: 1,
                store: { store_no: 1, store_name: "재승이네 청과", store_img: null }
            }
            ]

        }
    }


}
</script>
