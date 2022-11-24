import { MarketInstance } from "./index.js";

const api = MarketInstance();

async function sidoList(success, fail) {
    await api.get(`/sido`).then(success).catch(fail);
}

async function gugunList(params, success, fail) {
    await api.get(`/gugun`, { params: params }).then(success).catch(fail);
}

async function marketList(params, success, fail) {
    await api.get(`/market/addr`, { params: params }).then(success).catch(fail);
}

async function marketByName(params, success, fail) {
    await api.get(`/market/name`, { params: params }).then(success).catch(fail);
}

//번호로 검색
async function marketByNo(no, success, fail) {
    await api.get(`/market/no?marketno=${no}`).then(success).catch(fail);
}

async function logout(params, success, fail) {
    await api.post("/logouts", params).then(success).catch(fail);
}

//라이브상점내에 주문판 조회 (구매자입장)
async function getItem(no, success, fail) {
    await api.get(`/item/store/${no}`).then(success).catch(fail);
}

//라이브상점내 주문 (구매자)
async function getOrder(customer_no, store_no, params, success, fail) {
    await api.post(`/order/${customer_no}/${store_no}`, { products: params }).then(success).catch(fail);
}

export { sidoList, gugunList, marketList, marketByNo, marketByName, logout, getItem, getOrder };
