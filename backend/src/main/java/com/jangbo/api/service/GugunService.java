package com.jangbo.api.service;

import com.jangbo.db.dto.GugunDto;
import com.jangbo.db.repository.GugunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GugunService {

    @Autowired
    GugunRepository gugunRepository;

    public List<GugunDto> findAllByGugunCodeStartsWith(String sidocode) {
        return gugunRepository.findAllByGugunCodeStartsWith(sidocode);
    }

}
