package com.jangbo.db.repository;

import com.jangbo.db.dto.MarketDto;
import com.jangbo.db.entity.Market;
import com.jangbo.db.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface MarketRepository extends JpaRepository<Market,Integer> {

    @Query("select new com.jangbo.db.dto.MarketDto(m.marketNo, m.marketName, m.marketAddr, m.lat, m.lng) " +
            "from Market m where m.gugun.sidoGugun like :sidogugun%")
    List<MarketDto> findAllByGugun_SidoGugunStartsWith(@Param("sidogugun") String sidogugun);

    @Query("select new com.jangbo.db.dto.MarketDto(m.marketNo, m.marketName, m.marketAddr, m.lat, m.lng) " +
            "from Market m where m.marketName like %:marketname%")
    List<MarketDto> findAllByMarketNameContains(@Param("marketname") String marketname);


}
