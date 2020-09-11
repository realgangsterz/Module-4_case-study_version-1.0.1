package com.codegym.controller.user;

import com.codegym.model.cart.Cart;
import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductColor;
import com.codegym.model.product.ProductSize;
import com.codegym.model.user.User;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private ProductColorService productColorService;
    @Autowired
    private UserService userService;

//    @Autowired
//    PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public ModelAndView registerPage(){
        User user = new User();
        ModelAndView mv = new ModelAndView("user/register");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView PostRegisterPage(@ModelAttribute(value = "user") User user){
//        String password = passwordEncoder.encode(user.getPassword());
//        user.setPassword(password);
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("user",user);
        modelAndView.addObject("message","Register Successfully !");
        return modelAndView;
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/home")
    public String userHome(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page, Model model, HttpSession session) {
        Page<Product> products;
        Iterable<Category> categories = categoryService.findAll();
        Iterable<ProductSize> productSizes = productSizeService.findAll();
        Iterable<ProductColor> productColors = productColorService.findAll();
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 4 );
        if (s.isPresent()) {
            products = productService.findAllByNameContaining( s.get(), pageable );
        } else {
            products = productService.findAll( pageable );
        }
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        model.addAttribute( "products", products );
        model.addAttribute( "categories", categories );
        model.addAttribute( "productSizes", productSizes );
        model.addAttribute( "productColors", productColors);
        model.addAttribute( "size", cartItems.size() );
        return "home";
    }
}
