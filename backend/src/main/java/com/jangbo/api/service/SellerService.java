package com.jangbo.api.service;

import com.jangbo.db.entity.Seller;
import com.jangbo.db.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
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
