import { StoreInMarketList } from "@/api/store.js";

const storeInMarket = {
    namespaced: true,
    state: {
        stores: [],
        market_no: "",
    },

    mutations: {
        SET_STORE_LIST: (state, stores) => {
            stores.forEach((store) => {
                state.stores.push({ no: store.storeNo, name: store.storeName, item: store.storeCategory, image: store.storeImg });
            });
        },
        SET_MARKETNO: (state, no) => {
            state.market_no = no;
        },
        CLEAR_STORE_LIST: (state) => {
            state.stores = [];
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
    },
};

export default storeInMarket;
