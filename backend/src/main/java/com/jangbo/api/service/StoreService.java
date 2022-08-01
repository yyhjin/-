package com.jangbo.api.service;

import com.jangbo.db.entity.Store;
import com.jangbo.db.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Optional<Store> selectBystoreNo(int storeNo){
    return storeRepository.findById(storeNo);
    }

    public void deleteBystoreNo(int storeNo){
        storeRepository.deleteById(storeNo);
    }
}
