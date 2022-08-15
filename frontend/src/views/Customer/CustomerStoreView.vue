<template>
<div>
  <el-scrollbar height="800px">
    <div class="about">
      <h3 style="margin-left:10px; display:inline-block">{{this.storeName}}</h3>
      <el-button type="danger" plain @click="btn_out()" style="float:right; margin:20px;">나가기</el-button>
    </div>
    <div style="height :200px">
      오픈비두
    </div>
    <div style="text-align : center">
      <h3 style="display:inline-block"  v-if="this.hochul == true">호출 대기 중 입니다.....</h3>
      <h3 style="display:inline-block"  v-else > 호출을 눌러 문의하세요 </h3>
      <button @click="cl_hochul()" v-if="this.hochul == false" style="float:right; margin-top:20px; margin-right:10px">호출하기
      </button>
      <button @click="cl_hochul()" v-else style="float:right; margin-top:20px; margin-right:10px">호출취소
      </button>
    </div>
    <div class="scrollbar-flex-content ">
        <div v-for="(menu, idx) in menus" :key="idx" class="scrollbar-demo-item" @click=cl_item(menu)>
            <store-menu :menu="menu" />
        </div>
    </div>
    <div>
      <div class="div_content">
        <div>
          <div v-if="this.content==true">
            <div>
              <h3 style="margin-left:10px; display:inline-block">장바구니</h3>
              <el-button type="danger" plain @click="btn_order()" style="float:right; margin:20px">주문하기</el-button>
            </div>
            <div >
              <el-table :data="orderItems" style="width: 90%; margin:auto; " max-height="250">
                <el-table-column prop="itemName" label="물품" width="90" />
                <el-table-column prop="price" label="가격" width="90" />
                <el-table-column prop="count" label="수량" width="60" />
                <el-table-column label="삭제" width="60">  
                  <template #default="scope">
                    <el-button
                      link
                      type="primary"
                      size="small"
                      @click.prevent="deleteRow(scope.$index)"
                    >
                      삭제
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <div style="text-align:right; margin-right:20px; margin-top:-10px">
                <h4>합계 : {{money}}원</h4>
              </div>
            </div>
            <div>
              <h4 style="margin-left:10px; margin-top:-30px">추가하기</h4>
              <div style="text-align:center">
              <el-input v-model="this.selected.itemName" placeholder="품목" style="width:155px">
                <template #prepend >품목</template>
              </el-input>
              <el-input v-model="this.selected.count" placeholder="수량" style="width:155px">
                <template #prepend>수량</template>
              </el-input>
              </div>
              <div style="text-align:right">
                <el-button type="danger" :icon="Check" circle @click="btn_add()" style="margin:10px 20px;"/>
              </div>
            </div>
          </div>

          <div v-else>
            <room-chat></room-chat>
          </div>
        </div>
      </div>
      <div>
        <el-button type="danger" plain @click="btn_jang()" style="margin-left:5px">장바구니</el-button>
        <el-button type="danger" plain @click="btn_chat()">채팅</el-button>
      </div>
    </div>
  </el-scrollbar>
</div>  
</template>
<script>
import StoreMenu from "@/components/Room/StoreMenu.vue";
//import UserVideo from "@/components/Openvidu/UserVideo";
import RoomChat from "@/components/Openvidu/RoomChat.vue";
import {Check} from "@element-plus/icons-vue";
//import {useStore} from "vuex";
//import {getItem} from "@/api/market";
import {getOrder} from "@/api/market";
import { ref } from "vue";
import { ElMessage } from 'element-plus'
//import { OpenVidu } from "openvidu-browser";

//axios.defaults.headers.post["Content-Type"] = "application/json";

//const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
//const OPENVIDU_SERVER_SECRET = "MY_SECRET";

window.onbeforeunload = () => {
  // Gracefully leave session
  if (this.session) {
    this.removeUser();
    this.leaveSession();
  }
};

export default {
  components: {
    StoreMenu,
    RoomChat,
  },
  data(){
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      new_subscribers: [],

      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },
  setup(){
    //const store = useStore();
   // const customerNo = computed(() => store.state.userInfo.userNo);
    //const sellerNo = ref();
    const storeName = ref("싱싱청과물");
    const customerNo= ref("1");
    const sellerNo= ref("1");
    
    const menus = ref([{
      itemName:"고기",
      itemNo:"1",
      price:"1000",
      recent:"true",
  },
  {
      itemName:"사과",
      itemNo:"2",
      price:"3500",
      recent:"true",
  }
  ]);
    const orderItems = ref([{
      itemName:"꽁치",
      itemNo: "3",
      count:"1",
      price:"5000",
    },
    {
      itemName:"삼겹살",
      itemNo: "4",
      count: "1",
      price:"10000",
    }]);
    
    const selected = ref({
      itemName:"",
      itemNo:"",
      count: "",
      price:"",
    });

    const params=ref([{
      itemNo: "3",
      count:"1",
    },
    {
      itemNo: "4",
      count: "1",
    }])
    const hochul = ref(false);
    const content = ref(true);
    
    const deleteRow = (index) => {
      orderItems.value.splice(index, 1)
      params.value.splice(index,1)
    }

    const open = (message) => {
      ElMessage.error(message);
    }

    return{Check ,params,customerNo, storeName, sellerNo, menus , selected, orderItems, hochul , content, deleteRow, open};
  },
  computed: {
    money(){
      var sum=0;
      for(var i = 0; i<this.orderItems.length; i++){
        sum += this.orderItems[i].count * this.orderItems[i].price;
      }

      return sum;

    },
  },
  created() {
    //this.sellerNo = this.$route.path.no (판매자 번호 받기)
    //this.storeName = this.$route.params.storeName (가게 이름 받ㄱ기)
    this.no = "1";
    /* 메뉴검색
    getItem(
      12,
      (response)=> {
        console.log(response);
        //this.menus = response.data;
      },
      (error) => {
        console.log(error);
      }
      )
      */
  },
  methods: {
    btn_out(){
      //뒤로가기 만들기
    },
    btn_order(){

      getOrder(
        //this.params,
        //JSON.stringify(this.params),
        //{storeNo:this.sellerNo, customerNo:this.customerNo},
        this.customerNo,
        this.sellerNo,
        this.params,
        (response)=> {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      )
    },
    cl_item(menu){
      this.selected = menu;
    },
    cl_hochul(){
      if(this.hochul == false){
        console.log("호출");
        this.hochul = true;
      }else {
        console.log("호출취소");
        this.hochul =false;
      }
    },
    btn_jang(){
      this.content=true;
      console.log("jang");
    },
    btn_chat(){
      this.content=false;
      console.log("chat");
    },
    btn_add(){
      console.log(this.selected.itemName)
      if(this.selected.itemName == ""){
        this.open("품목을 골라주세요");
      } else if(this.selected.count == ""){
        this.open("수량을 입력해주세요")
      } else {
        this.orderItems.push({itemNo:this.selected.itemNo, count:this.selected.count, itemName:this.selected.itemName, price:this.selected.price});
        this.params.push({itemNo:this.selected.itemNo, count:this.selected.count});
        this.selected={};
      }
      
    },


  },
}
</script>
<style scoped>
.el-button--danger.is-link, .el-button--danger.is-plain, .el-button--danger.is-text {
  --el-button-hover-bg-color : rgb(255, 111,97,30%) !important;
  --el-button-hover-border-color : rgb(255, 111,97,30%) !important;
}

.div_content { 
  margin-top: 20px;
  background: rgb(255, 111,97,30%);
  border-radius: 10px;
}

.scrollbar-flex-content {
  display: flex;
  border: 1px solid #ff6f61;
  border-radius: 10px;
}
.scrollbar-demo-item {
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-danger-light-9);
  color: var(--el-color-danger);
}
</style>
