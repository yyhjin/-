<template>
	<video v-if="isSeller=='0'" class="seller-stream" autoplay/>
	<video v-else class="customer-stream" autoplay/>
</template>

<script>
export default {
	name: 'OvVideo',
	props: {
		streamManager: Object,
		isSeller: String
	},

	mounted () {
		this.streamManager.addVideoElement(this.$el);
	},
	methods:{
		connectCustomer(sub){
		console.log(this.streamManager.videoActive)
		sub.stream.videoActive=false
		}
	
}}
</script>
<style scoped>
.seller-stream{
	width:100%
}
.customer-stream{
	/* 비디오 가려놓은 상태. */
	display:none;  

}
</style>