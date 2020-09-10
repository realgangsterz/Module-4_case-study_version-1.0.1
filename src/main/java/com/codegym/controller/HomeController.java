package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.model.ProductColor;
import com.codegym.model.ProductSize;
import com.codegym.service.CategoryService;
import com.codegym.service.ProductColorService;
import com.codegym.service.ProductService;
import com.codegym.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private ProductColorService productColorService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page, Model model) {
        Page<Product> products;
        Iterable<Category> categories = categoryService.findAll();
        Iterable<ProductSize> productSizes = productSizeService.findAll();
        Iterable<ProductColor> productColors = productColorService.findAll();
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 3 );
        if (s.isPresent()) {
            products = productService.findAllByNameContaining( s.get(), pageable );
        } else {
            products = productService.findAll( pageable );
        }

        model.addAttribute( "products", products );
        model.addAttribute( "categories", categories );
        model.addAttribute( "productSizes", productSizes );
        model.addAttribute( "productColors", productColors);
        return "home";
    }

    @GetMapping("category/{categoryId}")
    public ModelAndView viewCategory(@PathVariable("categoryId") Long id, Pageable pageable, @RequestParam("page") Optional<String> page) {
        Iterable<Category> categories = categoryService.findAll();
        int t = 0;
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 3 );
        Category category = categoryService.findById( id );
        Page<Product> products = productService.findAllByCategory( category, pageable );

        ModelAndView modelAndView = new ModelAndView( "categoryView" );

        modelAndView.addObject( "products", products );
        modelAndView.addObject( "categories", categories );
        modelAndView.addObject( "category", category );
        return modelAndView;
    }

    @GetMapping("/product-details/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById( id );
        model.addAttribute( "productDetail", product );
        return "product_details";
    }

    @GetMapping("blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("shop")
    public String shop() {
        return "shop";
    }

    @GetMapping("blog/details")
    public String blogDetail() {
        return "blog_details";
    }
    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("elements")
    public String elements() {
        return "elements";
    }

    @GetMapping("main")
    public String main() {
        return "main";
    }
}
