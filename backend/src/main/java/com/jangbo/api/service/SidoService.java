package com.jangbo.api.service;

import com.jangbo.db.dto.SidoDto;
import com.jangbo.db.repository.SidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SidoService {

    @Autowired
    SidoRepository sidoRepository;

    /** 시도 전체 목록 조회(시도코드 2글자만 가져옴) */
    public List<SidoDto> findAllSubstring() {
        return sidoRepository.findAllSubstring();
    }

}
