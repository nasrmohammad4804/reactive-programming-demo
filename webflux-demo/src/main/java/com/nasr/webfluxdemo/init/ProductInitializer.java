package com.nasr.webfluxdemo.init;

import com.nasr.webfluxdemo.domain.Product;
import com.nasr.webfluxdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductInitializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        productService.count()
                .subscribe(count -> {

                    if (count == 0)
                        createDefaultProduct();
                });
    }

    private void createDefaultProduct() {
        List<Product> products = List.of(
                new Product("tv LG", 32L, 12_000_000d),
                new Product("xiaomi g3", 24L, 6_700_000d),
                new Product("laptop asus", 5L, 31_000_000d),
                new Product("airpod xiaomi", 17L, 670_000d)
        );
        productService.saveAll(products)
                .subscribe(productDto -> {});
    }
}
