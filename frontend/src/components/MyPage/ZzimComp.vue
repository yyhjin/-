<template>
    <div>
        <el-card>
            <el-row >
                <el-col :span="6">
                    <div class="zzimstore_pic">
                        <el-avatar shape="square" :size="50" />
                    </div>
                </el-col>
                <el-col :span="18">
                    <div class="zzimstore_description">
                        <div>
                            {{ zzimstore.name }}
                        </div>
                        <div class="zzimstore_hashtags">
                            <!-- 스토어 메뉴 or 해쉬태그 들어갈 부분. -->
                        </div>  
                    </div>
                </el-col>
            </el-row>

        </el-card>
       
    </div>
</template>
<script>
export default {
    props:[
        'zzimstore'
    ]
    
    
}
</script>
<style></style>
