package com.jangbo.api.service;

import com.jangbo.db.dto.CallOrderDto;
import com.jangbo.db.entity.CallOrder;
import com.jangbo.db.repository.CallOrderRepository;
import com.jangbo.db.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallOrderService {

    @Autowired
    CallOrderRepository callOrderRepository;

    public Integer getCount(Integer storeno) {
        return callOrderRepository.countByStoreNo(storeno);
    }

    public CallOrder callsave(CallOrder callorder) {
        return callOrderRepository.save(callorder);
    }


    public List<CallOrderDto> findAllByStoreNoOrderByOrderNoAsc(Integer storeno) {
        return callOrderRepository.findAllByStoreNoOrderByOrderNoAsc(storeno);
    }


    public CallOrder findByStoreNoAndCustomerId(Integer storeNo, String customerId) {
        return callOrderRepository.findByStoreNoAndCustomerId(storeNo, customerId);
    }

    public void delete(CallOrder callOrder) {
        callOrderRepository.delete(callOrder);
    }


    public Integer updateAll(Integer storeNo) {
        return callOrderRepository.updateAll(storeNo);
    }

    public Integer updateByIndex(Integer storeNo, int index) {
        return callOrderRepository.updateByIndex(storeNo, index);
    }
}
