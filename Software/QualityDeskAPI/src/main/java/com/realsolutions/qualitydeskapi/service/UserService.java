package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();

    public Optional<User> findById(int id);

    public void update(User user);

    public void deleteById(int id);

    public List<User> findByMail(String mail);

    public List<User> selectByCardDefined(Boolean isCardDefined);
}
