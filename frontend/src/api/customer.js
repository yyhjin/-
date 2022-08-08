import { CustomerInstance } from "./index.js";

const api = CustomerInstance();

async function getId(id, success, fail) {
    await api.get(`/idcheck/${id}`).then(success).catch(fail);
}

async function joinCustomer(params, success, fail) {
    await api.post(`/signup`, params).then(success).catch(fail);
}

async function loginCustomer(params, success, fail) {
    await api.post(`/login`, params).then(success).catch(fail);
}
export { getId, joinCustomer, loginCustomer };
