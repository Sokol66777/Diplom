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

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/subscribeRequest")
public class SubscribeRequestController {

    @Autowired
    private SubscribeRequestService subscribeRequestService;

    @Autowired
    private UserFasad userFasad;

    @Autowired
    private UserService userService;



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
        Set<Integer> pages = new HashSet<>();
        Page<SubscribeRequest> subRequests = subscribeRequestService.findByIdChanel(idChanel,pageable);
        ModelAndView modelAndView = new ModelAndView("currentUserNotifications");
        if(subRequests.getTotalPages()>6){
            if(subRequests.getNumber()>3){
                pages.add(0);
                pages.add(-1);
            }else{
                pages.add(0);
                pages.add(1);
                pages.add(2);
            }
            if(subRequests.getNumber()>3 && (subRequests.getNumber()<(subRequests.getTotalPages()-2))){
                pages.add(subRequests.getNumber()-2);
                pages.add(subRequests.getNumber()-1);
            }
            if(subRequests.getNumber()>2 && (subRequests.getNumber()<(subRequests.getTotalPages()-3))){
                pages.add(subRequests.getNumber());
            }
            if(subRequests.getNumber()>1 && (subRequests.getNumber()<(subRequests.getTotalPages()-4))){
                pages.add(subRequests.getNumber()+1);
                pages.add(subRequests.getNumber()+2);
            }
            if(subRequests.getNumber()<subRequests.getTotalPages()-4){
                pages.add(-1);
                pages.add(subRequests.getTotalPages());
            }else{
                pages.add(subRequests.getTotalPages()-3);
                pages.add(subRequests.getTotalPages()-2);
                pages.add(subRequests.getTotalPages()-1);
            }
            modelAndView.addObject("pages",pages);
        }
        modelAndView.addObject("currentPage", subRequests.getNumber());
        modelAndView.addObject("subRequests",subRequests.getContent());
        modelAndView.addObject("totalPages",subRequests.getTotalPages());
        return modelAndView;
    }

}
