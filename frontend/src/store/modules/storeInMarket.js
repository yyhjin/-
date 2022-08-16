import { StoreInMarketList } from "@/api/store.js";

const storeInMarket = {
    namespaced: true,
    state: {
        stores: [],
        market_no: "",
        market_name: "",
    },

    mutations: {
        SET_STORE_LIST: (state, stores) => {
            stores.forEach((store) => {
                state.stores.push({
                    addr: store.storeAddr,
                    item: store.storeCategory,
                    idx: store.storeIdx,
                    image: store.storeImg,
                    intro: store.storeIntro,
                    name: store.storeName,
                    no: store.storeNo,
                    phone: store.storePhone,
                    subject: store.storeSubject,
                });
            });
        },
        SET_MARKETNO: (state, no) => {
            state.market_no = no;
        },
        SET_MARKETNAME: (state, name) => {
            state.market_name = name;
        },
        CLEAR_STORE_LIST: (state) => {
            state.stores = [];
        },
        CLEAR_MARKETNO: (state) => {
            state.market_no = "";
        },
        CLEAR_MARKETNAME: (state) => {
            state.market_name = "";
        },
    },

    actions: {
        getStore: ({ commit }, params) => {
            commit("CLEAR_STORE_LIST");
            console.log(params.value);
            StoreInMarketList(
                params.value,
                (response) => {
                    console.log(response.data);
                    commit("SET_STORE_LIST", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        logout: ({ commit }) => {
            commit("CLEAR_STORE_LIST");
            commit("CLEAR_MARKETNO");
            commit("CLEAR_MARKETNAME");
        },
    },
};

export default storeInMarket;
