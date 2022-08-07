import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import SelectState from "../views/SelectState.vue";
import CustomerJoinView from "../views/Customer/CustomerJoinView.vue";
import SellerJoinView from "../views/Seller/SellerJoinView.vue";
import MyPageView from "../views/Customer/MyPageView.vue";
import CustomerProfileView from "../views/Customer/CustomerProfileView.vue";
import MyStoreView from "../views/Seller/MyStoreView.vue";
import testView from "../views/test.vue";
import SearchedMarketView from "../views/SearchedMarketView.vue";
import MarketListView from "../views/MarketListVIew.vue";
import StoreOpenView from "../views/StoreOpenView.vue";

const routes = [
    {
        path: "/test",
        name: "test",
        component: testView,
    },
    {
        path: "/",
        name: "home",
        component: HomeView,
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
    },

    {
        path: "/searchStore",
        name: "searchStore",
        component: SearchedMarketView,
    },

    {
        path: "/joinCustomer",
        name: "joinCustomer",
        component: CustomerJoinView,
    },
    {
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
    },
    {
        path: "/customer/:id/profile",
        name: "profile",
        component: CustomerProfileView,
    },
    {
        path: "/seller/:id",
        name: "mystore",
        component: MyStoreView,
    },
    {
        path: "/openStore",
        name: "openStore",
        component: StoreOpenView,
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
