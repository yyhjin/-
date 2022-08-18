<template>
  <div>
    <el-card @click="dialogVisible = true" class="card1" shadow="always">
      <el-row>
        <el-col :span="6">
          <div class="order_pic">
            <el-avatar style="margin-top: 10px" shape="square" :size="50" />
          </div>
        </el-col>
        <el-col :span="18">
          <div class="order_discription" style="text-align: right">
            <div>
              <h4 style="margin: 0px">{{ order.storeName }}</h4>
            </div>
            <div class="order_discription_item">
              <!-- <span>구매 내역 :{{ order.orderItems[0].itemName }} </span> -->
              <span>주문 번호 : {{ order.orderNo }}</span
              ><br />
              <span>상태 : {{ order.status }}</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <el-dialog
      v-model="dialogVisible"
      title="상세 내역"
      width="90%"
      :before-close="handleClose"
    >
      <!-- <div style="text-align: center">
                <h2>{{ order.marketName }}</h2>
                <h3>가게:{{ order.storeName }}</h3>
                <h3>주문번호:{{ order.orderNo }}</h3>
                <h4>주문내역:</h4>
                <div v-for="(item, idx) in order.orderItems" :key="idx">
                    <h3>{{ item.itemName }} {{ item.count }}개 {{ item.price }}원</h3>
                </div>
                <h3>상태:{{ order.status }}</h3>
                <h3>날짜:{{ order.orderDate }}</h3>
            </div> -->

      <!-- 컴포넌트로 대체할게요. -->
      <BillComp :bill="data" />

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false" color="#42413e"
            >취소</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import BillComp from "@/components/Mystore/BillComp.vue";
import { ref } from "vue";
export default {
  components: {
    BillComp,
  },
  name: "OrderComp",
  props: ["order"],
  data() {
    return {
      data: this.order,
    };
  },

  setup() {
    const dialogVisible = ref(false);
    const handleClose = (done) => {
      done();
    };

    return { dialogVisible, handleClose };
  },
};
</script>
<style scoped>
.card1 {
  margin-bottom: 10px;
}
</style>
