package org.launchcode.controllers;

import org.launchcode.data.UserData;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }
    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute
    User user, String verify) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        if (user.getPassword().equals(verify)){
            UserData.add(user);
            model.addAttribute("users", UserData.getAll());
            model.addAttribute("welcome", "Welcome, " +
                    user.getUsername() + "!");
            return "user/index";
        } else {
            model.addAttribute("error", "Passwords must match!");
            return "user/add";
        }
    }
}
