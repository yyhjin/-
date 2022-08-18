package com.jangbo.api.service;

import com.jangbo.api.request.ItemEditPatchReq;
import com.jangbo.api.request.ItemRecentPatchReq;
import com.jangbo.api.request.ItemRegisterPostReq;
import com.jangbo.db.entity.Item;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.ItemRepository;
import com.jangbo.db.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;

    /** 상점 - 상점별 아이템 정보 조회 */
    @Transactional(readOnly = true)
    public List<Item> findItemsByStore(Integer storeNo) {
        return itemRepository.findByStore(storeRepository.getOne(storeNo));

    }

    /** 상점 - 아이템 정보 등록 */
    @Transactional
    public Integer save(ItemRegisterPostReq itemRegisterPostReq) {
        Store store = storeRepository.getOne(itemRegisterPostReq.getStoreNo());
        Item item = Item.builder()
                .store(store)
                .itemName(itemRegisterPostReq.getItemName())
                .price((itemRegisterPostReq.getPrice()))
                .recent(itemRegisterPostReq.isRecent())
                .build();
        return itemRepository.save(item).getItemNo();
    }

    /** 상점 - 아이템 정보 수정 */
    @Transactional
    public Integer update(Integer itemNo, ItemEditPatchReq itemEditPatchReq) {
        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new IllegalAccessError("[ItemNo=" + itemNo + "] 해당 상품은 존재하지 않습니다."));
        item.update(itemEditPatchReq.getItemName(), itemEditPatchReq.getPrice());
        return itemNo;
    }

    /** 상점 - 아이템 최근 사용 여부 체크
     * 요청이 들어오면 true 혹은 false로 수정이 됩니다. */
    @Transactional
    public Integer isRecent(Integer itemNo, ItemRecentPatchReq itemRecentPatchReq) {
        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new IllegalAccessError("[ItemNo=" + itemNo + "] 해당 상품은 존재하지 않습니다."));
        item.isRecent(itemRecentPatchReq.isRecent());
        return itemNo;
    }


    /**상점 - 아이템 정보삭제 */
    @Transactional
    public void delete(Integer itemNo) {

        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new IllegalAccessError("[ItemNo=" + itemNo + "] 해당 상품은 존재하지 않습니다."));
        itemRepository.delete(item);
    }


    public Item findItemByItemNo(int itemNo) {
        return itemRepository.findByItemNo(itemNo);
    }


}
