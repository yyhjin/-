package com.jangbo.db.repository;

import com.jangbo.db.entity.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SidoRepository extends JpaRepository<Sido,String> {

}
