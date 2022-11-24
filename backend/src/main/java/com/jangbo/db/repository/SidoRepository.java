package com.jangbo.db.repository;

import com.jangbo.db.dto.SidoDto;
import com.jangbo.db.entity.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SidoRepository extends JpaRepository<Sido,String> {

    @Query("select new com.jangbo.db.dto.SidoDto(substring(s.sidoCode,1,2), s.sidoName) from Sido s")
    List<SidoDto> findAllSubstring();

}
