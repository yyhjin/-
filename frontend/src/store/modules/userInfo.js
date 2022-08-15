const userInfo = {
    namespaced: true,
    state: {
        userType: "구매자",
        userNo: "9999",
        tokken: "1",
        userId: "hello",
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
        SET_USERID: (state, id) => {
            state.userId = id;
        },
        CLEAR_USERTYPE: (state) => {
            state.userType = "구매자";
        },
        CLEAR_USERNO: (state) => {
            state.userNo = "9999";
        },
        CLEAR_TOKKEN: (state) => {
            state.tokken = "";
        },
        CLEAR_USERID: (state) => {
            state.userId = "hello";
        },
    },
    actions: {
        logout: ({ commit }) => {
            commit("CLEAR_USERTYPE");
            commit("CLEAR_USERNO");
            commit("CLEAR_TOKKEN");
            commit("CLEAR_USERID");
        },
    },
};

export default userInfo;
