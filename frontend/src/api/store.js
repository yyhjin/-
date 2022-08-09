import { StoreInstance } from "./index.js";

const api = StoreInstance();

async function StoreInMarketList(marketNo, success, fail) {
    await api.get(`/market/${marketNo}`).then(success).catch(fail);
}

async function StoreDetail(storeNo, success, fail) {
    await api.get(`/${storeNo}`).then(success).catch(fail);
}

async function StoreRoomDetail(storeNo, success, fail) {
    await api.get(`/room/${storeNo}`).then(success).catch(fail);
}

async function marketByName(params, success, fail) {
    await api.get(`/market/name`, { params: params }).then(success).catch(fail);
}

export { StoreInMarketList, StoreDetail, StoreRoomDetail, marketByName };
