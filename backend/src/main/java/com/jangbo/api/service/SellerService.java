package com.jangbo.api.service;

import com.jangbo.db.entity.Seller;
import com.jangbo.db.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

    @Transactional
    public Integer join(Seller seller) {
        validateDuplicateSeller(seller);
        sellerRepository.save(seller);
        return seller.getSellerNo();
    }

    private void validateDuplicateSeller(Seller seller) {
        //EXCEPTION
        List<Seller> findSellers = sellerRepository.findBySellerId(seller.getSellerId());
        if (!findSellers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Seller findOne(Integer sellerNo) {
        return sellerRepository.findOne(sellerNo);
    }

    @Transactional
    public boolean update(Integer sellerNo, String businessNumber, String sellerName, String sellerPhone) {
        Seller seller = sellerRepository.findOne(sellerNo);
        seller.setBusinessNumber(businessNumber);
        seller.setSellerName(sellerName);
        seller.setSellerPhone(sellerPhone);
        return true;
    }

}
