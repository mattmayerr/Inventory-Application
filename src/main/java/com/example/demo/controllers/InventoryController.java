package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class InventoryController {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ProductRepository productRepository;

    // Handle Buy Now for Parts
    @PostMapping("/buyPart")
    public String buyPart(@RequestParam("partId") Long partId) {
        Optional<Part> optionalPart = partRepository.findById(partId);

        if (optionalPart.isPresent()) {
            Part part = optionalPart.get();
            if (part.getInv() > 0) {
                part.setInv(part.getInv() - 1);
                partRepository.save(part);
                return "redirect:/purchaseSuccess";
            } else {
                return "redirect:/purchaseError";
            }
        } else {
            return "redirect:/purchaseError";
        }
    }

    // Handle Buy Now for Products
    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productId") Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productRepository.save(product);
                return "redirect:/purchaseSuccess";
            } else {
                return "redirect:/purchaseError";
            }
        } else {
            return "redirect:/purchaseError";
        }
    }

    // Success Page Mapping
    @GetMapping("/purchaseSuccess")
    public String purchaseSuccess() {
        return "purchaseSuccess"; // Points to purchaseSuccess.html
    }

    // Error Page Mapping
    @GetMapping("/purchaseError")
    public String purchaseError() {
        return "purchaseError";
    }
}
