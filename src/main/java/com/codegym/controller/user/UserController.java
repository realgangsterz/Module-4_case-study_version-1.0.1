//package com.codegym.controller.user;
//
//import com.codegym.model.User;
//import com.codegym.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
//    @GetMapping("/register")
//    public ModelAndView registerPage(){
//        User user = new User();
//        ModelAndView mv = new ModelAndView("user/register");
//        mv.addObject("user", user);
//        return mv;
//    }
//
//    @PostMapping("/register")
//    public ModelAndView PostRegisterPage(@ModelAttribute(value = "user") User user){
//        String password = passwordEncoder.encode(user.getPassword());
//        user.setPassword(password);
//        userService.save(user);
//        ModelAndView modelAndView = new ModelAndView("");
//        return ;
//    }
//}
