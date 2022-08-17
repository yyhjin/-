<template>
  <div style="margin: auto">
    <el-card shadow="never">
      <template #header>
        <div class="about" style="margin-top: -10px; maring-bottom: -10px">
          <h3 style="margin-left: 10px; display: inline-block">
            {{ this.storeName }}
          </h3>
          <div
            style="
              width: 25px;
              weight: 25px;
              display: inline-block;
              margin-left: 10px;
            "
            @click="cl_JJIM()"
            v-if="this.jjim == false"
          >
            <svg
              viewBox="0 0 1024 1024"
              xmlns="http://www.w3.org/2000/svg"
              data-v-029747aa=""
            >
              <path
                fill="currentColor"
                d="m512 747.84 228.16 119.936a6.4 6.4 0 0 0 9.28-6.72l-43.52-254.08 184.512-179.904a6.4 6.4 0 0 0-3.52-10.88l-255.104-37.12L517.76 147.904a6.4 6.4 0 0 0-11.52 0L392.192 379.072l-255.104 37.12a6.4 6.4 0 0 0-3.52 10.88L318.08 606.976l-43.584 254.08a6.4 6.4 0 0 0 9.28 6.72L512 747.84zM313.6 924.48a70.4 70.4 0 0 1-102.144-74.24l37.888-220.928L88.96 472.96A70.4 70.4 0 0 1 128 352.896l221.76-32.256 99.2-200.96a70.4 70.4 0 0 1 126.208 0l99.2 200.96 221.824 32.256a70.4 70.4 0 0 1 39.04 120.064L774.72 629.376l37.888 220.928a70.4 70.4 0 0 1-102.144 74.24L512 820.096l-198.4 104.32z"
              ></path>
            </svg>
          </div>
          <div
            style="
              width: 25px;
              weight: 25px;
              display: inline-block;
              margin-left: 10px;
            "
            @click="cl_JJIM()"
            v-else
          >
            <svg
              viewBox="0 0 1024 1024"
              xmlns="http://www.w3.org/2000/svg"
              data-v-029747aa=""
            >
              <path
                fill="currentColor"
                d="M283.84 867.84 512 747.776l228.16 119.936a6.4 6.4 0 0 0 9.28-6.72l-43.52-254.08 184.512-179.904a6.4 6.4 0 0 0-3.52-10.88l-255.104-37.12L517.76 147.904a6.4 6.4 0 0 0-11.52 0L392.192 379.072l-255.104 37.12a6.4 6.4 0 0 0-3.52 10.88L318.08 606.976l-43.584 254.08a6.4 6.4 0 0 0 9.28 6.72z"
              ></path>
            </svg>
          </div>
          <el-button
            color="red"
            @click="btn_out()"
            style="float: right; margin-top: 20px; color: white"
            >나가기</el-button
          >
        </div>
      </template>
      <div style="height: 250px">
        <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
        />
      </div>
    </el-card>
    <el-card shadow="never" style="margin-top: 10px; height: 100px">
      <div style="text-align: right">
        <h4
          style="display: inline-block; text-align: left; color: #e07c49"
          v-if="this.hochul == true"
        >
          호출중... 대기 : {{ this.number }}번
        </h4>

        <el-button
          color="#e07c49"
          @click="cl_hochul()"
          v-if="this.hochul == false"
          style="margin-left: 20px; margin-top: 18px; color: white"
          round
          >문의하기</el-button
        >
        <el-button
          color="#42413e"
          @click="cl_cancleho()"
          v-else
          style="margin-left: 20px"
          round
          >호출취소
        </el-button>
      </div>
    </el-card>
    <el-card shadow="never" style="margin-top: 10px">
      <div class="scrollbar-flex-content">
        <div
          v-for="(menu, idx) in menus"
          :key="idx"
          class="scrollbar-demo-item"
          @click="cl_item(menu)"
        >
          <store-menu :menu="menu" />
        </div>
      </div>
    </el-card>

    <el-tabs
      type="border-card"
      tab-position="top"
      class="demo-tabs"
      style="margin-top: 10px"
    >
      <el-tab-pane label="장바구니">
        <div>
          <el-table
            :data="orderItems"
            style="width: 90%; margin: auto"
            max-height="250"
          >
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
                  style="font-weight: bold"
                >
                  삭제
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="text-align: right; margin-right: 20px; margin-top: -10px">
            <h4>합계 : {{ money }}원</h4>
          </div>
        </div>
        <div>
          <h4 style="margin-left: 10px">추가하기</h4>
          <div style="text-align: center">
            <el-input
              v-model="this.selected.itemName"
              placeholder="품목"
              style="width: 140px"
            >
              <template #prepend>품목</template>
            </el-input>
            <el-input
              v-model="this.selected.count"
              placeholder="수량"
              style="width: 140px"
            >
              <template #prepend>수량</template>
            </el-input>
            <el-button
              :icon="Plus"
              circle
              @click="btn_add()"
              style="
                margin: auto;
                margin-top: 10px;
                background-color: #42413e;
                color: white;
                display: block;
              "
            />
          </div>
        </div>
        <el-button
          round
          color="#e07c49"
          @click="btn_preorder()"
          style="float: right; margin-top: 15px; color: white"
          >주문하기</el-button
        >
      </el-tab-pane>
      <el-tab-pane label="채팅">
        <room-chat
          ref="chat"
          @message="sendMessage"
          :subscribers="subscribers"
        ></room-chat
      ></el-tab-pane>
    </el-tabs>

    <!-- <el-card class="box-card" shadow="never" style="margin-top: 10px">
      <div class="div_content">
        <div>
          <div v-if="this.content == true">
            <div style="margin-top: -30px">
              <h3 style="margin-left: 10px; display: inline-block">장바구니</h3>
              <el-button
                round
                color="#e07c49"
                @click="btn_preorder()"
                style="float: right; margin-top: 15px; color: white"
                >주문하기</el-button
              >
            </div>
            <div>
              <el-table
                :data="orderItems"
                style="width: 90%; margin: auto"
                max-height="250"
              >
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
                      style="font-weight: bold"
                    >
                      삭제
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <div
                style="text-align: right; margin-right: 20px; margin-top: -10px"
              >
                <h4>합계 : {{ money }}원</h4>
              </div>
            </div>
            <div>
              <h4 style="margin-left: 10px">추가하기</h4>
              <div style="text-align: center">
                <el-input
                  v-model="this.selected.itemName"
                  placeholder="품목"
                  style="width: 140px"
                >
                  <template #prepend>품목</template>
                </el-input>
                <el-input
                  v-model="this.selected.count"
                  placeholder="수량"
                  style="width: 140px"
                >
                  <template #prepend>수량</template>
                </el-input>
              </div>
              <div style="text-align: right">
                <el-button
                  :icon="Plus"
                  circle
                  @click="btn_add()"
                  style="
                    margin: 10px 10px;
                    background-color: #42413e;
                    color: white;
                  "
                />
              </div>
            </div>
          </div>

          <div v-else>
            <room-chat
              ref="chat"
              @message="sendMessage"
              :subscribers="subscribers"
            ></room-chat>
          </div>
        </div>
      </div>
    </el-card>
    <div>
      <el-button plain @click="btn_jang()" style="margin-left: -10px"
        >장바구니</el-button
      >
      <el-button plain @click="btn_chat()" style="margin-left: 0"
        >채팅</el-button
      >
    </div> -->
    <el-dialog
      v-model="centerDialogVisible"
      title="주문 확인"
      width="90%"
      center
    >
      <el-table
        :data="orderItems"
        style="width: 90%; margin: auto"
        max-height="250"
      >
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
      <div style="text-align: right; margin-right: 20px; margin-top: -10px">
        <h4>합계 : {{ money }}원</h4>
      </div>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="btn_order" color="#42413e"
            >주문하기</el-button
          >
          <el-button
            @click="centerDialogVisible = false"
            class="btn_cancle"
            style="background-color: white"
            >취소</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import StoreMenu from "@/components/Room/StoreMenu.vue";
import UserVideo from "@/components/Openvidu/UserVideo";
import RoomChat from "@/components/Openvidu/RoomChat.vue";
import { Plus, Star, StarFilled } from "@element-plus/icons-vue";
import { makeCall, deleteCall, getCall } from "@/api/call";
import { setJJim } from "@/api/customer";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import axios from "axios";
//import {getItem} from "@/api/market";
import { getOrder } from "@/api/market";
import { ref, computed } from "vue";
import { ElMessage } from "element-plus";
import { OpenVidu } from "openvidu-browser";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + "i7a602.p.ssafy.io" + ":7602";
const OPENVIDU_SERVER_SECRET = "jangbo602";

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
    UserVideo,
    RoomChat,
  },

  mounted() {
    //this.joinSession();
  },
  created() {
    //this.myUserName = this.$route.params.userName;
    //this.myUserNo = this.$route.params.userNo;
    this.jjim = this.$route.params.storeJJim;
    this.mySessionId = this.$route.params.storeNo;
    this.storeName = this.$route.params.storeName;
    //this.sellerNo = this.$route.path.no (판매자 번호 받기)
    //this.storeName = this.$route.params.storeName (가게 이름 받ㄱ기)

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
  data() {
    return {
      OV: undefined,
      session: undefined,
      publisher: undefined,
      subscribers: [],

      //myUserNo: "",
      //오픈비두 필수입력
      mySessionId: "1",
      myUserName: "",
    };
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    //소비자 정보
    const customerNo = computed(() => store.state.userInfo.userNo);
    const myUserNo = computed(() => store.state.userInfo.userNo);
    const userId = computed(() => store.state.userInfo.userId);

    const storeName = ref();
    const number = ref();
    //찜 버튼용
    const jjim = ref(false);

    const menus = ref([
      { itemName: "고기", itemNo: "1", price: "1000", recent: "true" },
      { itemName: "사과", itemNo: "2", price: "3500", recent: "true" },
    ]);
    const orderItems = ref([
      { itemName: "꽁치", itemNo: "3", count: "1", price: "5000" },
      { itemName: "삼겹살", itemNo: "4", count: "1", price: "10000" },
    ]);

    const selected = ref({
      itemName: "",
      itemNo: "",
      count: "",
      price: "",
    });

    const params = ref([
      { itemNo: "3", count: "1" },
      { itemNo: "4", count: "1" },
    ]);
    const hochul = ref(false);
    const content = ref(true);

    const deleteRow = (index) => {
      orderItems.value.splice(index, 1);
      params.value.splice(index, 1);
    };

    const open = (message) => {
      ElMessage.error(message);
    };

    const clientdata = ref({
      //myUserName
      type: 1, //0,1
      userName: userId,
      userNo: myUserNo,
    });
    const centerDialogVisible = ref(false);

    return {
      centerDialogVisible,
      clientdata,
      router,
      Plus,
      Star,
      StarFilled,
      jjim,
      myUserNo,
      number,
      userId,
      params,
      customerNo,
      storeName,
      menus,
      selected,
      orderItems,
      hochul,
      content,
      deleteRow,
      open,
    };
  },
  computed: {
    money() {
      var sum = 0;
      for (var i = 0; i < this.orderItems.length; i++) {
        sum += this.orderItems[i].count * this.orderItems[i].price;
      }

      return sum;
    },
  },

  methods: {
    //찜등록
    cl_JJIM() {
      setJJim(
        this.myUserNo,
        { storeNo: this.mySessionId },
        (response) => {
          if (response.data.check == true) {
            this.jjim = true;
          } else {
            this.jjim = false;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    //뒤로가기 만들기
    btn_out() {
      this.cl_cancleho();
      this.leaveSession();
      this.$router.push({ name: "home" });
    },
    btn_preorder() {
      this.centerDialogVisible = true;
    },
    //주문하기
    btn_order() {
      getOrder(
        this.customerNo,
        //sessionId가 상점번호
        this.mySessionId,
        this.params,
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    cl_item(menu) {
      //메뉴 선택
      this.selected = menu;
    },
    //전체 호출 내역반환
    totalhochul() {
      getCall(
        this.mySessionId,
        (response) => {
          console.log(response.data);
          for (var i = 0; i < response.data.length; i++) {
            if (response.data[i].customerId == this.userId) {
              this.number = response.data[i].orderNo;
              console.log(this.number);
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    //호출하기
    cl_hochul() {
      console.log("호출");
      console.log(this.userId + " " + this.mySessionId);
      makeCall(
        this.userId,
        this.mySessionId,
        (response) => {
          console.log(response);
          this.number = response.data;
          this.hochul = true;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    //호출 취소
    cl_cancleho() {
      console.log("호출취소");
      console.log(this.userId + " " + this.mySessionId);
      deleteCall(
        this.userId,
        this.mySessionId,
        (response) => {
          console.log(response);
          this.hochul = false;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    //장바구니보기
    btn_jang() {
      this.content = true;
      console.log("jang");
    },
    //채팅보기
    btn_chat() {
      this.content = false;
      console.log("chat");
    },
    //장바구니에 추가하기
    btn_add() {
      console.log(this.selected.itemName);
      if (this.selected.itemName == "") {
        this.open("품목을 골라주세요");
      } else if (this.selected.count == "") {
        this.open("수량을 입력해주세요");
      } else {
        this.orderItems.push({
          itemNo: this.selected.itemNo,
          count: this.selected.count,
          itemName: this.selected.itemName,
          price: this.selected.price,
        });
        this.params.push({
          itemNo: this.selected.itemNo,
          count: this.selected.count,
        });
        this.selected = {};
      }
    },
    // 오픈비두
    isPublisher(userName) {
      return userName.includes("pu");
    },

    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        this.count++;
        const subscriber = this.session.subscribe(stream);
        console.log(JSON.parse(stream.connection.data));
        //판매자만 sub에 넣기
        if (JSON.parse(stream.connection.data).clientData.type == 0) {
          this.subscribers.push(subscriber);
        }
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        // this.$store.commit('decrease')
        this.count--;
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // public 채팅 signal 받기
      this.session.on("signal:public-chat", (event) => {
        console.log(JSON.parse(event.data).sender);
        this.$refs.chat.addMessage(
          event.data,
          JSON.parse(event.data).sender === this.userId,
          false
        );
      });

      // private 채팅 signal 받기
      this.session.on("signal:private-chat", (event) => {
        this.$refs.chat.addMessage(event.data, false, true);
      });

      //호출삭제되면 시그널받아서 순번 다시 조회하기
      this.session.on("signal:delete-hochul", (event) => {
        this.console.log(event);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.clientdata })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: false, // Whether you want to start publishing with your video enabled or not
              //resolution: "640x480", // The resolution of your video
              resolution: "120x80",
              frameRate: 30, // The frame rate of your video
              insertMode: "PREPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });
            this.publisher = publisher;

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },
    // 판매자에서 호출삭제하면 받을 시그널
    reHocul() {
      this.session
        .signal({
          data: {},
          to: [],
          type: "delete-hochul",
        })
        .then(() => {
          console.log("호출 삭제");
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getHocul() {
      this.session
        .signal({
          data: {},
          to: [],
          type: "add-hochul",
        })
        .then(() => {
          console.log("호출 완료");
        })
        .catch((error) => {
          console.log(error);
        });
    },

    sendMessage({ content, to }) {
      let now = new Date();
      let current = now.toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit",
        hour12: false, // true인 경우 오후 10:25와 같이 나타냄.
      });

      let messageData = {
        content: content,
        sender: this.userId,
        time: current,
      };

      // 전체 메시지
      if (to === "all") {
        this.session
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
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
    },
    /*
    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },
*/
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

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
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
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
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
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
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
    },
  },
};
</script>
<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  margin-left: -10px;
  width: 320px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.div_content {
  margin-top: 20px;
}

.scrollbar-flex-content {
  display: flex;
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
  background: white;
  border: 1px solid;
  color: #42413e;
}

.el-button--warning.is-link,
.el-button--warning.is-plain,
.el-button--warning.is-text {
  --el-button-border-color: #e07c49 !important;
  --el-button-bg-color: #e07c49 !important;
}

.el-button.is-plain {
  --el-button-hover-bg-color: #42413e;
  --el-button-hover-text-color: white;
  --el-button-hover-border-color: white;
}
</style>
