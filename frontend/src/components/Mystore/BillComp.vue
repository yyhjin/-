<template>
  <el-card class="bill_root">
    <div class="bill_header">
      <!-- bill:{
                    "customerId": "string",
                    "customerNo": 0,
                    "marketName": "string",
                    "marketNo": 0,
                    "orderDate": "2022-08-15T09:30:47.114Z",
                    "orderItems": [
                    {
                        "count": 0,
                        "itemName": "string",
                        "orderItemNo": 0,
                        "orderNo": 0,
                        "price": 0
                    }
                    ],
                    "orderNo": 0,
                    "status": "string",
                    "storeName": "string",
                    "storeNo": 0
                } -->
      <h2 class="order_date" style="margin-top: 5px; margin-bottom: 10px">
        {{ bill.orderDate.substr(0,10) }}
      </h2>
      <h3 class="order_no" style="margin-top: 5px">
        주문번호 : {{ bill.orderNo }}
        <!-- 상태: {{bill.status}} -->
      </h3>
    </div>
    <!-- 회원이름 = 판매자만 보이게 -->
    <div class="custmer_name" v-if="isSeller">
      {{ bill.customerName }}
    </div>
    <div>
      <div
        class="order_items"
        v-for="(item, idx) in this.bill.orderItems"
        :key="idx"
      >
        <div class="item">
          {{ item.itemName }} x {{ item.count }} |
          {{ item.price * item.count }}원
        </div>
      </div>
      <el-button
        @click="openDum"
        size="small"
        v-if="isSeller"
        style="margin-top: 5px; margin-bottom: 5px; color: white"
        round
        color="#e07c49"
        >
        >덤 추가!</el-button
      >
    </div>

    <!-- (구매자)step bar & 취소표시  -->
    <div v-if="!isSeller" style="margin-top: 10px">
      <el-steps :active="parseInt(bill.status) + 1" v-if="bill.status != '3'">
        <el-step
          :title="bill.status >= 0 ? '결제 완료' : ''"
          :description="bill.status == 0 ? '결제가 완료되었어요!' : ''"
        />
        <el-step
          :title="bill.status >= 1 ? '포장 완료' : ''"
          :description="bill.status == 1 ? '상인이 포장을 마쳤어요!' : ''"
        />
        <el-step
          :title="bill.status >= 2 ? '배달 완료' : ''"
          :description="bill.status == 2 ? '고객님께 도착했어요!' : ''"
        />
      </el-steps>
      <h3 v-else>취소된 주문입니다!</h3>
    </div>
    <!-- (판매자)status변경버튼 :구매자는 취소버튼만.-->
    <div>
      <el-button
        @click="changeState(1)"
        size="small"
        v-if="isSeller"
        color="#42413e"
        >포장 완료</el-button
      >
      <el-button
        @click="changeState(2)"
        size="small"
        v-if="isSeller"
        color="#42413e"
        >배달 완료</el-button
      >
      <el-button @click="changeState(3)" color="red" size="small" round
        >주문 취소
      </el-button>
    </div>
  </el-card>
</template>
<script>
import { changeOrderState } from "@/api/order.js";
export default {
  props: ["bill"], //status: 0=결제완료 1=포장완료 2=배달완료 3=취소
  data() {  
    return {
      data: this.bill,
      isSeller: this.$store.state.userInfo.userType == "판매자",
    };
  },
  methods: {
    changeState(orderstate) {
      const body = { orderNo: this.bill.orderstate, status: `${orderstate}` };
      changeOrderState(body, () => {
        console.log("성공");
      });
    },
    openDum() {
      this.$emit("emitDum",this.bill.orderNo);
    },
  },
};
</script>

<style>
.bill_root {
  width: 100%;
  max-width: 400px;
  margin: auto;
}
</style>
