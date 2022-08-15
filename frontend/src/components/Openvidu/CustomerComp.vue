<template>
        <div v-if="streamManager"
         :class="`customer ${customerData.isConnected? 'on':'off'} `">
            <ov-video :stream-manager="streamManager" isSeller="false" />
            <div>
                {{customerData.customerName}}
                <div v-if="customerData.isConnected">음성 연결됨.</div>
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