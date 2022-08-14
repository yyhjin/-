package com.jangbo.api.service;

import com.jangbo.db.dto.CallOrderDto;
import com.jangbo.db.entity.CallOrder;
import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.CallOrderRepository;
import com.jangbo.db.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallOrderService {

    @Autowired
    CallOrderRepository callOrderRepository;
    StoreRepository storeRepository;

    public Integer getCount(Integer storeno) {
        return callOrderRepository.countByStoreNo(storeno);
    }

    public CallOrder callsave(CallOrder callorder) {
        return callOrderRepository.save(callorder);
    }


    public List<CallOrderDto> findAllByStore_StoreNoOrderByCallOrderNoAsc(Integer storeno) {
        return callOrderRepository.findAllByStoreNoOrderByCallOrderNoAsc(storeno);
    }


    public void delete(Integer storeNo, String customerId) {
        CallOrder callOrder = callOrderRepository.findByStoreNoAndCustomerId(storeNo, customerId);
        callOrderRepository.delete(callOrder);
    }


}
