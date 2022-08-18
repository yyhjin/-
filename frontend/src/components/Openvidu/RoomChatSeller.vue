<template>
    <div style="margin: auto ; height:400px;">
        <!-- chat-bar -->
        <el-card>
            <div ref="content" style="margin-top: 5px">
            <el-scrollbar height="280px" style="align-items: center">
                <!-- 채팅 내용 -->
                <ul style="margin-left: -40px" id="chat-bar">
                    <li v-for="(chat, idx) in state.chats" :key="idx">
                        <!-- 내가 보낸 채팅인 경우 -->
                        <div v-if="chat.isMyMessage" style="text-align: right" class="div_chat">
                            <div>
                                 <div style="margin-right: 10px; margin-top: 10px">
                                    <span style="margin-right: 10px">Me</span>
                                    <span>{{ chat.time }}</span>
                                </div>
                                <div style="margin-right: 10px; margin-top: -10px">
                                    <p>
                                        {{ chat.content }}
                                    </p>
                                </div>

                            </div>
                        </div>
                        <!-- 다른 사람이 보낸 채팅인 경우 -->
                        <div v-else class="div_chat">
                            <div>
                                 <div style="margin-left: 10px; margin-top: 10px">
                                    <span style="margin-right: 10px">{{ chat.userId }}</span>
                                    <span>{{ chat.time }}</span>
                                </div>
                                <div style="margin-left: 10px">
                                    <p>
                                        {{ chat.content }}
                                    </p>
                                </div>

                            </div>
                               
                    
                        </div>
                    </li>
                </ul>
            </el-scrollbar>
            <!-- 채팅내용. -->

            <div style="border: 1px solid; border-radius: 5px">
                <!-- 메시지 보낼 유저 선택창 -->
                <div style="display: inline">
                    <el-select v-model="state.selectedUser" class="m-2" style="width: 120px; margin-bottom: 30px; margin-left: 10px">
                        <el-option value="all">all</el-option>
                        <el-option v-for="(sub, idx) in state.subscribers" :value="sub.stream.connection" :key="idx" :label="JSON.parse(sub.stream.connection.data).clientData.userName">
                            {{ JSON.parse(sub.stream.connection.data).clientData.userName }}</el-option
                        >
                        <!--     <el-option v-for="(sub, idx) in state.subscribers" :value="sub.stream.connection" :key="idx" label="사장님"> 사장님</el-option>-->
                    </el-select>
                </div>

                <!-- 메시지 작성 -->
                <div style="display: inline">
                    <el-input v-model="state.message" :rows="2" type="textarea" @keydown.enter="sendMessage" style="width: 210px; margin: 10px 10px" />
                    <svg
                        @click="sendMessage()"
                        class="h-6 w-6 send-icon cursor-pointer"
                        version="1.1"
                        xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink"
                        x="0px"
                        y="0px"
                        viewBox="0 0 491.022 491.022"
                        style="enable-background: new 0 0 491.022 491.022; margin-right: 10px; margin-left: 10px; margin-top: 10px; width: 20px"
                        xml:space="preserve"
                    >
                        <g>
                            <g>
                                <path
                                    d="M490.916,13.991c-0.213-1.173-0.64-2.347-1.28-3.307c-0.107-0.213-0.213-0.533-0.32-0.747
									c-0.107-0.213-0.32-0.32-0.533-0.533c-0.427-0.533-0.96-1.067-1.493-1.493c-0.427-0.32-0.853-0.64-1.28-0.96
									c-0.213-0.107-0.32-0.32-0.533-0.427c-0.32-0.107-0.747-0.32-1.173-0.427c-0.533-0.213-1.067-0.427-1.6-0.533
									c-0.64-0.107-1.28-0.213-1.92-0.213c-0.533,0-1.067,0-1.6,0c-0.747,0.107-1.493,0.32-2.133,0.533
									c-0.32,0.107-0.747,0.107-1.067,0.213L6.436,209.085c-5.44,2.347-7.893,8.64-5.547,14.08c1.067,2.347,2.88,4.373,5.227,5.44
									l175.36,82.453v163.947c0,5.867,4.8,10.667,10.667,10.667c3.733,0,7.147-1.92,9.067-5.12l74.133-120.533l114.56,60.373
									c5.227,2.773,11.627,0.747,14.4-4.48c0.427-0.853,0.747-1.813,0.96-2.667l85.547-394.987c0-0.213,0-0.427,0-0.64
									c0.107-0.64,0.107-1.173,0.213-1.707C491.022,15.271,491.022,14.631,490.916,13.991z M190.009,291.324L36.836,219.218
									L433.209,48.124L190.009,291.324z M202.809,437.138V321.831l53.653,28.267L202.809,437.138z M387.449,394.898l-100.8-53.013
									l-18.133-11.2l-0.747,1.28l-57.707-30.4L462.116,49.298L387.449,394.898z"
                                />
                            </g>
                        </g>
                    </svg>
                </div>
            </div>
        </div>

        </el-card>
        
    </div>
</template>

<style scoped>
ul {
    list-style: none;
}
</style>

<script>
import { reactive } from "vue";

export default {
    props: {
        subscribers: Object,
    },

    setup(props, { emit }) {
        const state = reactive({
            selectedUser: "all",
            message: "",
            subscribers: props.subscribers,
            chats: [],
        });

        const sendMessage = () => {
            let strippeddMessage = state.message.trim();

            if (strippeddMessage === "") return;

            console.log("보낼 메시지 : " + strippeddMessage);

            emit("message", {
                content: strippeddMessage,
                to: state.selectedUser,
            });

            event.preventDefault(); // enter키 누를 때 줄바꿈 방지
            state.message = ""; // 메시지 창 초기화

            console.log(state.subscribers);
        };

        const addMessage = async (messageData, isMyMessage, isPrivate) => {
            let message = JSON.parse(messageData);

            // 내가 보낸 메시지인 경우
            if (isMyMessage) {
                message.sender += " (You)";
            }

            // 개인 메시지인 경우
            if (isPrivate) {
                message.content += " (private)";
            }

            let chatBar = document.querySelector("#chat-bar");
            let isScrollBottom = chatBar.scrollHeight - chatBar.scrollTop <= chatBar.clientHeight + 2;

            // await 키워드 => 새로운 채팅 메시지 추가 완료 후 스크롤바가 아래로 이동되도록 함.
            await state.chats.push({
                userId: message.sender,
                time: message.time,
                content: message.content,
                isMyMessage: isMyMessage,
            });

            // 채팅 스크롤이 끝까지 내려가 있는 경우 => 스크롤바 맨 아래로 이동시키기
            if (isScrollBottom) {
                chatBar.scrollTo({ top: chatBar.scrollHeight, behavior: "smooth" });
            }

            console.log("메시지 수신 완료");
        };

        return { state, sendMessage, addMessage };
    },
};
</script>
