package com.jangbo.api.service;

import com.jangbo.api.request.RoomEditPatchReq;
import com.jangbo.api.request.StoreEditPatchReq;
import com.jangbo.api.request.StoreRegisterPostReq;
import com.jangbo.api.response.RoomInfoRes;
import com.jangbo.api.response.StoreInfoRes;
import com.jangbo.db.entity.Market;
import com.jangbo.db.entity.Seller;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.MarketRepository;
import com.jangbo.db.repository.SellerRepository;
import com.jangbo.db.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;
    private final MarketRepository marketRepository;
    private final FileService fileService;


    /** 상점- 전체 목록 조회  */
    @Transactional(readOnly = true)
    public List<StoreInfoRes> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(StoreInfoRes::new)
                .collect(Collectors.toList());
    }

    /** 상점 - 시장에 따른 상점목록 조회 */
    @Transactional(readOnly = true)
    public List<Store> findStoresByMarket(Integer marketNo) {
        return storeRepository.findByMarket(marketRepository.getOne(marketNo));

    }

    /** 상점 -상점 이름 검색 */
    @Transactional(readOnly = true)
    public List<Store> findStoreByNameandMarketNo(String storeName,int marketNo){
        List<Store> list = storeRepository.findAllByStoreNameSQL(storeName,marketNo);
        return list;
    }

    /** 상점 - 상점 정보 조회 */
    @Transactional(readOnly = true)
    public StoreInfoRes findStoreById(int storeNo) {
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalAccessError("[storeNo=" + storeNo + "] 해당 상점이 존재하지 않습니다."));

        return new StoreInfoRes(store);
    }

    @Transactional(readOnly = true)
    public Store findStoreBySeller(Integer sellerNo) {
        return storeRepository.findBySeller(sellerRepository.findOne(sellerNo));

    }

    /** 상점 - 상점 등록 */
    @Transactional
    public Integer save(StoreRegisterPostReq storeRegisterPostReq) {
        Seller seller = sellerRepository.findOne(storeRegisterPostReq.getSellerNo());
        Market market = marketRepository.getOne(storeRegisterPostReq.getMarketNo());

        Store store = Store.builder()
                .seller(seller)
                .market(market)
                .storeName(storeRegisterPostReq.getStoreName())
                .storeCategory(storeRegisterPostReq.getStoreCategory())
                .storePhone(storeRegisterPostReq.getStorePhone())
                .storeAddr(storeRegisterPostReq.getStoreAddr())
                .storeImg(storeRegisterPostReq.getStoreImg())
                .build();
        return storeRepository.save(store).getStoreNo();

    }

    /** 상점 - 상점 정보 수정 */
    @Transactional
    public Integer updateStore(Integer storeNo, StoreEditPatchReq storeEditPatchReq) {
        Market market = marketRepository.getOne(storeEditPatchReq.getMarketNo());
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalAccessError("[storeNo=" + storeNo + "] 해당 상점은 존재하지 않습니다."));
        store.updateStore(storeEditPatchReq.getStoreName(), storeEditPatchReq.getStoreCategory(), storeEditPatchReq.getStorePhone(), storeEditPatchReq.getStoreAddr(), market);

        return storeNo;
    }

    /** 상점 - 상점 정보삭제 */
    @Transactional
    public void delete(Integer storeNo) {
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalAccessError("[storeNo=" + storeNo + "] 해당 상점이 존재하지 않습니다."));
        if (store.getStoreImg() != "default.png") {
            fileService.deleteImg(storeNo);
        }
        storeRepository.delete(store);
    }


    /** 상점 - 방 정보 조회 */
    @Transactional(readOnly = true)
    public RoomInfoRes findRoomById(int storeNo) {
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalAccessError("[storeNo=" + storeNo + "] 해당 상점이 존재하지 않습니다."));
        return new RoomInfoRes(store);
    }

    /** 상점 - 방 정보 수정 */
    @Transactional
    public Integer updateRoom(Integer storeNo, RoomEditPatchReq roomEditPatchReq) {
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalAccessError("[storeNo=" + storeNo + "] 해당 상점은 존재하지 않습니다."));
        store.updateRoom(roomEditPatchReq.getStoreSubject(), roomEditPatchReq.getStoreIntro());
        return storeNo;
    }

    @Transactional
    public Integer updateStoreIdx(Integer storeNo) {
        Store store = storeRepository.findByStoreNo(storeNo);
        if (!store.isStoreIdx()) {
            store.updateStoreIdx(true);
        } else {
            store.updateStoreIdx(false);
        }
        return storeNo;
    }


}
