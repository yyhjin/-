<template>
    <div v-if="streamManager" class="video">
        <ov-video :stream-manager="streamManager" isSeller="0" />
        <div>
            <!-- <p>{{ clientData }}</p> -->
        </div>
    </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
    name: "UserVideo",
    components: {
        OvVideo,
    },
    props: {
        streamManager: Object,
        isSeller:String,
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
<style></style>
