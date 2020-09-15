package com.codegym.controller.home;

import com.codegym.model.*;
import com.codegym.model.cart.Cart;
import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductColor;
import com.codegym.model.product.ProductSize;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
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
    @Autowired
    private Product_ProductSizeService product_productSizeService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam("s") Optional<String> s, Pageable pageable, @RequestParam("page") Optional<String> page, Model model, HttpSession session) {
        Page<Product> products;
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
        model.addAttribute( "productSizes", productSizes );
        model.addAttribute( "productColors", productColors );
        model.addAttribute( "size", cartItems.size() );
        return "home";
    }


    @GetMapping("category/{categoryId}")
    public ModelAndView viewCategory(@PathVariable("categoryId") Long id, Pageable pageable,HttpSession session, @RequestParam("page") Optional<String> page) {
        int t = 0;
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        if (page.isPresent()) {
            t = Integer.parseInt( page.get() );
        }
        pageable = new PageRequest( t, 4 );
        Category category = categoryService.findById( id );
        Page<Product> products = productService.findAllByCategory( category, pageable );

        ModelAndView modelAndView = new ModelAndView( "categoryView" );
        modelAndView.addObject( "size", cartItems.size() );
        modelAndView.addObject( "products", products );

        modelAndView.addObject( "category", category );
        return modelAndView;
    }


    @GetMapping("/product-details/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        Product product = productService.findById( id );
        Iterable<Product_ProductSize> product_productSizes = product_productSizeService.findAllByProduct( product );
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        model.addAttribute( "productDetail", product );
        model.addAttribute( "product_productSizes", product_productSizes );
        model.addAttribute( "size", cartItems.size() );

        return "product_details";
    }



    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public String viewCart(HttpSession session, @RequestParam("productId")Long productId,
                           @RequestParam("size") String size,  Model model) {

        Product product = productService.findById( productId );
        ProductSize productSize = productSizeService.findBySize( size );
        Iterable<Product_ProductSize> product_productSizes = product_productSizeService.findAllByProduct( product );
        Product_ProductSize product_productSize = product_productSizeService.findAllByProductAndProductSize( product, productSize );
        String key = String.valueOf( productId ) +  String.valueOf( productSize.getSizeId() );

        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        if (product_productSize != null) {
            Cart cart;
            if (cartItems.containsKey( Long.valueOf( key ) )) {
                cart = cartItems.get( Long.valueOf( key ) );
                cart.setProduct_productSize( product_productSize );
                cart.setQuantity( cart.getQuantity() + 1 );
            } else {
                cart = new Cart();
                cart.setProduct_productSize( product_productSize );
                cart.setQuantity( 1 );
            }
            cartItems.put( Long.valueOf( key ), cart );
        }

        model.addAttribute( "productDetail", product );
        model.addAttribute( "size", cartItems.size() );
        session.setAttribute( "myCartItems", cartItems );
        session.setAttribute( "myCartTotal", totalPrice( cartItems ) );
        model.addAttribute( "product_productSizes", product_productSizes );
        return "product_details";
    }

    public double totalPrice(HashMap<Long, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Long, Cart> list : cartItems.entrySet()) {
            count += list.getValue().getProduct_productSize().getProduct().getProductPrice() * list.getValue().getQuantity();
        }
        return count;
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

    @GetMapping("order")
    public String main() {
        return "/order/orderView";
    }
}
