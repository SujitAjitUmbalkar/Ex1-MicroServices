package com.codingshuttle.ecommerce.inventory_service.controller;

import com.codingshuttle.ecommerce.inventory_service.dto.ProductDTO;
import com.codingshuttle.ecommerce.inventory_service.entity.Product;
import com.codingshuttle.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController
{
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts()
    {
        return productService.getAllInventory();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }
}
