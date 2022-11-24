import { OrderInstance } from "./index.js"
const api = OrderInstance();


//주문생성
async function makeOrder(customerNo,storeNo,body,success, fail) {
    await api.post(`?customerNo=${customerNo}&storeNo=${storeNo}`,body)
    .then(success).catch(fail);
}
//주문상태수정
async function changeOrderState(body,success, fail) {
    await api.put(``,body)
    .then(success).catch(fail);
}

//주문목록조회-소비자
async function customerOrderList(customerno,success, fail) {
    await api.get(`/customer?customerno=${customerno}`)
    .then(success).catch(fail);
}


//주문목록조회-판매자
async function sellerOrderList(storeno,success, fail) {
    await api.get(`/store?storeno=${storeno}`)
    .then(success).catch(fail);
}

//첫주문여부 조회
async function isFirstOrder(customerno,success, fail) {
    await api.get(`/firstorder?customerno=${customerno}`)
    .then(success).catch(fail);
}   
//덤 추가.
async function pushDum(orderNo,freeItemRequest,success, fail) {
    await api.post(`/${orderNo}`,freeItemRequest)
    .then(success).catch(fail);
}   

export {makeOrder,changeOrderState,customerOrderList,sellerOrderList,isFirstOrder,pushDum}