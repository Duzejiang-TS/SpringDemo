package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("showUser")
    public String selectUser(int id, Model model){
        User user = userService.selectUser(id);
        System.out.println(user);
        model.addAttribute("user",user);
        return "show";
    }
}