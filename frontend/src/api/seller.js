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

export { getId, joinSeller, loginSeller };
