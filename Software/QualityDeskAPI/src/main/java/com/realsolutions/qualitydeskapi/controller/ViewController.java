package com.realsolutions.qualitydeskapi.controller;

import com.realsolutions.qualitydeskapi.entity.*;
import com.realsolutions.qualitydeskapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/view")
public class ViewController {

    private final UserService userService;
    private final CardService cardService;
    private final UserCardRelationshipService userCardRelationshipService;
    private final MeasurePointService measurePointService;
    private final MeasurementService measurementService;
    private final StepService stepService;
    private final SizeService sizeService;
    private final OrderService orderService;

    @Autowired
    public ViewController(UserService userService, CardService cardService, UserCardRelationshipService userCardRelationshipService, MeasurePointService measurePointService, MeasurementService measurementService, StepService stepService, SizeService sizeService, OrderService orderService) {
        this.userService = userService;
        this.cardService = cardService;
        this.userCardRelationshipService = userCardRelationshipService;
        this.measurePointService = measurePointService;
        this.measurementService = measurementService;
        this.stepService = stepService;
        this.sizeService = sizeService;
        this.orderService = orderService;
    }

    @GetMapping("/Index")
    public String Index(Model theModel) {

        List<Measurement> measurementList = measurementService.findAll();

        theModel.addAttribute("measurementList", measurementList);

        return "index";
    }

    @GetMapping("/UserDefinition")
    public String UserDefinition(Model theModel) {

        List<User> userList = userService.findAll();

        User newUser = new User();

        theModel.addAttribute("userList", userList);
        theModel.addAttribute("newUser", newUser);

        return "user_definition";
    }

    @GetMapping("/CardDefinition")
    public String CardDefinition(Model theModel) {

        List<Card> cardList = cardService.findAll();

        Card newCard = new Card();

        theModel.addAttribute("cardList", cardList);
        theModel.addAttribute("newCard", newCard);

        return "card_definition";
    }

    @GetMapping("/UserCardRelationship")
    public String UserCardRelationship(Model theModel) {

        List<UserCardRelationship> userCardRelationshipList = userCardRelationshipService.findAll();
        List<User> userListByIsCardDefined = userService.selectByCardDefined(false);
        List<Card> cardListByIsUsing = cardService.selectCardByIsUsing(false);

        UserCardRelationship newUserCardRelationship = new UserCardRelationship();

        theModel.addAttribute("userCardRelationshipList", userCardRelationshipList);
        theModel.addAttribute("newUserCardRelationship", newUserCardRelationship);
        theModel.addAttribute("userListByIsCardDefined", userListByIsCardDefined);
        theModel.addAttribute("cardListByIsUsing", cardListByIsUsing);

        return "user_card_relationship";
    }

    @GetMapping("/OrderDefinition")
    public String OrderDefinition(Model theModel) {

        List<Order> orderList = orderService.findAll();

        Order newOrder = new Order();

        theModel.addAttribute("orderList", orderList);
        theModel.addAttribute("newOrder", newOrder);

        return "order_definition";
    }

    @GetMapping("/SizeDefinition")
    public String SizeDefinition(Model theModel) {

        List<Size> sizeList = sizeService.findAll();

        Size newSize = new Size();

        theModel.addAttribute("sizeList", sizeList);
        theModel.addAttribute("newSize", newSize);

        return "size_definition";
    }

    @GetMapping("/MeasurePointDefinition")
    public String MeasurePointDefinition(Model theModel) {

        List<MeasurePoint> measurePointList = measurePointService.findAll();

        MeasurePoint newMeasurePoint = new MeasurePoint();

        theModel.addAttribute("measurePointList", measurePointList);
        theModel.addAttribute("newMeasurePoint", newMeasurePoint);

        return "measure_point_definition";
    }

    @GetMapping("/StepDefinition")
    public String StepDefinition(Model theModel) {

        List<Step> stepList = stepService.findAll();
        List<Size> sizeList = sizeService.findAll();
        List<Order> orderList = orderService.findAll();
        List<MeasurePoint> measurePointList = measurePointService.findAll();

        Step newStep = new Step();

        theModel.addAttribute("stepList", stepList);
        theModel.addAttribute("sizeList", sizeList);
        theModel.addAttribute("orderList", orderList);
        theModel.addAttribute("measurePointList", measurePointList);
        theModel.addAttribute("newStep", newStep);

        return "step_definition";
    }

    @PostMapping("/AddUser")
    public String AddUser(@ModelAttribute("newUser") User user) {

        List<User> userByEmailList = userService.findByMail(user.getMail());

        if (userByEmailList.isEmpty()) {

            user.setId(0);
            user.setCardDefined(false);
            user.setCreatedDate(Instant.now());
            user.setLastUpdatedDate(Instant.now());
            userService.update(user);

            return "redirect:/view/UserDefinition?user_add_ok";
        } else {

            return "redirect:/view/UserDefinition?user_mail_already_defined";
        }
    }

    @PostMapping("/AddCard")
    public String AddCard(@ModelAttribute("newCard") Card card) {

        System.out.println(card.getCardNo());
        List<Card> cardByCardNoList = cardService.findByCardNo(card.getCardNo());

        System.out.println(cardByCardNoList);

        if (cardByCardNoList.isEmpty()) {

            card.setId(0);
            card.setUsing(false);
            cardService.update(card);

            return "redirect:/view/CardDefinition?card_add_ok";
        } else {

            return "redirect:/view/CardDefinition?card_no_already_defined";
        }
    }

    @PostMapping("/AddSize")
    public String AddSize(@ModelAttribute("newSize") Size size) {

        List<Size> sizeByNameList = sizeService.findByName(size.getName());

        if (sizeByNameList.isEmpty()) {

            size.setId(0);
            sizeService.update(size);

            return "redirect:/view/SizeDefinition?size_add_ok";
        } else {

            return "redirect:/view/SizeDefinition?size_name_already_defined";
        }
    }

    @PostMapping("/AddOrder")
    public String AddOrder(@ModelAttribute("newOrder") Order order) {

        List<Order> orderByOrderNoList = orderService.findByOrderNo(order.getOrderNo());

        if (orderByOrderNoList.isEmpty()) {

            order.setId(0);
            orderService.update(order);

            return "redirect:/view/OrderDefinition?order_add_ok";
        } else {

            return "redirect:/view/OrderDefinition?order_no_already_defined";
        }
    }

    @PostMapping("/AddMeasurePoint")
    public String AddMeasurePoint(@ModelAttribute("newMeasurePoint") MeasurePoint measurePoint) {

        List<MeasurePoint> measurePointByNameList = measurePointService.findByName(measurePoint.getName());

        if (measurePointByNameList.isEmpty()) {

            measurePoint.setId(0);
            measurePointService.update(measurePoint);

            return "redirect:/view/MeasurePointDefinition?measure_point_add_ok";
        } else {

            return "redirect:/view/MeasurePointDefinition?measure_point_name_already_defined";
        }
    }

    @PostMapping("/AddUserCardRelationship")
    public String AddUserCardRelationship(@ModelAttribute("newUserCardRelationship") UserCardRelationship userCardRelationship) {

        Optional<User> userResult = userService.findById(userCardRelationship.getUser().getId());
        Optional<Card> cardResult = cardService.findById(userCardRelationship.getCard().getId());

        userCardRelationship.setId(0);
        userCardRelationship.setUser(userResult.get());
        userCardRelationship.setCard(cardResult.get());
        userCardRelationship.getCard().setUsing(true);
        userCardRelationship.getUser().setCardDefined(true);
        userCardRelationshipService.update(userCardRelationship);

        return "redirect:/view/UserCardRelationship?user_card_relationship_add_ok";
    }

    @PostMapping("/AddStep")
    public String AddStep(@ModelAttribute("newStep") Step step) {

        List<Step> stepResult = stepService.findByOrderIdMeasurePointIdSizeId(step.getOrder().getId(), step.getSize().getId(), step.getMeasurePoint().getId());

        if (!stepResult.isEmpty()) {

            return "redirect:/view/StepDefinition?step_already_exist";

        } else {

            Optional<Size> sizeResult = sizeService.findById(step.getSize().getId());
            Optional<Order> orderResult = orderService.findById(step.getOrder().getId());
            Optional<MeasurePoint> measurePointResult = measurePointService.findById(step.getMeasurePoint().getId());

            step.setId(0);
            step.setSize(sizeResult.get());
            step.setOrder(orderResult.get());
            step.setMeasurePoint(measurePointResult.get());
            stepService.update(step);

            return "redirect:/view/StepDefinition?step_add_ok";

        }
    }

    @PostMapping("/DeleteCard")
    public String DeleteCard(@RequestParam(name = "cardNo") String cardNo) {

        List<Card> cardList = cardService.findByCardNo(cardNo);

        if (!cardList.isEmpty()) {

            List<UserCardRelationship> userCardRelationshipList = userCardRelationshipService.findByCardId(cardList.get(0).getId());

            if (userCardRelationshipList.isEmpty()) {

                cardService.deleteById(cardList.get(0).getId());
                return "redirect:/view/CardDefinition?delete_ok";

            } else {
                return "redirect:/view/CardDefinition?delete_err";
            }
        } else {
            return "redirect:/view/CardDefinition?delete_err";
        }
    }

    @PostMapping("/DeleteMeasurePoint")
    public String DeleteMeasurePoint(@RequestParam(name = "name") String name) {

        List<MeasurePoint> measurePointList = measurePointService.findByName(name);

        if (!measurePointList.isEmpty()) {

            List<Step> stepList = stepService.findByMeasurePointId(measurePointList.get(0).getId());

            if (stepList.isEmpty()) {

                measurePointService.deleteById(measurePointList.get(0).getId());
                return "redirect:/view/MeasurePointDefinition?delete_ok";

            } else {
                return "redirect:/view/MeasurePointDefinition?delete_err";
            }

        } else {
            return "redirect:/view/MeasurePointDefinition?delete_err";
        }

    }

    @PostMapping("/DeleteOrder")
    public String DeleteOrder(@RequestParam(name = "orderNo") String orderNo) {

        List<Order> orderList = orderService.findByOrderNo(orderNo);

        if (!orderList.isEmpty()) {

            List<Step> stepList = stepService.findByOrderId(orderList.get(0).getId());

            if (stepList.isEmpty()) {

                orderService.deleteById(orderList.get(0).getId());
                return "redirect:/view/OrderDefinition?delete_ok";

            } else {
                return "redirect:/view/OrderDefinition?delete_err";
            }

        } else {
            return "redirect:/view/OrderDefinition?delete_err";
        }

    }

    @PostMapping("/DeleteSize")
    public String DeleteSize(@RequestParam(name = "name") String name) {

        List<Size> sizeList = sizeService.findByName(name);

        if (!sizeList.isEmpty()) {

            List<Step> stepList = stepService.findBySizeId(sizeList.get(0).getId());

            if (stepList.isEmpty()) {

                sizeService.deleteById(sizeList.get(0).getId());
                return "redirect:/view/SizeDefinition?delete_ok";

            } else {
                return "redirect:/view/SizeDefinition?delete_err";
            }

        } else {
            return "redirect:/view/SizeDefinition?delete_err";
        }

    }

    @PostMapping("/DeleteStep")
    public String DeleteStep(@RequestParam(name = "id") String id) {

        Optional<Step> result = stepService.findById(Integer.parseInt(id));

        if (result.isPresent()) {

            List<Measurement> measurementList = measurementService.findByStepId(result.get().getId());

            if (measurementList.isEmpty()) {

                stepService.deleteById(result.get().getId());
                return "redirect:/view/StepDefinition?delete_ok";

            } else {
                return "redirect:/view/StepDefinition?delete_err";
            }
        } else {
            return "redirect:/view/StepDefinition?delete_err";
        }
    }

    @PostMapping("/DeleteUserCardRelationship")
    public String DeleteUserCardRelationship(@RequestParam(name = "id") String id) {

        Optional<UserCardRelationship> result = userCardRelationshipService.findById(Integer.parseInt(id));

        if (result.isPresent()) {

            userCardRelationshipService.deleteById(result.get().getId());
            return "redirect:/view/UserCardRelationship?delete_ok";

        } else {
            return "redirect:/view/UserCardRelationship?delete_err";
        }
    }

    @PostMapping("/DeleteMeasurement")
    public String DeleteMeasurement(@RequestParam(name = "id") String id) {

        Optional<Measurement> result = measurementService.findById(Integer.parseInt(id));

        if (result.isPresent()) {

            measurementService.deleteById(result.get().getId());
            return "redirect:/view/Index?delete_ok";

        } else {
            return "redirect:/view/Index?delete_err";
        }
    }

    @PostMapping("/DeleteUser")
    public String DeleteUser(@RequestParam(name = "mail") String mail) {

        List<User> userList = userService.findByMail(mail);

        if (!userList.isEmpty()) {

            List<UserCardRelationship> userCardRelationshipList = userCardRelationshipService.findByUserId(userList.get(0).getId());

            if (userCardRelationshipList.isEmpty()) {

                List<Measurement> measurementList = measurementService.findByUserId(userList.get(0).getId());

                if (measurementList.isEmpty()) {

                    userService.deleteById(userList.get(0).getId());
                    return "redirect:/view/UserDefinition?delete_ok";

                } else {
                    return "redirect:/view/UserDefinition?delete_err";
                }

            } else {
                return "redirect:/view/UserDefinition?delete_err";
            }
        } else {
            return "redirect:/view/UserDefinition?delete_err";
        }
    }

}
