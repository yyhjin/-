package com.jangbo.api.service;

import com.jangbo.api.request.StoreRegisterPostReq;
import com.jangbo.api.response.StoreInfoRes;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {


    private final StoreRepository storeRepository;

//
//    //private final SellerRepository sellerRepository;
//
//    public StoreRegisterPostReq findByNo(int storeNo) {
//        Store store = storeRepository.findById(storeNo).orElseThrow(()->new IllegalArgumentException("해당 상점이 없습니다 id="+storeNo));
//        return new StoreRegisterPostReq(store);
//    }
//
//    public void deleteBystoreNo(int storeNo) {
//        storeRepository.deleteById(storeNo);
//    }
//
//

    /** 게시글 - 목록 조회 */
    //@Transactional(readOnly = true)
    public List<StoreInfoRes> findAll() {

        return storeRepository.findAll()
                .stream()
                .map(StoreInfoRes::new)
                .collect(Collectors.toList());
    }

    /** 게시글 - 상세 조회 */
   // @Transactional(readOnly = true)
    public StoreInfoRes findById(int storeNo) {

        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalAccessError("[storeNo=" + storeNo + "] 해당 상점이 존재하지 않습니다."));

        return new StoreInfoRes(store);
    }

    /** 게시글 - 등록 */
   // @Transactional
    public Integer save(StoreRegisterPostReq storeRegisterPostReq) {

        return storeRepository.save(storeRegisterPostReq.toEntity())
                .getStoreNo();
    }

    /** 게시글 - 수정 */
//    @Transactional
//    public Long update(Long boardSeq, BoardUpdateRequestDto boardUpdateRequestDto) {
//
//        Board board = boardRepository.findById(boardSeq)
//                .orElseThrow(() -> new IllegalAccessError("[boardSeq=" + boardSeq + "] 해당 게시글이 존재하지 않습니다."));
//
//        board.update(boardUpdateRequestDto.getBoardSubject(), boardUpdateRequestDto.getBoardContent());
//
//        return boardSeq;
//    }

    /** 게시글 - 삭제 */
   // @Transactional
    public void delete(Integer storeNo) {

        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalAccessError("[storeNo=" + storeNo + "] 해당 상점이 존재하지 않습니다."));

        storeRepository.delete(store);
    }




}
