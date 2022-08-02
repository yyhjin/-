<template>
    <div class="div_big">
        <div class="div_market" v-for="item in marketList" v-bind:key="item" @click="cl_div(item)">
            <img class="mk_img" src="@/assets/logo.png" alt="상점사진" />
            <h3 class="mk_name">{{ item.name }}</h3>
            <h4 class="mk_like">{{ item.like }}</h4>
            <h4 class="mk_items">{{ item.items }}</h4>
        </div>
    </div>
</template>

<script>
export default {
    name: "SearchType",

    data() {
        return {
            items: [{ name: "OO수산" }, { name: "OO청과물" }],
            item: "",
            marketList: [
                { name: "OO수산", items: "고등어,꽁치,갈치", like: 222 },
                { name: "OO청과물", items: "사과,배,토마토", like: 142 },
                { name: "OO청과물", items: "사과,배,토마토", like: 142 },
            ],
        };
    },

    mounted() {},

    methods: {
        cl_div(item) {
            this.item = item;
            console.log(this.item);
        },
    },
};
</script>

<style>
.div_big {
    margin: auto;
}

.mk_img {
    float: left;
}

.mk_name {
    float: left;
    margin-left: 10px;
    margin-top: 10px;
}

.mk_like {
    text-align: right;
    margin-right: 20px;
    margin-top: 10px;
}

.mk_items {
    margin-top: 10px;
}
</style>
