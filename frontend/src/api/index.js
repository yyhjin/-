import axios from "axios";

function CustomerInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/customer",
        headers: {
            "content-type": "application/json",
        },
        withCredentials: true,
    });
    return instance;
}

function SellerInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080/seller",
        header: {
            "content-type": "application/json",
        },
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

export { MarketInstance, SellerInstance, CustomerInstance, StoreInstance };
