package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.entity.UserCardRelationship;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCardRelationshipService {

    public List<UserCardRelationship> findAll();

    public Optional<UserCardRelationship> findById(int id);

    public void update(UserCardRelationship userCardRelationship);

    public void deleteById(int id);

    public List<UserCardRelationship> findByCardId(int cardId);

    public List<UserCardRelationship> findByUserId(int userId);

}
