package com.siit.class22project.controller;

import com.siit.class22project.model.ProductCreateDto;
import com.siit.class22project.model.ProductReturnDto;
import com.siit.class22project.model.ProductUpdateDto;
import com.siit.class22project.service.ProductService;
import com.siit.class22project.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mvc")
@RequiredArgsConstructor
public class ProductMvcController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public String getProducts(Model model) {
        List<ProductReturnDto> productList = productService.getProducts(Pageable.ofSize(100));
        model.addAttribute("productList", productList);
        return "products";
    }

    @GetMapping("/delete")
    public String deleteProductById(@RequestParam Long id) {
        productService.deleteProductById(id);
        return "redirect:/mvc";
    }

    @GetMapping("/update")
    public String updateView(@RequestParam Long id, Model model) {
        ProductUpdateDto productUpdateDto = new ProductUpdateDto();
        productUpdateDto.setId(id);
        model.addAttribute("productUpdateDto", productUpdateDto);
        return "update";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute ProductUpdateDto productUpdateDto, Model model) {
        model.addAttribute("productUpdateDto", productUpdateDto);
        productService.updateProduct(productUpdateDto);
        return "redirect:/mvc";
    }

    @GetMapping("/create")
    public String createView(Model model) {
        model.addAttribute("productCreateDto", new ProductCreateDto());
        return "create";
    }

    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute ProductCreateDto productCreateDto, Model model) {
        productService.createProduct(productCreateDto);
        return "redirect:/mvc";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam Long id) {
        shoppingCartService.addToCart(id);
        return "redirect:/mvc?added=true";
    }

}
