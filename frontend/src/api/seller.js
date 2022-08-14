import { SellerInstance } from "./index.js";

const api = SellerInstance();

async function getId(id, success, fail) {
    await api.get(`/idcheck/${id}`).then(success).catch(fail);
}

async function joinSeller(params, success, fail) {
    await api.post(`/signup`, params).then(success).catch(fail);
}

async function loginSeller(params, success, fail) {
    await api.post(`/login`, params).then(success).catch(fail);
}

//회원수정
async function updateSeller(no, params, success, fail) {
    await api.put(`/${no}`, params).then(success).catch(fail);
}

//회원조회
async function getSeller(no, success, fail) {
    await api.get(`/${no}`).then(success).catch(fail);
}

export { getId, joinSeller, loginSeller, updateSeller, getSeller };
