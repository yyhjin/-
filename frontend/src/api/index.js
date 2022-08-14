import axios from "axios";

function CustomerInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/customer",
        headers: {
            "content-type": "application/json",
        },
        withCredentials: true // 쿠키 cors 통신 설정
    });
    return instance;
}

function SellerInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/seller",
        header: {
            "content-type": "application/json",
        },
        // withCredentials: true // 쿠키 cors 통신 설정
    });
    return instance;
}

function MarketInstance() {
    const instance = axios.create({ 
        baseURL: "http://localhost:8080",
        headers: {
            "Content-type": "application/json",
        },
    });
    return instance;
}

function StoreInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/store",
        headers: {
            "Content-type": "application/json",
        },
    });
    return instance;
}
function StoreRegisterInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/store",
        headers: {
            "Content-type": "multipart/form-data"
        },
    });
    return instance;
}
function ItemInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/item",
        headers: {
            "Content-type": "application/json",
        },
    });
    return instance;
}
function OrderInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/order",
        headers: {
            "Content-type": "application/json",
        },
    });
    return instance;
}

export { MarketInstance, SellerInstance, CustomerInstance, StoreInstance,StoreRegisterInstance,ItemInstance,OrderInstance };
