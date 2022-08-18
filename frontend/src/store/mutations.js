// 도 리스트
export function setDoList(state, DoList) {
    state.DoList = DoList;
}

// 시 리스트
export function setGuList(state, GuList) {
    state.GuList = GuList;
}

export function setSellList(state, value) {
    state.SellList.push({ name: value.name, count: value.count, price: value.price, max: value.max });
}

export function setReSellList(state, value) {
    state.SellList.splice(value, 1);
}
