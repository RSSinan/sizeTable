package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.dao.UserCardRelationshipRepository;
import com.realsolutions.qualitydeskapi.entity.Card;
import com.realsolutions.qualitydeskapi.entity.UserCardRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCardRelationshipServiceImpl implements UserCardRelationshipService {

    private final UserCardRelationshipRepository userCardRelationshipRepository;

    @Autowired
    public UserCardRelationshipServiceImpl(UserCardRelationshipRepository userCardRelationshipRepository) {
        this.userCardRelationshipRepository = userCardRelationshipRepository;
    }

    @Override
    public List<UserCardRelationship> findAll() {
        return userCardRelationshipRepository.findAll();
    }

    @Override
    public Optional<UserCardRelationship> findById(int id) {
        return userCardRelationshipRepository.findById(id);
    }

    @Override
    public void update(UserCardRelationship userCardRelationship) {
        userCardRelationshipRepository.save(userCardRelationship);
    }

    @Override
    public void deleteById(int id) {
        userCardRelationshipRepository.deleteById(id);
    }

    @Override
    public List<UserCardRelationship> findByCardId(int cardId) {
        return userCardRelationshipRepository.findByCardId(cardId);
    }

    @Override
    public List<UserCardRelationship> findByUserId(int userId) {
        return userCardRelationshipRepository.findByUserId(userId);
    }
}
