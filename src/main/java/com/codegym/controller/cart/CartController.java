package com.codegym.controller.cart;

import com.codegym.model.cart.Cart;
import com.codegym.model.product.Category;
import com.codegym.service.CategoryService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String viewCart(HttpSession session, Model model) {

        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        model.addAttribute( "size", cartItems.size() );
        session.setAttribute( "myCartItems", cartItems );
        session.setAttribute( "myCartTotal", totalPrice( cartItems ) );
        return "/cart";
    }

    @RequestMapping(value = "/remove/{key}", method = RequestMethod.GET)
    public String cartRemove(HttpSession session, @PathVariable("key") Long key, Model model) {
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey( key )) {
            cartItems.remove( key );
        }
        model.addAttribute( "size", cartItems.size() );
        session.setAttribute( "myCartItems", cartItems );
        session.setAttribute( "myCartTotal", totalPrice( cartItems ) );

        return "/cart";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String cartEdit(HttpSession session, Model model) {
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );

        model.addAttribute( "size", cartItems.size() );
        session.setAttribute( "myCartItems", cartItems );
        session.setAttribute( "myCartTotal", totalPrice( cartItems ) );

        return "/cartEdit";
    }

    @RequestMapping(value = "/edit/{key}", method = RequestMethod.POST)
    public String saveCartEdit(HttpSession session,@PathVariable("key") Long key, Model model,
                               @RequestParam("quantity") Integer quantity) {
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute( "myCartItems" );
        model.addAttribute( "size", cartItems.size() );
        cartItems.get( key ).setQuantity( quantity );
        session.setAttribute( "myCartTotal", totalPrice( cartItems ) );
        return "/cartEdit";
    }

    public double totalPrice(HashMap<Long, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Long, Cart> list : cartItems.entrySet()) {
            count += list.getValue().getProduct_productSize().getProduct().getProductPrice() * list.getValue().getQuantity();
        }
        return count;
    }

}
