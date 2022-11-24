import { CallInstance } from "./index.js";

const api = CallInstance();

async function getCall(storeno, success, fail) {
    await api.get(`/call?storeno=${storeno}`).then(success).catch(fail);
}

async function makeCall(id, no, success, fail) {
    await api.post(`/call?customerId=${id}&storeNo=${no}`).then(success).catch(fail);
}

async function deleteCall(id, no, success, fail) {
    await api.delete(`/call?customerId=${id}&storeNo=${no}`).then(success).catch(fail);
}

export { getCall, makeCall, deleteCall };
