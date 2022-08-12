const userInfo = {
    namespaced: true,
    state: {
        userType: "1",
        userNo: "5",
        tokken: "3",
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
        CLEAR_TOKKEN: (state) => {
            state.tokken = "";
        },
    },
};

export default userInfo;
