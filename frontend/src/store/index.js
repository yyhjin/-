import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

import state from "./state";
import * as getters from "./getters";
import * as mutations from "./mutations";
import * as actions from "./actions";
import marketStore from "@/store/modules/marketStore.js";
import storeInMarket from "@/store/modules/storeInMarket.js";
import userInfo from "@/store/modules/userInfo";
import orderStore from "@/store/modules/orderStore";

const root = {
    namespaced: true,
    state,
    getters,
    mutations,
    actions,
};

export default createStore({
    modules: { root, marketStore, storeInMarket, userInfo, orderStore },
    state: {
        //dummy
        userinfo: {
            // id: "jaeseungzzang@naver.com",
            // password: "",
            // password_double: "",
            // userno: "2",
            // nick: "재승2",
            // name: "한재승",
            // phone_number: "0107777777",
            // address: "역삼역",
            // detailAddress: "1402호",
            // extraAddress: "(멀티캠퍼스 빌딩)",
            // birthday: "20220728",
            // gender: "남",
            // userType: "구매자",
        },
    },
    getters: {},
    mutations: {},
    actions: {},
    plugins: [
        createPersistedState({
            paths: ["storeInMarket", "userInfo"],
            storage: window.sessionStorage,
        }),
    ],
});
