package com.jangbo.api.service;

import com.jangbo.api.request.ItemRegisterPostReq;
import com.jangbo.db.entity.Item;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.ItemRepository;
import com.jangbo.db.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final StoreRepository storeRepository;

    private final ItemRepository itemRepository;

    @Transactional
    public Integer save(ItemRegisterPostReq itemRegisterPostReq) {
        Store store = storeRepository.getOne(itemRegisterPostReq.getStoreNo());
        Item item = Item.builder()
                .store(store)
                .itemNo(itemRegisterPostReq.getItemName())
                .price((itemRegisterPostReq.getPrice()))
                .recent(itemRegisterPostReq.isRecent())
                .build();
        return itemRepository.save(item).getItemNo();
    }

    @Transactional
    public Integer update(Integer itemNo, ItemRegisterPostReq itemRegisterPostReq) {
        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new IllegalAccessError("[ItemNo=" + itemNo + "] 해당 상품은 존재하지 않습니다."));
        item.update(itemRegisterPostReq.getItemName(), itemRegisterPostReq.getPrice(), itemRegisterPostReq.isRecent());
        return itemNo;
    }


    /**
     * 상품 - 상품 정보삭제
     */

    @Transactional
    public void delete(Integer itemNo) {

        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new IllegalAccessError("[ItemNo=" + itemNo + "] 해당 상품은 존재하지 않습니다."));
        itemRepository.delete(item);
    }
}
