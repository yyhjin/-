import { ItemInstance } from "./index.js";
const api = ItemInstance();

//상점 메뉴 조회.
async function menuList(storeNo, success, fail) {
    await api.get(`/store/${storeNo}`).then(success).catch(fail);
}

//아이템 crud
async function getItem(itemNo, success, fail) {
    await api.get(`/${itemNo}`).then(success).catch(fail);
}
async function delelteItem(itemNo, success, fail) {
    await api.delete(`/${itemNo}`).then(success).catch(fail);
}
async function modifyItem(itemNo, itemEditPatchReq, success, fail) {
    await api.patch(`/${itemNo}`, itemEditPatchReq).then(success).catch(fail);
}
async function addItem(storeNo, itemRegisterPostReq, success, fail) {
    await api.post(`/${storeNo}`, itemRegisterPostReq).then(success).catch(fail);
}
//최근사용여부.
async function checkUsed(itemNo, itemRecentPatchReq, success, fail) {
    await api.patch(`/check/${itemNo}`, itemRecentPatchReq).then(success).catch(fail);
}

//한 상점안에서 아이템 목록
async function getItemList(storeNo, success, fail) {
    await api.get(`/store/${storeNo}`).then(success).catch(fail);
}

export { menuList, getItem, delelteItem, modifyItem, addItem, checkUsed, getItemList };
