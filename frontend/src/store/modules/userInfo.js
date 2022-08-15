const userInfo = {
    namespaced: true,
    state: {
        userType: "0",
        userNo: "0",
        tokken: "3",
        userId: "",
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
            state.userType = "";
        },
        CLEAR_USERNO: (state) => {
            state.userNo = "";
        },
        CLEAR_TOKKEN: (state) => {
            state.tokken = "";
        },
        CLEAR_USERID: (state) => {
            state.userId = "";
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
