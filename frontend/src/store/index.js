import { createStore } from "vuex";

import state from "./state";
import * as getters from "./getters";
import * as mutations from "./mutations";
import * as actions from "./actions";
import marketStore from "@/store/modules/marketStore.js";
import storeInMarket from "@/store/modules/storeInMarket.js";

const root = {
    namespaced: true,
    state,
    getters,
    mutations,
    actions,
};

export default createStore({
    modules: { root, marketStore, storeInMarket },
    state: {
        //dummy
        userinfo: {
            id: "jaeseungzzang@naver.com",
            password: "",
            password_double: "",
            name: "한재승",
            phone_number: "0107777777",
            address: "역삼역",
            postcode: "",
            detailAddress: "1402호",
            extraAddress: "(멀티캠퍼스 빌딩)",
            birthday: "20220728",
            gender: "남",
            userType: "구매자",
        },
    },
    getters: {},
    mutations: {},
    actions: {},
});
