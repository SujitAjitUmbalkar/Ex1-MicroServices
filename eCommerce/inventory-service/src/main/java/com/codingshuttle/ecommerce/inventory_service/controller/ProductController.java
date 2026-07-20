package com.codingshuttle.ecommerce.inventory_service.controller;

import com.codingshuttle.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDTO;
import com.codingshuttle.ecommerce.inventory_service.entity.Product;
import com.codingshuttle.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController
{
    private final ProductService productService;
    private final DiscoveryClient discoveryClient; //  import from cloud
    private final RestClient restClient;        // to make 3rd part api calls , and fetch apis
//    OR
    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchorders")
    public String fetchOrderService()
    {
//        ServiceInstance orderService = discoveryClient.getInstances("order-service").getFirst();
//
//        String response = restClient.get()
//                .uri(orderService.getUri()+"/api/v1/orders/hello")
//                .retrieve()
//                .body(String.class);

        return ordersFeignClient.hello();
    }

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
