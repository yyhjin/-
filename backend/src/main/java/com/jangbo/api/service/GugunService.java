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

    /** 시도코드에 해당하는 구군 목록 조회 */
    public List<GugunDto> findAllByGugunCodeStartsWith(String sidocode) {
        return gugunRepository.findAllByGugunCodeStartsWith(sidocode);
    }

}
