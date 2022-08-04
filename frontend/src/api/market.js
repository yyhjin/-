import { MarketInstance } from "./index.js";

const api = MarketInstance();

async function sidoList(success, fail) {
    await api.get(`/sido`).then(success).catch(fail);
}

async function gugunList(sidoCode, success, fail) {
    await api.get(`/gugun/${sidoCode}`).then(success).catch(fail);
}

async function marketList(params, success, fail) {
    await api.get(`/market/addr`, { params: params }).then(success).catch(fail);
}

async function marketByName(params, success, fail) {
    await api.get(`/market/addr`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, marketList, marketByName };
