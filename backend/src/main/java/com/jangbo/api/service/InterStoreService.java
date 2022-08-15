package com.jangbo.api.service;

import com.jangbo.api.request.InterStoreReq;
import com.jangbo.db.entity.*;
import com.jangbo.db.repository.CustomerRepository;
import com.jangbo.db.repository.InterStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InterStoreService {

    private final CustomerRepository customerRepository;
    private final InterStoreRepository interStoreRepository;

    public boolean update(Integer customerNo, InterStoreReq interStoreReq) {
        Customer customer = customerRepository.findOne(customerNo);
        List<InterStore> interStores = interStoreRepository.findAllByCustomer(customer);

        for (InterStore interStore : interStores) {
            if (interStore.getStoreNo() == interStoreReq.getStoreNo()) {
                interStoreRepository.deleteById(interStore.getId());
                return false; //찜목록 삭제
            }
        }

        InterStore interStore = customer.addInterStore(new InterStore(), interStoreReq.getStoreNo());
        interStoreRepository.saveAll(new ArrayList(Collections.singleton(interStore)));
        return true; // 찜목록 추가

    }

}
