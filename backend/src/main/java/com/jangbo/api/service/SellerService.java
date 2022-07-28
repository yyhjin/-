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
        validateDuplicateMember(seller);
        sellerRepository.save(seller);
        return seller.getSellerNo();
    }

    private void validateDuplicateMember(Seller seller) {
        //EXCEPTION
        List<Seller> findMembers = sellerRepository.findBySellerId(seller.getSellerId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Seller findOne(String sellerId) {
        return sellerRepository.findOne(sellerId);
    }
}
