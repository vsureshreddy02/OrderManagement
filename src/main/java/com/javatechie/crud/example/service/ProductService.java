package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.APIResponseDto;
import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }



    public APIResponseDto getProductById(int id) {
        Product product=repository.findById(id).orElse(null);
        APIResponseDto apiResponseDto=new APIResponseDto();
        apiResponseDto.setProduct(product);
        return apiResponseDto;
    }

    //@Scheduled(cron = "0/15 * * * * *")
    public List<Product> getProducts() {
        List<Product> product=repository.findAll();
        System.out.println("fetch service call in " + new Date().toString());
        System.out.println("no of record fetched : " + product.size());
        return product;
    }
    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }


}
