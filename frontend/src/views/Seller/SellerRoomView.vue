<template>
    <div class="root_div">
        <div class="main">
            <!--  판매자 화면 송출 페이지. -->
            <div class="sellervideo">
                <user-video :stream-manager="mainStreamManager"/>
            </div>
            <!-- 채팅 컴포넌트 -->
            <ChatComp /></div>
            <!-- 입장한 고객들 CONTROL -->
            <div id="customers">
				<CustomerComp 
                v-for="(sub,idx) in subscribers" :key="idx" 
                :stream-manager="sub" :customerData="customers[idx]" @click="connectCustomer(customers[idx])"
                />
			</div>

        <!-- <div class="customers">
            <el-card :class="'user '+user.status" v-for="(user,idx) in users" :key="idx" @click="connectUser(idx)">
                <div>{{user.id}}</div>
                <div>{{user.name}}</div>
            </el-card>
        </div> -->

        <!-- 상점 버튼 -->
        <div class="btns">
            <el-button type="primary" style="margin-left: 16px" @click="clickBills()">
                주문서
            </el-button>
            <el-button type="primary" style="margin-left: 16px" @click="clickMenues()">
                메뉴수정
            </el-button>
        </div>

        <!-- 메뉴수정 drawer -->
        <el-drawer v-model="drawer_menues" title="메뉴수정" size="50%" @click="clickMenues">
            <MenuComp/>
        </el-drawer>
         <!-- 주문서 drawer -->
        <el-drawer v-model="drawer_bills" title="주문서" size="50%">
            <div>
                <BillComp v-for="bill in bills" :bill="bill" :key="bill.orderNo"/>

                <!-- <div v-for="bill in bills" :key="bill">
                    <BillComp :bill="bill" />
                    <el-button @click="innerDrawer = true">덤 추가!</el-button>
                </div> -->
                <!-- 덤 추가 INNER DRAWER -->
                <el-drawer v-model="innerDrawer" title="I'm inner Drawer" :append-to-body="true">
                    <el-button v-for="item in items" :key="item" @click="addService()">
                        {{ item }}
                    </el-button>
                </el-drawer>
            </div>
        </el-drawer>

    </div>

</template>
<script>
/* eslint-disable */ 

import { ref,onMounted } from "vue";
import axios from 'axios';
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import { OpenVidu } from "openvidu-browser";
//Comps
import UserVideo from '@/components/Openvidu/UserVideo.vue';
import CustomerComp from '@/components/Openvidu/CustomerComp.vue';
import ChatComp from '@/components/Openvidu/RoomChat.vue'
import MenuComp from '@/components/Room/MenuComp.vue'
import BillComp from '@/components/Mystore/BillComp.vue'
//APIs
import {sellerOrderList} from '@/api/order.js'
import {menuList ,getItem,delelteItem,modifyItem,addItem} from '@/api/item.js'

axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443"
const OPENVIDU_SERVER_SECRET = "MY_SECRET"

export default{
//마운트 시 세션 START.
mounted(){
    this.joinSession()
},
beforeRouteLeave (to, from, next) {
  const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
  if (answer) {
    this.session.disconnect();
      next()
  } else {
    next(false)
  }
},
components:{
    ChatComp,UserVideo,CustomerComp,MenuComp,BillComp
},

setup(){
//router,store
    const route = useRoute()
    const store = useStore()

//openvidu 변수
    // axios.defaults.headers.post['Content-Type'] = 'application/json'
    
    const OV = ref(undefined)
    const session = ref(undefined)
    const mainStreamManager= ref(undefined)
    const publisher =ref(undefined)
    const subscribers = ref([])
    

    const sessionName = ref(route.params.storeNo) //mySessionId :StoreNo로 넣을게요
    const clientdata = ref({ //myUserName
        type:route.params.isSeller,//0,1
        userName:route.params.userName,
        userNo:route.params.userNo,
    })

    //구매자only 코드
    const isConnected =ref(false)
    //판매자only 코드 :손님정보 저장.
    const customers = ref([])//  {customerNo,customerName,connectionId,isConnected}
    
    
    
    
    console.log("unused variable check:"+OV+session+mainStreamManager+publisher+subscribers+sessionName+clientdata)

const joinSession = function() {
    // --- Get an OpenVidu object ---
    this.OV=new OpenVidu()
    // --- Init a session ---
    this.session = this.OV.initSession();
    // --- Specify the actions when events take place in the session ---
    
    ///////////////////// signal  ////////////////////////
    this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);

        //기본 연결시 video off 음성off 로 설정.
        subscriber.properties.subscribeToVideo =  false;
        subscriber.properties.subscribeToAudio =  true;
        this.subscribers.push(subscriber);
        
        //입장손님 정보 push
        const tmp= JSON.parse(subscriber.stream.connection.data).clientData
        this.customers.push({
            customerNo:tmp.userNo,
            customerName:tmp.userName,
            connectionId:subscriber.stream.connection.connectionId,
            isConnected:tmp.isConnected,
        })
        console.log(JSON.stringify(this.customers[-1]))

    });

    this.session.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
            this.subscribers.splice(index, 1);
        }
        //입장손님 data도 같이 날려주자.
        this.customers.splice(index,1);
    });

    this.session.on('exception', ({ exception }) => {
        console.warn(exception);
    });

    //구매자쪽 코드.
    this.session.on('signal:linkCall', (event) => {
         console.log("호출변경")
        if(event.data==this.publisher.stream.connection.connectionId){
            console.log(this.isConnected)
            this.isConnected = !this.isConnected;
            this.publisher.publishAudio(this.isConnected); //false
            this.publisher.publishVideo(this.isConnected); //false
            console.log(this.isConnected? '연결됨':'연결해제됨')
            
        }
    });

    // --- Connect to the session with a valid user token ---

    // 'getToken' method is simulating what your server-side should do.
    // 'token' parameter should be retrieved and returned by your own backend
    this.getToken(this.sessionName).then(token => {
        this.session.connect(token, { clientData: this.clientdata })
            .then(() => {

                // --- Get your own camera stream with the desired properties ---

                // 판매자의 경우엔 aud vid 둘다 true 
                // 구매자의 경우엔 aud만 true
                let publisher = this.OV.initPublisher(undefined, {
                    audioSource: undefined, // The source of audio. If undefined default microphone
                    videoSource: undefined, // The source of video. If undefined default webcam
                    publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                    publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                    resolution: '400x300',  // The resolution of your video
                    frameRate: 30,			// The frame rate of your video
                    insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                    mirror: false       	// Whether to mirror your local video or not
                });
                //mainStreamManager을 seller의 것으로 지정해야하는데! 나중에 하자.
                this.mainStreamManager = publisher;
                this.publisher = publisher;

                // --- Publish your stream ---

                this.session.publish(this.publisher);
            })
            .catch(error => {
                console.log('There was an error connecting to the session:', error.code, error.message);
            });
    });

    window.addEventListener('beforeunload', this.leaveSession)
}

function leaveSession() {
    // --- Leave the session by calling 'disconnect' method over the Session object ---
    if (this.session) this.session.disconnect();

    this.session = undefined;
    this.mainStreamManager = undefined;
    this.publisher = undefined;
    this.subscribers = [];
    this.OV = undefined;

    window.removeEventListener('beforeunload', this.leaveSession);
}

function updateMainVideoStreamManager (stream) {
    if (this.mainStreamManager === stream) return;
    this.mainStreamManager = stream;
}

/**
 * --------------------------
 * SERVER-SIDE RESPONSIBILITY
 * --------------------------
 * These methods retrieve the mandatory user token from OpenVidu Server.
 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
 * the API REST, openvidu-java-client or openvidu-node-client):
 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
 *   3) The Connection.token must be consumed in Session.connect() method
 */

function getToken(mySessionId) {
    return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
}

// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
function createSession (sessionId) {
    return new Promise((resolve, reject) => {
        axios
            .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
                customSessionId: sessionId,
            }), {
                auth: {
                    username: 'OPENVIDUAPP',
                    password: OPENVIDU_SERVER_SECRET,
                },
            })
            .then(response => response.data)
            .then(data => resolve(data.id))
            .catch(error => {
                if (error.response.status === 409) {
                    resolve(sessionId);
                } else {
                    console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
                    if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
                        location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
                    }
                    reject(error.response);
                }
            });
    });
}

// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
function createToken (sessionId) {
    return new Promise((resolve, reject) => {
        axios
            .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
                auth: {
                    username: 'OPENVIDUAPP',
                    password: OPENVIDU_SERVER_SECRET,
                },
            })
            .then(response => response.data)
            .then(data => resolve(data.token))
            .catch(error => reject(error.response));
    });
}
//호출 연결.
function connectCustomer(customer){
    console.log(customer.connectionId)
    this.session.signal({
        type: 'linkCall',
        data: customer.connectionId,
        to: []
      })
      .then(
        customer.isConnected = !customer.isConnected
        

      )
 }

/* bills */

//  주문 flow : db의존으로.
//         1. 고객이 주문을 넣으면  axios 요청과 / signal 을 모두 보낸다.
//         2. 판매자는 signal 을 통해 주문서 도착 여부를 확인할 수 있다.
//         3. axios 요청에의해 주문은 db에 들어가 있으며,
//         4. 판매자는 주문서 탭을 작동 시 axios 요청으로 주문서를 갱신한다.
//          
const drawer_bills = ref(false)
const bills = ref([])

const loadBills = function(storeId){
    sellerOrderList(storeId,
    (res)=>{
        //dummy
        res.data=[{orderNo:1,customerId:"재승",orderItems:[{itemName:"사과",count:3,price:2000 },{itemName:"배",count:2,price:3000 },{itemName:"수박",count:1,price:10000 }],orderDate:"20220803",status:0 },
        {orderNo:1,customerId:"재승",orderItems:[{itemName:"사과",count:3,price:2000 },{itemName:"배",count:2,price:3000 },{itemName:"수박",count:1,price:10000 }],orderDate:"20220803",status:1 }]

        this.bills=res.data
    },
    ()=>{})
}
const clickBills = function(){
    this.loadBills(route.params.storeNo)
    console.log(JSON.stringify(this.bills))
    this.drawer_bills=true;
    
}



/* chatting */



/* 메뉴 */
const drawer_menues = ref(false)

const clickMenues = function(){
    this.drawer_menues=true;
    menuList(route.params.storeNo,//입장할때 받아온 storeNo
    (res)=>{
        this.items=res.data
        console.log(JSON.parse(this.items))
    },
    ()=>{})
    } 


/* 덤 */
const innerDrawer = ref(false)
const items = ref([])

   return{
    //components, 
    UserVideo,ChatComp,
    //openvidu
    OV,session,mainStreamManager,publisher,subscribers,sessionName,clientdata,joinSession,leaveSession,
    updateMainVideoStreamManager,getToken,createSession,createToken,connectCustomer,
    isConnected,
    customers,
    //bills
    drawer_bills,bills,loadBills,clickBills,
    //chatting
    
    //menues
    drawer_menues,innerDrawer,items,clickMenues,
    }

 }// setup 종료.
}
</script>

<style scoped>
#customers{
    display:flex;
    flex-direction: row;
    flex-wrap: wrap;
}

.customer{
    border: 2px;
    border-style: groove;

    width:150px;
    height:120px;
    margin:10px;
}
.on{
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


.sellervideo {
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