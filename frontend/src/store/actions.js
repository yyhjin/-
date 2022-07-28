import axios from "axios";

export function searchDoList({ state }, payload) {
    console.log("searchDoList", state, payload);
    const url = "/search";
    return axios.post(url, payload);
}
