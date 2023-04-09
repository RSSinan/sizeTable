package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.Card;
import com.realsolutions.qualitydeskapi.entity.User;
import com.realsolutions.qualitydeskapi.entity.UserCardRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCardRelationshipRepository extends JpaRepository<UserCardRelationship, Integer> {

    @Query(value = "SELECT * FROM UserCardRelationships  WHERE CardId = :cardId", nativeQuery = true)
    public List<UserCardRelationship> findByCardId(@Param("cardId") int cardId);

    @Query(value = "SELECT * FROM UserCardRelationships  WHERE UserId = :userId", nativeQuery = true)
    public List<UserCardRelationship> findByUserId(@Param("userId") int userId);
}