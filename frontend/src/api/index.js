import axios from "axios";
//i7a602.p.ssafy.io
//localhost
axios.defaults.headers.post['Access-Control-Allow-Origin']='*';

function CustomerInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be/customer",
        baseURL: "https://localhost:8080/be/customer",
        headers: {
            "content-type": "application/json",
        },
        withCredentials: true, // 쿠키 cors 통신 설정
    });
    return instance;
}

function SellerInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be/seller",
        baseURL: "https://localhost:8080/be/seller",
        header: {
            "content-type": "application/json",
        },
        withCredentials: true, // 쿠키 cors 통신 설정
    });
    return instance;
}

function MarketInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be",
        baseURL: "https://localhost:8080/be",
        headers: {
            "Content-type": "application/json",
        },
        withCredentials: true
    });
    return instance;
}

function StoreInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be/store",
        baseURL: "https://localhost:8080/be/store",
        headers: {
            "Content-type": "application/json",
        },
        withCredentials: true
    });
    return instance;
}
function StoreRegisterInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be/store",
        baseURL: "https://localhost:8080/be/store",
        headers: {
            "Content-type": "multipart/form-data",
        },
        withCredentials: true
    });
    return instance;
}
function ItemInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be/item",
        baseURL: "https://localhost:8080/be/item",
        headers: {
            "Content-type": "application/json",
        },
        withCredentials: true
    });
    return instance;
}
function OrderInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be/order",
        baseURL: "https://localhost:8080/be/order",
        headers: {
            "Content-type": "application/json",
        },
        withCredentials: true
    });
    return instance;
}

function CallInstance() {
    const instance = axios.create({
        //baseURL: "http://i7a602.p.ssafy.io:8080/be/",
        baseURL: "https://localhost:8080/be/",
        headers: {
            "content-type": "application/json",
        },
        withCredentials: true
    });
    return instance;
}

export { MarketInstance, SellerInstance, CustomerInstance, StoreInstance, StoreRegisterInstance, ItemInstance, OrderInstance, CallInstance };
