package com.pvt.controllers.subscribeRequest;

import com.pvt.fasad.UserFasad;
import com.pvt.forms.UserForm;
import com.pvt.jar.entity.SubscribeRequest;
import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.services.SubscribeRequestService;
import com.pvt.jar.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/subscribeRequest")
public class SubscribeRequestController {

    @Autowired
    SubscribeRequestService subscribeRequestService;

    @Autowired
    UserFasad userFasad;

    @Autowired
    UserService userService;



    @GetMapping
    public ModelAndView sendSubscribeRequest(@RequestParam("idChanel") long idChanel, @RequestParam("idUser")long idUser,
                                     @RequestParam("usernameSubscriber") String usernameSubscriber,
                                     HttpServletRequest request, HttpServletResponse response) throws LogicException {

        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setIdChanel(idChanel);
        subscribeRequest.setIdSubscriber(idUser);
        subscribeRequest.setUsernameSubscriber(usernameSubscriber);

        subscribeRequestService.add(subscribeRequest);

        UserForm friendUserForm = userFasad.get(idChanel);
        boolean isSubscribe = false;

        ModelAndView modelAndView = new ModelAndView("friendUser");
        modelAndView.addObject("isSubscribe",isSubscribe);
        modelAndView.addObject("friendUser",friendUserForm);
        modelAndView.addObject("message","subscription request has been sent");
        return modelAndView;
    }

    @GetMapping("/currentUserNotifications")
    public ModelAndView currentUserNotifications(@RequestParam("idUser") long idChanel,
                                                 @PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable){

        Page<SubscribeRequest> subRequests = subscribeRequestService.findByIdChanel(idChanel,pageable);
        ModelAndView modelAndView = new ModelAndView("currentUserNotifications");
        modelAndView.addObject("subRequests",subRequests.getContent());
        modelAndView.addObject("totalPages",subRequests.getTotalPages());
        return modelAndView;
    }

}
