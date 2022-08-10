import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import VueCookies from "vue-cookies";

createApp(App).use(store).use(router).use(ElementPlus).use(VueCookies).mount("#app");

window.Kakao.init("7f4fe16346b6216e38bc88071db9e21b");
