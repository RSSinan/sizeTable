package com.realsolutions.qualitydeskapi.service;

import com.realsolutions.qualitydeskapi.dao.UserRepository;
import com.realsolutions.qualitydeskapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public List<User> selectByCardDefined(Boolean isCardDefined) {
        return userRepository.selectByCardDefined(isCardDefined);
    }
}
