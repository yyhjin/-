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

async function logout(params, success, fail) {
    await api.post("/logouts", params).then(success).catch(fail);
}

export { sidoList, gugunList, marketList, marketByName, logout };
