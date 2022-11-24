<template>
    <div class="root_div">
        <div class="header">
            <nav
    class="nav_room"
    v-if="$route.path.substring(0, 5) == '/room'"
    style="display: flex; justify-content: space-between"
  >
    <h2 style="margin-top:0;">{{storeIntro}}</h2>
    <span>
    <!-- TODO:문 닫을때 close 처리 해줘야해요. -->
      <el-button
        type="danger"
        size="large"
        @click="
        $router.push({
            name: 'mystore',
            params: { id: $store.getters['userInfo/isAuthenticated'] },
        })
        "
        >닫기</el-button
        >
    </span>
  </nav>
  <div v-if="callQueue.length!=0"><el-alert title="손님이 호출을 기다리고 있어요!" type="error" effect="dark" :closable="false"/></div>
        </div>
        <div class="main" style="display:flex; margin-bottom:10px;">
            <!--  판매자 화면 송출 페이지. -->
            <div style="display:flex ;flex-direction:column;height:400px;" >
                <el-card>
                    <div class="sellervideo">
                    <user-video :stream-manager="mainStreamManager" />
                    </div>
                    <!-- 채팅 컴포넌트 -->
                    <div class="information panel"></div>
                </el-card>
                
                
            </div>
                 <RoomChatSeller ref="chat" @message="sendMessage" :subscribers="subscribers"></RoomChatSeller>
            </div>
            
           
        
        <!-- 입장한 고객들 CONTROL -->
         <el-scrollbar height="160px">
                        <el-card>
                            <h3 v-if="subscribers.length==0">아직 손님이 없네요..</h3>
                 <div id="customers" style="width:100%; height:120px;  margin-bottom:10px">
                <CustomerComp v-for="(sub, idx) in subscribers" :key="idx" :stream-manager="sub" :customerData="customers[idx]" @click="connectCustomer(customers[idx])" />
             </div>
            </el-card>
        </el-scrollbar>


       

        <!-- <div class="customers">
            <el-card :class="'user '+user.status" v-for="(user,idx) in users" :key="idx" @click="connectUser(idx)">
                <div>{{user.id}}</div>
                <div>{{user.name}}</div>
            </el-card>
        </div> -->

        <!-- 상점 버튼 -->
        <div class="btns" style="margin-top:10px; display:flex; justify-content:space-between" >
             <div>
                 <el-badge :value="newbillcount">
                    <el-button type="primary" size="large" style="margin-left: 16px" @click="clickBills()"> 주문서 </el-button>
                </el-badge>
                <el-button type="primary" size="large" style="margin-left: 16px" @click="clickMenues()"> 메뉴관리 </el-button>

                <el-button type="danger" size="large" style="margin-left: 16px" @click="nextCall()"> 호출고객연결 </el-button>
            </div>
            <h4>구경중인 손님 : {{ customers.length }}명 | 호출 대기중 손님: {{ callQueue.length }}명</h4>
           
        </div>

        <!-- 메뉴수정 drawer -->
        <el-drawer v-model="drawer_menues" title="메뉴수정" size="50%" @click="clickMenues">
            <InstoreMenuComp :instoreMenu="instoreMenu" @delItem="deleteMenu" @addItem="addMenu" @updatePrice="updateMenuPrice" @updateSoldout="updateMenuSoldout" />
            <div class="addmenu" style="margin-top: 5px">
                <el-button v-for="item in dbMenues" :key="item" @click="addMenu(item)">{{ item.itemName }}</el-button>
            </div>
        </el-drawer>
        <!-- 주문서 drawer -->
        <el-drawer v-model="drawer_bills" title="주문서" size="50%">
            <div>
                <h3 v-if="bills.length==0">아직 주문이 없네요</h3>
                <BillComp @emitDum="showDum(bill.orderNo)" v-for="bill in bills" :bill="bill" :key="bill.orderNo" />

                <!-- <div v-for="bill in bills" :key="bill">s 
                    <BillComp :bill="bill" />
                    <el-button @click="innerDrawer = true">덤 추가!</el-button>
                </div> -->
                <!-- 덤 추가 INNER DRAWER -->
                <el-drawer v-model="innerDrawer" title="덤 추가하기" :append-to-body="true">
                    <el-card v-for="item in instoreMenu" :key="item" @click="onboardDum(item.itemName)">
                        <div style="display:flex ;justify-content:space-between"><h2> {{ item.itemName }}</h2> <h2>{{dumArr.filter(element=>item.itemName===element).length}}</h2></div>
                    </el-card>
                    <el-button @click="addDum(this.dumOrderNo,this.dumArr)" >덤 확정!</el-button>
                    <el-button @click="clearDum">덤 목록 비우기!</el-button>
                </el-drawer>
            </div>
        </el-drawer>
    </div>
</template>
<script>
/* eslint-disable */

import { ref,computed } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import { useStore } from "vuex";
import { OpenVidu } from "openvidu-browser";
import { ElMessage,ElNotification } from "element-plus";
import PhoneRinging from "@/assets/PhoneRinging.mp3";
import servicebell from "@/assets/servicebell.mp3";

//Comps
import UserVideo from "@/components/Openvidu/UserVideo.vue";
import CustomerComp from "@/components/Openvidu/CustomerComp.vue";
import RoomChatSeller from "@/components/Openvidu/RoomChatSeller.vue";
import InstoreMenuComp from "@/components/Room/InstoreMenuComp.vue";
import BillComp from "@/components/Mystore/BillComp.vue";
//APIs
import { sellerOrderList,pushDum } from "@/api/order.js";
import { menuList } from "@/api/item.js";

axios.defaults.headers.post["Content-Type"] = "application/json";
const OPENVIDU_SERVER_URL = "https://" + "i7a602.p.ssafy.io" + ":4443";
const OPENVIDU_SERVER_SECRET = "jangbo602";


export default {
    //메뉴변경시 소비자들에게 push.
    //마운트 시 세션 START.
    mounted() {
        this.joinSession();
    },
    beforeRouteLeave(to, from, next) {
        const answer = window.confirm("Do you really want to leave? you have unsaved changes!");
        if (answer) {
            this.session.disconnect();
            next();
        } else {
            next(false);
        }
    },
    components: {
        RoomChatSeller,
        UserVideo,
        CustomerComp,
        InstoreMenuComp,
        BillComp,
    },

    setup() {
        //router,store
        const route = useRoute();
        const store = useStore();

        //openvidu 변수
        // axios.defaults.headers.post['Content-Type'] = 'application/json'

        const OV = ref(undefined);
        const session = ref(undefined);
        const mainStreamManager = ref(undefined);
        const publisher = ref(undefined);
        const subscribers = ref([]);
        
        //내(판매자) 정보
        const storeNo = route.params.storeNo;
        const userId = computed(() => store.state.userInfo.userId);
        const storeIntro = ref(route.params.storeIntro);
        const storeSubject = ref(route.params.storeSubject);

        const sessionName = ref(route.params.storeNo); //mySessionId :storeNo로 넣을게요
        const clientdata = ref({
            //myUserName
            type: route.params.isSeller, //0,1
            userName: `[판매자]${route.params.userName}`,
            userNo: route.params.userNo,
        });

        //구매자only 코드
        const isConnected = ref(false);
        //판매자only 코드 :손님정보 저장.
        const customers = ref([]); //  {customerNo,customerName,connectionId,connection,isConnected}
        console.log("unused variable check:" + OV + session + mainStreamManager + publisher + subscribers + sessionName + clientdata);

        const joinSession = function () {
            // --- Get an OpenVidu object ---
            this.OV = new OpenVidu();
            // --- Init a session ---
            this.session = this.OV.initSession();
            // --- Specify the actions when events take place in the session ---

            /*--------------------------------------------------------------------SIGNAL on----------------------------------------------------------- */
            this.session.on("streamCreated", ({ stream }) => {
                const subscriber = this.session.subscribe(stream);

                //기본 연결시 video off 음성off 로 설정.
                subscriber.properties.subscribeToVideo = false;
                subscriber.properties.subscribeToAudio = true;
                this.subscribers.push(subscriber);

                //입장손님 정보 push
                const tmp = JSON.parse(subscriber.stream.connection.data).clientData;
                console.log("################################################customers 체크");
                console.log(subscriber.stream.connection.connectionId);
                const customer = {
                    customerNo: tmp.userNo,
                    customerName: tmp.userName,
                    subscriber: subscriber,
                    connection: subscriber.stream.connection,
                    connectionId: subscriber.stream.connection.connectionId,
                    isConnected: false,
                    isCalling: false,
                };
                this.customers.push(customer);
                console.log(customer);

                console.log("고객입장~");
                this.visitNotification(); //입장사운드 재생.
                this.transferMenu(); //메뉴 전송
            });

            this.session.on("streamDestroyed", ({ stream }) => {
                const index = this.subscribers.indexOf(stream.streamManager, 0);
                if (index >= 0) {
                    this.subscribers.splice(index, 1);
                }
                //입장손님 data도 같이 날려주자.
                this.customers.splice(index, 1);
            });

            this.session.on("exception", ({ exception }) => {
                console.warn(exception);
            });

            //구매자쪽 코드.
            // this.session.on("signal:linkCall", (event) => {
            //     console.log("호출변경");
            //     if (event.data == this.publisher.stream.connection.connectionId) {
            //         console.log(this.isConnected);
            //         this.isConnected = !this.isConnected;
            //         this.publisher.publishAudio(this.isConnected); //false
            //         this.publisher.publishVideo(this.isConnected); //false
            //         console.log(this.isConnected ? "연결됨" : "연결해제됨");
            //     }
            // });

            this.session.on("signal:makeOrder", (event) => {
                console.log(JSON.parse(event.data) + "님에게서 주문 도착"); //모달 alert 사용하기.
                ElNotification({
                    title: '주문 도착',
                    message: JSON.parse(event.data) + "님에게서 주문 도착",
                    type: 'success',
                    })
                console.log(this.newbillcount)
                this.newbillcount++;
            });

            //파라미터 체크 완료.
            this.session.on("signal:send-call", (event) => {
                const data = JSON.parse(event.data);
                console.log(data.userName + "님에게서 호출 도착"); //모달 alert 사용하기.
                ElMessage({
                    message: `고객이 호출하였습니다!!!!!`,
                    type: "error",
                });
                this.callNotificationPlay();
                for (var i = 0; i < this.customers.length; i++) {
                    if (this.customers[i].connectionId == data.connectionId) {
                        this.customers[i].isCalling = true;
                        this.callQueue.push(this.customers[i]);
                        console.log(`${this.customers[i].connectionId} 호출 큐에 추가되었습니다.`);
                        this.broadcastCallCount();
                        break;
                    }
                }
            });
            this.session.on("signal:delete-call", (event) => {
                const param = event.data;
                this.callNotificationStop();
                console.log(param + "님이 호출을 취소했습니다.");{ //모달 alert 사용하기.
                ElMessage({
                    message: `고객이 호출을 취소했습니다.`,
                });
                for (var i = 0; i < this.callQueue.length; i++) 
                    if (this.callQueue[i].connectionId == param) {
                        //i번째를 큐에서 제거.
                        this.callQueue.splice(i, 1);
                        console.log(`${this.customers[i].connectionId} 호출 큐에서 삭제되었습니다..`);
                        this.broadcastCallCount();
                        break;
                    }
                }
                //해당 손님의 iscalling:false
                for (var i = 0; i < this.customers.length; i++) {
                    if (this.customers[i].connectionId == param) {
                        this.customers[i].isCalling = false;
                    }
                }
            });
            this.session.on("signal:public-chat", (event) => {
                console.log(JSON.parse(event.data).sender);
                this.$refs.chat.addMessage(event.data, JSON.parse(event.data).sender === this.userId, false);
            });

            // private 채팅 signal 받기
            this.session.on("signal:private-chat", (event) => {
                this.$refs.chat.addMessage(event.data, false, true);
            });

            // --- Connect to the session with a valid user token ---

            // 'getToken' method is simulating what your server-side should do.
            // 'token' parameter should be retrieved and returned by your own backend
            this.getToken(this.sessionName).then((token) => {
                this.session
                    .connect(token, { clientData: this.clientdata })
                    .then(() => {
                        // --- Get your own camera stream with the desired properties ---

                        // 판매자의 경우엔 aud vid 둘다 true
                        // 구매자의 경우엔 aud만 true
                        let publisher = this.OV.initPublisher(undefined, {
                            audioSource: undefined, // The source of audio. If undefined default microphone
                            videoSource: undefined, // The source of video. If undefined default webcam
                            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                            publishVideo: true, // Whether you want to start publishing with your video enabled or not
                            resolution: "400x300", // The resolution of your video
                            frameRate: 30, // The frame rate of your video
                            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                            mirror: false, // Whether to mirror your local video or not
                        });
                        //mainStreamManager을 seller의 것으로 지정해야하는데! 나중에 하자.
                        this.mainStreamManager = publisher;
                        this.publisher = publisher;

                        // --- Publish your stream ---

                        this.session.publish(this.publisher);
                    })
                    .catch((error) => {
                        console.log("There was an error connecting to the session:", error.code, error.message);
                    });
            });

            window.addEventListener("beforeunload", this.leaveSession);
        };

        function leaveSession() {
            // --- Leave the session by calling 'disconnect' method over the Session object ---
            if (this.session) this.session.disconnect();

            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;

            window.removeEventListener("beforeunload", this.leaveSession);
        }

        function updateMainVideoStreamManager(stream) {
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
            return this.createSession(mySessionId).then((sessionId) => this.createToken(sessionId));
        }

        // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
        function createSession(sessionId) {
            return new Promise((resolve, reject) => {
                axios
                    .post(
                        `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
                        JSON.stringify({
                            customSessionId: sessionId,
                        }),
                        {
                            auth: {
                                username: "OPENVIDUAPP",
                                password: OPENVIDU_SERVER_SECRET,
                            },
                        }
                    )
                    .then((response) => response.data)
                    .then((data) => resolve(data.id))
                    .catch((error) => {
                        if (error.response.status === 409) {
                            resolve(sessionId);
                        } else {
                            console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
                            if (
                                window.confirm(
                                    `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                                )
                            ) {
                                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
                            }
                            reject(error.response);
                        }
                    });
            });
        }

        // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
        function createToken(sessionId) {
            return new Promise((resolve, reject) => {
                axios
                    .post(
                        `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
                        {},
                        {
                            auth: {
                                username: "OPENVIDUAPP",
                                password: OPENVIDU_SERVER_SECRET,
                            },
                        }
                    )
                    .then((response) => response.data)
                    .then((data) => resolve(data.token))
                    .catch((error) => reject(error.response));
            });
        }
        //호출 연결.
        function connectCustomer(customer) {
            console.log(customer.connectionId);
            this.callNotificationStop();//연결시 호출소리 해제.
            this.session
                .signal({
                    type: "linkCall",
                    data: customer.connectionId,
                    to: [customer.connection],
                })
                .then(() => {
                    customer.isConnected = !customer.isConnected;
                });
        }

        /* bills */

        //  주문 flow : db의존으로.
        //         1. 고객이 주문을 넣으면  axios 요청과 / signal 을 모두 보낸다.
        //         2. 판매자는 signal 을 통해 주문서 도착 여부를 확인할 수 있다.
        //         3. axios 요청에의해 주문은 db에 들어가 있으며,
        //         4. 판매자는 주문서 탭을 작동 시 axios 요청으로 주문서를 갱신한다.
        //
        const drawer_bills = ref(false);
        const bills = ref([]);
        const newbillcount = ref(0);

        const loadBills = function (storeId) {
            sellerOrderList(
                storeId,
                (res) => {
                    //TODO:dummy삭제
                    // res.data = [
                    //     {
                    //         orderNo: 1,
                    //         customerId: "재승",
                    //         orderItems: [
                    //             { itemName: "사과", count: 3, price: 2000 },
                    //             { itemName: "배", count: 2, price: 3000 },
                    //             { itemName: "수박", count: 1, price: 10000 },
                    //         ],
                    //         orderDate: "20220803",
                    //         status: 0,
                    //     },
                    //     {
                    //         orderNo: 1,
                    //         customerId: "재승",
                    //         orderItems: [
                    //             { itemName: "사과", count: 3, price: 2000 },
                    //             { itemName: "배", count: 2, price: 3000 },
                    //             { itemName: "수박", count: 1, price: 10000 },
                    //         ],
                    //         orderDate: "20220803",
                    //         status: 1,
                    //     },
                    // ];

                    this.bills = res.data;
                },
                () => {}
            );
        };
        const clickBills = function () {
            this.loadBills(route.params.storeNo);
            console.log(JSON.stringify(this.bills));
            this.drawer_bills = true;
            this.newbillcount = 0;
        };

        /* chatting */
        function sendMessage({ content, to }) {
            let now = new Date();
            let current = now.toLocaleTimeString([], {
                hour: "2-digit",
                minute: "2-digit",
                hour12: false, // true인 경우 오후 10:25와 같이 나타냄.
            });

            let messageData = {
                content: content,
                sender: userId.value,
                time: current,
            };

            // 전체 메시지
            if (to === "all") {
                session.value
                    .signal({
                        data: JSON.stringify(messageData),
                        to: [],
                        type: "public-chat",
                    })
                    .then(() => {
                        console.log("메시지 전송 완료");
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            }

            // 개인 메시지
            if (to !== "all") {
                this.session
                    .signal({
                        data: JSON.stringify(messageData),
                        to: [to],
                        type: "private-chat",
                    })
                    .then(() => {
                        // 내가 보낸 개인 메시지 추가
                        this.$refs.chat.addMessage(JSON.stringify(messageData), true, true);
                        console.log("메시지 전송 완료");
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            }
        }

        /* 메뉴 */
        const drawer_menues = ref(false);
        const instoreMenu = ref(JSON.parse(route.params.instoreMenu)); //오픈메뉴 params로받아오기 , 객체에 품절여부 속성 추가하기.

        //메뉴사이드바 발동.
        const clickMenues = function () {
            this.drawer_menues = true;
            //db메뉴 로드
            menuList(this.storeNo, (res) => {
                this.dbMenues = res.data;
                console.log(res.data);
            });
        };

        //메뉴 전달 to everyone. instore메뉴 watch로 감시=> 버그남.
        const transferMenu = function () {
            console.log("메뉴받아라");
            this.session
                .signal({
                    type: "loadMenu",
                    data: JSON.stringify(this.instoreMenu), //데이터 통신은 string으로만.
                    to: [],
                })
                .then(console.log("메뉴전달 완료."));
        };

        //instoreMenu CRUD
        const addMenu = function (item) {
            //item: {itemNo,itemName,price,  recent:true =>db, soldout:false}
            //중복인지체크
            for (var thing of this.instoreMenu) {
                if (thing.itemName == item.itemName) {
                    alert("중복입니다");
                    return;
                }
            }
            item.soldout = false;
            instoreMenu.value.push(item);
        };
        const deleteMenu = function (idx) {
            //instore메뉴 삭제.
            instoreMenu.value.splice(idx, 1); //this. 가 안될땐 .value 사용해서 고쳐보자.
            session.value //transferMenu()인데 버그나서 이렇게 씁니다.
                .signal({
                    type: "loadMenu",
                    data: JSON.stringify(instoreMenu.value),
                    to: [],
                })
                .then(console.log("메뉴전달 완료."));
        };
        const updateMenuPrice = function ({ idx, price }) {
            //이름은 수정불가에요. db에서 No로 조회하기 때문에.
            instoreMenu.value[idx].price = price;
        };
        const updateMenuSoldout = function (idx) {
            console.log("update실행");
            console.log(idx);
            // console.log(instoreMenu.value[0].soldout)
            instoreMenu.value[idx].soldout = !instoreMenu.value[idx].soldout;
            session.value //transferMenu()인데 버그나서 이렇게 씁니다.
                .signal({
                    type: "loadMenu",
                    data: JSON.stringify(instoreMenu.value),
                    to: [],
                })
                .then(console.log("메뉴전달 완료."));
        };

        /* 덤 */
        const dumOrderNo =ref()
        const innerDrawer = ref(false);
        const dbMenues = ref([]); //이건 메뉴추가시 사용할 아이템들.
        const dumBoard = ref([]);
        const dumArr = ref([])
        function onboardDum(item) {
            this.dumArr.push(item)
            //덤메뉴 클릭시 onboard
        }
        function clearDum() {
            while(dumArr.value.length>0){
                dumArr.value.pop()
            }
            
            //onboard 덤메뉴 clear
        }
        function addDum(orderNo,dumArr) {
            let body = []
            let tmp =  {}
            const countByArray = (arr) => {
            return arr.reduce((prev, curr) => {
                prev[curr] = ++prev[curr] || 1;
                return prev;
            }, {});
            };
            tmp = countByArray(dumArr)
            var keys=Object.keys(tmp)
            for(var key of keys){
                body.push({itemName:key,count:tmp[key]})
            }
            for(var dum of body)           
            pushDum(orderNo,dum,
            (res)=>{
                console.log(res)
            },
            ()=>{})
            ElNotification({
                    title: '덤 추가 완료!',
                    message: `${dumArr[0]} 외 ${body.length-1} 개의 덤 증정! `,
                    type: 'success',
                    })
            clearDum()

            
            //onboard 덤메뉴 add
        }
        function showDum(data) {
            innerDrawer.value = true;
            this.dumOrderNo=data;
            console.log(this.dumOrderNo)
        }

        /* 호출스택관리. */
        const callQueue = ref([]); //{connection,name,id}
        const connectCount = ref(0);

        function broadcastCallCount() {
            this.session
                .signal({
                    type: "callCount",
                    data: this.callQueue.length,
                    to: [],
                })
                .then(console.log("호출카운트 전달 완료.."));
        }
        //다음손님받기
        function nextCall() {
            if(this.callQueue.length==0){ //호출고객없을땐 x
                ElMessage({
                message: `호출중인 손님이 없습니다`,
                type: "message",
            });
            }
            this.connectCustomer(this.callQueue.shift());
            
        }
        //연결시.

        /*상인 알림 */
        const visitsound = ref(new Audio(servicebell));
        const callsound = ref(new Audio(PhoneRinging));
        function visitNotification() {
            this.visitsound.play();
            ElMessage({
                message: `손님왔어요!!!`,
                type: "success",
            });
        }

        //호출알림 start
        function callNotificationPlay() {
           this.callsound.loop=true;
            this.callsound.play();
        }

        //호출알림 stop
        function callNotificationStop() {
            this.callsound.pause();
        }

        return {
            //openvidu
            OV,
            session,
            mainStreamManager,
            publisher,
            subscribers,
            sessionName,
            clientdata,
            joinSession,
            leaveSession,
            updateMainVideoStreamManager,
            getToken,
            createSession,
            createToken,
            connectCustomer,
            storeNo,
            userId,
            storeIntro,
            storeSubject,
            isConnected,
            customers,
            //bills
            drawer_bills,
            bills,
            loadBills,
            clickBills,
            newbillcount,
            //chatting
            sendMessage,

            //menues&dums
            drawer_menues,
            innerDrawer,
            dbMenues,
            clickMenues,
            transferMenu,
            instoreMenu,
            addMenu,
            deleteMenu,
            updateMenuPrice,
            updateMenuSoldout,
            showDum,
            dumBoard,
            dumOrderNo,
            dumArr,
            onboardDum,
            clearDum,
            addDum,
            //alarm
            visitNotification,
            visitsound,
            callNotificationPlay,
            callNotificationStop,
            callsound,
            //호출
            callQueue,
            connectCount,
            broadcastCallCount,
            nextCall,
        };
    }, // setup 종료.
};
</script>

<style scoped>
#customers {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}

.customer {
    border: 2px;
    border-radius: 15px;
    border-style: groove;
    width: 160px;
    height: 120px;
    margin: 10px;
    margin-top: 0;
}
.on {
    border-color: red;
}
.main {
    display: flex;
    flex-wrap: wrap;
}
.root_div {
    display: flex;
    flex-direction: column;
    margin: auto;
}

.sellervideo {
    width: 468px;
    height: 352px;
    border: 2px;
    border-style: groove;
    margin-right: 5px;
}

.el-card__body {
    display: flex;
}
</style>
