package com.jangbo.db.repository;

import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.dto.StoreDto;
import com.jangbo.db.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface MarketRepository extends JpaRepository<Market,Integer> {

    List<MarketDto> findAllByGugun_SidoGugunStartsWith(@Param("sidogugun") String sidogugun);

    List<MarketDto> findAllByMarketNameContains(@Param("marketname") String marketname);

    MarketDto findMarketByMarketNo(@Param("marketno") Integer marketno);
}
