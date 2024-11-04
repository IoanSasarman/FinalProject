package com.siit.class22project.controller;

import com.siit.class22project.model.ProductShoppingCart;
import com.siit.class22project.service.ShoppingCartService;
import com.siit.class22project.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public String prepareCheckout(Model model) {
        List<ProductShoppingCart> productShoppingCartList =
                shoppingCartService.getCurrentUserShoppingCartProducts(UserUtil.getCurrentUserId());
        String currency = null;
        if (!productShoppingCartList.isEmpty()) {
            currency = productShoppingCartList.getFirst().getCurrency();
        }
        model.addAttribute("productShoppingCartList", productShoppingCartList);
        model.addAttribute("totalPrice", calculateTotalPrice(productShoppingCartList) > 0 ? calculateTotalPrice(productShoppingCartList) : 0);
        model.addAttribute("currency", currency != null ? currency : "USD");
        return "shoppingcart";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        shoppingCartService.deleteProductShoppingCartByCartItemId(id);
        return "redirect:/shoppingcart";
    }

    private double calculateTotalPrice(List<ProductShoppingCart> productShoppingCartList) {
        double totalPrice = 0;
        for (ProductShoppingCart productShoppingCart : productShoppingCartList) {
            totalPrice += productShoppingCart.getPrice();
        }
        return totalPrice;
    }

    @GetMapping("/clear")
    public String clear() {
        shoppingCartService.clearCurrentUserShoppingCart(UserUtil.getCurrentUserId());
        return "redirect:/shoppingcart?cleared=true";
    }
}
