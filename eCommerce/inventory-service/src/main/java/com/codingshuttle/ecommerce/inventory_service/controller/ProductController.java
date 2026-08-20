package com.codingshuttle.ecommerce.inventory_service.controller;

import com.codingshuttle.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.codingshuttle.ecommerce.inventory_service.config.FeaturesEnablesConfig;
import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDTO;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDTO;
import com.codingshuttle.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class ProductController
{
    private final ProductService productService;
    private final DiscoveryClient discoveryClient; //  import from cloud
    private final RestClient restClient;        // to make 3rd part api calls , and fetch apis
    private final OrdersFeignClient ordersFeignClient;
    private final FeaturesEnablesConfig featuresEnablesConfig;


    @Value("${my.variable}")
    private String myVariable;

    @GetMapping("/config/myvariable")
    public String checkFeatures()
    {
        if (featuresEnablesConfig.isUserTrackingUnabled())
        {
            return "user tracking unabled : " + myVariable;
        }
        else
        {
            return "user tracking disabled : " + myVariable;
        }
    }

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

    @PutMapping("/reduce-stock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDTO orderRequestDTO)
    {
        Double totalPrice = productService.reduceStock(orderRequestDTO);
        return ResponseEntity.ok(totalPrice);
    }

}
