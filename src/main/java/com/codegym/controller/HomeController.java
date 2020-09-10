package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String index(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page, Model model){
        Page<Product> products;
        int t = 0;
        if(page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 4 );
        if(s.isPresent()) {
            products = productService.findAllByProductNameContaining( s.get(),pageable );
        } else {
            products = productService.findAll( pageable );
        }
        model.addAttribute( "products", products);
        return "index";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }

    @GetMapping("blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("blog/details")
    public String blogDetail(){
        return "blog_details";
    }



}
