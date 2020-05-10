package app.controllers;

import app.entity.User;
import app.service.FindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class FindUserController {

    @Autowired
    private FindUserService findUserService;

    @GetMapping("/find")
    public String openFindFormUser(Model model){
        return findUserService.openFindFormUser(model);
    }

    @PostMapping("/find")
    public String submitFindUser(Model model, @ModelAttribute User user, @RequestHeader(value = "User-Agent") String agent){
        return findUserService.submitFindUser(model, user, agent);
    }

}
