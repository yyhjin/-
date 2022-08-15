import { StoreInstance, StoreRegisterInstance } from "./index.js";

const api = StoreInstance();
const api_register = StoreRegisterInstance();

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

async function storeRegister(formdata, success, fail) {
    await api_register.post(``, formdata).then(success).catch(fail);
}

//판매자 상점정보조회.
async function getStoreBySellerNo(sellerNo, success, fail) {
    await api.get(`/seller/${sellerNo}`).then(success).catch(fail);
}

//상점정보수정
async function modifyStore(storeNo, storeEditPatchReq, success, fail) {
    await api.patch(`/${storeNo}`, storeEditPatchReq).then(success).catch(fail);
}

//상점 프로필사진 불러오기
async function getIMG(storeNo, success, fail) {
    await api.get(`/img/${storeNo}`).then(success).catch(fail);
}

export { StoreInMarketList, StoreDetail, StoreRoomDetail, marketByName, storeRegister, getStoreBySellerNo, modifyStore, getIMG };
