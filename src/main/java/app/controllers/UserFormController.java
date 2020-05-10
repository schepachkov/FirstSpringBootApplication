package app.controllers;

import app.service.UserFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import app.entity.User;

@Controller
public class UserFormController {

    @Autowired
    private UserFormService userFormService;

    @GetMapping("/user")
    public String userForm(Model model){
        return userFormService.userForm(model);
    }

    @PostMapping("/user")
    public String userSubmit(Model model, @ModelAttribute User user){
        return userFormService.userSubmit(model, user);
    }
}
