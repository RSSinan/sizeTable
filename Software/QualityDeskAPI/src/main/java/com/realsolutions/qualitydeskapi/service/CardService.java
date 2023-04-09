package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    public List<Card> findAll();

    public Optional<Card> findById(int id);

    public void update(Card card);

    public void deleteById(int id);

    public List<Card> findByCardNo(String cardNo);

    public List<Card> selectCardByIsUsing(Boolean isUsing);


}
