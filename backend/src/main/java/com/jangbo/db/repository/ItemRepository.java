package com.jangbo.db.repository;

import com.jangbo.db.entity.Item;
import com.jangbo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByStore(Store store);
    Item findByItemNo(@Param("itemNo") Integer itemNo);
}
