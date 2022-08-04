import axios from "axios";

function MarketInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:8080",
        headers: {
            "Content-type": "application/json",
        },
    });
    return instance;
}

export { MarketInstance };
