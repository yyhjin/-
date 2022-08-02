package com.jangbo.api.service;

import com.jangbo.db.dto.SidoDto;
import com.jangbo.db.entity.Gugun;
import com.jangbo.db.entity.Sido;
import com.jangbo.db.repository.SidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SidoService {

    @Autowired
    SidoRepository sidoRepository;

    public List<SidoDto> findAllSubstring() {
        return sidoRepository.findAllSubstring();
    }

}
