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

    public List<SidoDto> findAllSubstring() {
        return sidoRepository.findAllSubstring();
    }

}
