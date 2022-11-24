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
    await api.get(`/image/${storeNo}`).then(success).catch(fail);
}

//시장에서 이름으로 상점찾기
async function searchName(marketNo, storeName, success, fail) {
    await api.get(`/name?marketNo=${marketNo}&storeName=${storeName}`).then(success).catch(fail);
}

//프로필 사진 수정
async function updateImg(storeNo, formdata, success, fail) {
    await api_register.post(`/image?storeNo=${storeNo}`, formdata).then(success).catch(fail);
}

//store_idx 변경
async function updateIdx(storeNo, success, fail) {
    await api.post(`/room/${storeNo}`).then(success).catch(fail);
}
//방정보수정
async function updateRoom(storeNo, params, success, fail) {
    await api.patch(`/room/${storeNo}`, params).then(success).catch(fail);
}

export { StoreInMarketList, StoreDetail, StoreRoomDetail, marketByName, storeRegister, getStoreBySellerNo, modifyStore, getIMG, searchName, updateImg, updateIdx, updateRoom };
