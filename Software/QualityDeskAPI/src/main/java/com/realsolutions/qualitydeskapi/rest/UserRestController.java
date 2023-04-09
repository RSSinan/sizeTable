package com.realsolutions.qualitydeskapi.rest;

import com.realsolutions.qualitydeskapi.entity.Card;
import com.realsolutions.qualitydeskapi.entity.User;
import com.realsolutions.qualitydeskapi.entity.UserCardRelationship;
import com.realsolutions.qualitydeskapi.service.CardService;
import com.realsolutions.qualitydeskapi.service.UserCardRelationshipService;
import com.realsolutions.qualitydeskapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/User")
public class UserRestController {

    private final UserService userService;
    private final CardService cardService;
    private final UserCardRelationshipService userCardRelationship;

    @Autowired
    public UserRestController(UserService userService, CardService cardService, UserCardRelationshipService userCardRelationship) {
        this.userService = userService;
        this.cardService = cardService;
        this.userCardRelationship = userCardRelationship;
    }

    @GetMapping("/GetUsers")
    public HashMap<Object, Object> GetUsers() {

        HashMap<Object, Object> usersMap = new HashMap<>();

        List<User> userList = userService.findAll();

        for(User user: userList) {
            HashMap<Object, Object> userMap = new HashMap<>();
            userMap.put("FullName", user.getFullName());
            userMap.put("Note", user.getNote());
            userMap.put("Mail", user.getMail());
            userMap.put("Password", user.getPassword());
            usersMap.put(user.getId(), userMap);
        }

        return usersMap;
    }

    @GetMapping("/CheckUserByForm")
    public HashMap<Object, Object> CheckUserByForm(@RequestBody HashMap<Object, Object> userInfoMap) {

        HashMap<Object, Object> usersMap = new HashMap<>();

        List<User> userList = userService.findByMail(String.valueOf(userInfoMap.get("mail")));

        if (!userList.isEmpty()) {

            HashMap<Object, Object> userMap = new HashMap<>();
            userMap.put("FullName", userList.get(0).getFullName());
            userMap.put("Note", userList.get(0).getNote());
            userMap.put("Mail", userList.get(0).getMail());
            userMap.put("Password", userList.get(0).getPassword());

            usersMap.put(userList.get(0).getId(), userMap);

        } else {
            usersMap.put(0, 0);
        }

        return usersMap;
    }

    @GetMapping("/CheckUserByCardNo")
    public HashMap<Object, Object> CheckUserByCardNo(@RequestBody HashMap<Object, Object> cardMap) {

        HashMap<Object, Object> usersMap = new HashMap<>();

        List<Card> cardList = cardService.findByCardNo(String.valueOf(cardMap.get("cardNo")));

        if (!cardList.isEmpty()) {

            List<UserCardRelationship> userCardRelationshipList = userCardRelationship.findByCardId(cardList.get(0).getId());

            if (!userCardRelationshipList.isEmpty()) {

                HashMap<Object, Object> userMap = new HashMap<>();
                userMap.put("FullName", userCardRelationshipList.get(0).getUser().getFullName());
                userMap.put("Note", userCardRelationshipList.get(0).getUser().getNote());
                userMap.put("Mail", userCardRelationshipList.get(0).getUser().getMail());
                userMap.put("Password", userCardRelationshipList.get(0).getUser().getPassword());

                usersMap.put(userCardRelationshipList.get(0).getUser().getId(), userMap);
            }

        } else {
            usersMap.put(0, 0);
        }

        return usersMap;
    }


}
