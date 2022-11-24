<template>
        <div v-if="streamManager"
         :class="`customer ${customerData.isConnected? 'on':'off'} `">
            <ov-video :stream-manager="streamManager" isSeller="1" />
            <div>
               
                <div style="margin:10px; margin-bottom:0px; margin-left:5px;display:flex"> <img style="width:50px ;margin:5px" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
                {{customerData.customerName}}
                </div>
                <div style="margin:10px;">
                      <el-tag
                    v-if="customerData.isConnected"
                    type="danger"
                    effect="plain"
                    >
                    연결중
                </el-tag>
                
                 <el-tag
                    v-if="customerData.isCalling"
                    type="danger"
                    effect="plain"
                    >
                    호출중
                </el-tag>
                </div>
            </div>
        </div>
</template>

<script>
import OvVideo from "./OvVideo";
export default {
    name: "CustomerComp",
    components: {
        OvVideo,
    },
    data(){
        return{
            isSeller:false
        }
    },
     props: {
        streamManager: Object,
        customerData: Object
    },
    computed: {
        clientData() {
            const { clientData } = this.getConnectionData();
            return clientData;
        },
    },

    methods: {
        getConnectionData() {
            const { connection } = this.streamManager.stream;
            return JSON.parse(connection.data);
        },
    },

};
</script>

<style>

</style>