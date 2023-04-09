package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "SELECT * FROM Cards  WHERE CardNo = :cardNo", nativeQuery = true)
    public List<Card> findByCardNo(@Param("cardNo") String cardNo);

    @Query(value = "SELECT * FROM Cards  WHERE IsUsing = :isUsing", nativeQuery = true)
    public List<Card> selectCardByIsUsing(@Param("isUsing") Boolean isUsing);


}