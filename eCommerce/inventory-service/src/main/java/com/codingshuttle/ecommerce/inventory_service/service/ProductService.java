package com.codingshuttle.ecommerce.inventory_service.service;

import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDTO;
import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestItemDTO;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDTO;
import com.codingshuttle.ecommerce.inventory_service.entity.Product;
import com.codingshuttle.ecommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService
{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDTO> getAllInventory()
    {
        log.info("Getting all inventory items ");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                .map(product -> modelMapper.map(product , ProductDTO.class))
                .toList();
    }

    public ProductDTO getProductById(Long id)
    {
        log.info("Getting product by id {}", id);

        Optional<Product> product =  productRepository.findById(id);
        return product.map(product1 -> modelMapper.map(product1 , ProductDTO.class))
                .orElseThrow(()-> new RuntimeException("Not Found !"));
    }

    public Double reduceStock(OrderRequestDTO orderRequestDTO)
    {
        Double totalPrice = 0.0;

        for(OrderRequestItemDTO orderRequestItemDTO : orderRequestDTO.getItems())
        {
            Integer quantity = orderRequestItemDTO.getQuantity();
            Long productId = orderRequestItemDTO.getProductId();

//            check if the product exists in db
            Product product = productRepository.findById(productId)
                    .orElseThrow(()-> new RuntimeException("Product Not Found with id "+productId));

//            check if stock is not sufficient
            if(quantity > product.getStock())
            {
                throw new RuntimeException("Stock Exceeded");
            }

//            reduce stock
            product.setStock(product.getStock()-quantity);
            productRepository.save(product);

//            calculate price
            totalPrice = totalPrice + quantity*product.getPrice();

        }
        return   totalPrice;
    }
}
