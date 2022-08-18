import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import VueCookies from "vue3-cookies";
// import * as ElementPlusIconsVue from "@element-plus/icons-vue";

createApp(App).use(store).use(router).use(ElementPlus).use(VueCookies).mount("#app");

// for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
//     App.component(key, component);
// }


