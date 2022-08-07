import { sidoList, gugunList, marketList, marketByName } from "@/api/market.js";

const marketStore = {
    namespaced: true,
    state: {
        sidos: [{ value: "없음", text: "선택하세요" }],
        guguns: [{ value: "없음", text: "선택하세요" }],
        markets: [
            { name: "시장장", address: "서울시 어딘가" },
            { name: "우리동네최고시장", address: "대한민국 저기어딘가" },
        ],
    },

    mutations: {
        SET_SIDO_LIST: (state, sidos) => {
            sidos.forEach((sido) => {
                state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
            });
        },
        SET_GUGUN_LISt: (state, guguns) => {
            guguns.forEach((gugun) => {
                state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
            });
        },
        SET_MARKET_LIST: (state, market) => {
            state.market = market;
        },
        CLEAR_SIDO_LIST: (state) => {
            state.sido = [{ value: null, text: "선택하세요" }];
        },
        CLEAR_GUGUN_LIST: (state) => {
            state.guguns = [{ value: null, text: "선택하세요" }];
        },
    },

    actions: {
        getSido: ({ commit }) => {
            sidoList(
                ({ data }) => {
                    commit("SET_SIDO_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getGugun: ({ commit }, sidoCode) => {
            const params = {
                sido: sidoCode,
            };
            gugunList(
                params,
                ({ data }) => {
                    commit("SET_GUGUN_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getMarketList: ({ commit }, sidoName, gugunName) => {
            const params = {
                sido_name: sidoName,
                gugun_name: gugunName,
            };
            marketList(
                params,
                (response) => {
                    commit("SET_MARKET_LIST", response.data.response.body.items.item);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getMarketListByName: ({ commit }, marketName) => {
            const params = {
                marketName: marketName,
            };
            marketByName(
                params,
                (response) => {
                    commit("SET_MARKET_LIST", response.data.response.body.items.item);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
};

export default marketStore;
