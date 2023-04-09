package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.dao.CardRepository;
import com.realsolutions.qualitydeskapi.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> findById(int id) {
        return cardRepository.findById(id);
    }

    @Override
    public void update(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void deleteById(int id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Card> findByCardNo(String cardNo) {
        return cardRepository.findByCardNo(cardNo);
    }

    @Override
    public List<Card> selectCardByIsUsing(Boolean isUsing) {
        return cardRepository.selectCardByIsUsing(isUsing);
    }
}
