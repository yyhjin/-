import { createRouter, createWebHistory } from "vue-router";
//import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import SelectState from "../views/SelectState.vue";
import CustomerJoinView from "../views/Customer/CustomerJoinView.vue";
import SellerJoinView from "../views/Seller/SellerJoinView.vue";
import MyPageView from "../views/Customer/MyPageView.vue";
import CustomerProfileView from "../views/Customer/CustomerProfileView.vue";
import MyStoreView from "../views/Seller/MyStoreView.vue";
//import testView from "../views/test.vue";
import SearchedMarketView from "../views/SearchedMarketView.vue";
import MarketListView from "../views/MarketListVIew.vue";
import StoreOpenView from "../views/StoreOpenView.vue";
import StoreProfileView from "../views/Seller/StoreProfileView.vue";
import StoreMenuView from "../views/Seller/StoreMenuView.vue";
import StoreBillsView from "../views/Seller/StoreBillsView.vue";
import StoreRegisterView from "../views/Seller/StoreRegisterView.vue";
// import SellerRoomView from "../views/Seller/SellerRoomView.vue"; lazy-loading 대체
import SellerProfileView from "../views/Seller/SellerProfileView";
// import OpenVidu  from "../views/OpenVidu";
import TestTwo from "../views/Seller/TestTwo.vue";
import CustomerPass from "../views/Customer/CustomerPass";
import SellerPass from "../views/Seller/SellerPass";

import store from "@/store/index";
import { ElMessage } from "element-plus";

const open = () => {
    ElMessage({
        message: "로그인후 사용하세요",
        type: "warning",
    });
};

const beforeAuth = (isAuth) => (from, to, next) => {
    const isAuthenticated = store.getters["userInfo/isAuthenticated"];
    if (isAuthenticated && isAuth) {
        return next();
    } else {
        // 홈 화면으로 이동
        open();
        next("/login");
    }
};

const routes = [
    {
        path: "/",
        name: "home",
        component: TestTwo,
    },
    {
        path: "/selectjoin",
        name: "selectjoin",
        component: SelectState,
    },
    {
        path: "/search",
        name: "search",
        component: MarketListView,
        beforeEnter: beforeAuth(true),
    },

    {
        path: "/searchStore",
        name: "searchStore",
        component: SearchedMarketView,
        beforeEnter: beforeAuth(true),
    },

    {
        //소비자회원가입
        path: "/joinCustomer",
        name: "joinCustomer",
        component: CustomerJoinView,
    },
    {
        //판매자회원가입
        path: "/joinSeller",
        name: "joinSeller",
        component: SellerJoinView,
    },
    {
        path: "/join",
        name: "join",
        component: () => import(/* webpackChunkName: "about" */ "../views/Customer/CustomerJoinView.vue"),
    },
    {
        path: "/login",
        name: "login",
        component: LoginView,
    },
    {
        path: "/customer/:id",
        name: "mypage",
        component: MyPageView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/customer/:id/profile",
        name: "profile",
        component: CustomerProfileView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/customer/:id/CustomerPass",
        name: "CustomerPass",
        component: CustomerPass,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/seller/:id/SellerPass",
        name: "SellerPass",
        component: SellerPass,
        beforeEnter: beforeAuth(true),
    },
    {
        // 판매자 마이페이지 정보수정
        path: "/seller/profile",
        name: "sellerProfile",
        component: SellerProfileView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/seller/:id",
        name: "mystore",
        component: MyStoreView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/openStore",
        name: "openStore",
        component: StoreOpenView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/seller/:id/store",
        name: "store_profile",
        component: StoreProfileView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/seller/:id/menu",
        name: "store_menu",
        component: StoreMenuView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/seller/:id/bills",
        name: "store_bills",
        component: StoreBillsView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/seller/:id/register",
        name: "store_register",
        component: StoreRegisterView,
        beforeEnter: beforeAuth(true),
    },
    {
        path: "/room/:storeNo",
        name: "seller_room",
        component: () => import(/* webpackChunkName: "about" */ "../views/Seller/SellerRoomView.vue"),

        beforeEnter: beforeAuth(true),
    },
    {
        path: "/customer/room/:storeNo",
        name: "customer_room",
        component: () => import(/* webpackChunkName: "about" */ "../views/Customer/CustomerStoreView.vue"),

        beforeEnter: beforeAuth(true),
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
