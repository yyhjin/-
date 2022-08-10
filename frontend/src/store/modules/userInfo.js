const userInfo = {
    namespaced: true,
    state: {
        userType: "0",
        userNo: "0",
        tokken: "3",
    },
    getters: {
        isAuthenticated: function (state) {
            return state.userNo;
        },
    },
    mutations: {
        SET_USERTYPE: (state, userType) => {
            state.userType = userType;
        },
        SET_USERNO: (state, userNo) => {
            state.userNo = userNo;
        },
        SET_USERTOKKEN: (state, tokken) => {
            state.tokken = tokken;
        },
        CLEAR_USERTYPE: (state) => {
            state.userType = "";
        },
        CLEAR_USERNO: (state) => {
            state.userNo = "";
        },
        CLEAR_TOKKEN: (state) => {
            state.tokken = "";
        },
    },
    actions: {
        logout: ({ commit }) => {
            commit("CLEAR_USERTYPE");
            commit("CLEAR_USERNO");
            commit("CLEAR_TOKKEN");
        },
    },
};

export default userInfo;
