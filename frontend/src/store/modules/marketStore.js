import { sidoList, gugunList, marketList, marketByName } from "@/api/market.js";

const marketStore = {
    namespaced: true,
    state: {
        sidos: [{ value: "없음", text: "선택하세요" }],
        guguns: [{ value: "없음", text: "선택하세요" }],
        markets: [{ name: "시장이 없습니다", address: "", no: "0" }],
    },

    mutations: {
        SET_SIDO_LIST: (state, sidos) => {
            sidos.forEach((sido) => {
                state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
            });
        },
        SET_GUGUN_LIST: (state, guguns) => {
            guguns.forEach((gugun) => {
                state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
            });
        },
        SET_MARKET_LIST: (state, markets) => {
            markets.forEach((market) => {
                state.markets.push({ name: market.marketName, address: market.marketAddr, no: market.marketNo });
            });
        },
        SET_MARKET_INIT: (state) => {
            state.markets = [{ name: "시장이 없습니다", address: "", no: "0" }];
        },
        CLEAR_SIDO_LIST: (state) => {
            state.sido = [{ value: "없음", text: "선택하세요" }];
        },
        CLEAR_GUGUN_LIST: (state) => {
            state.guguns = [{ value: "없음", text: "선택하세요" }];
        },
        CLEAR_MARKET_LIST: (state) => {
            state.markets = [];
        },
    },

    actions: {
        getSido: ({ commit }) => {
            commit("CLEAR_SIDO_LIST");
            commit("CLEAR_GUGUN_LIST");
            sidoList(
                (data) => {
                    commit("SET_SIDO_LIST", data.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getGugun: ({ commit }, param) => {
            commit("CLEAR_GUGUN_LIST");
            //console.log(param.value);
            const params = {
                sidocode: param.value,
            };
            gugunList(
                params,
                (response) => {
                    //console.log(response);
                    commit("SET_GUGUN_LIST", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getMarketList: ({ commit }, param) => {
            commit("CLEAR_MARKET_LIST");
            const params = {
                sidogugun: param,
            };
            marketList(
                params,
                (response) => {
                    console.log(response);
                    commit("SET_MARKET_LIST", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },

        getMarketListByName: ({ commit }, param) => {
            commit("CLEAR_MARKET_LIST");
            const params = {
                marketname: param,
            };
            console.log(params);
            marketByName(
                params,
                (response) => {
                    console.log(response);
                    commit("SET_MARKET_LIST", response.data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
};

export default marketStore;
