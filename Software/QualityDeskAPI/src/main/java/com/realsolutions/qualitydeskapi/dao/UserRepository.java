package com.realsolutions.qualitydeskapi.dao;

import com.realsolutions.qualitydeskapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM Users WHERE Mail = :mail", nativeQuery = true)
    public List<User> findByMail(@Param("mail") String mail);

    @Query(value = "SELECT * FROM Users WHERE IsCardDefined = :isCardDefined", nativeQuery = true)
    public List<User> selectByCardDefined(@Param("isCardDefined") Boolean isCardDefined);
}