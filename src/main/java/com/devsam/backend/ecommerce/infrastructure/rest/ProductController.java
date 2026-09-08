package com.devsam.backend.ecommerce.infrastructure.rest;

import com.devsam.backend.ecommerce.application.ProductService;
import com.devsam.backend.ecommerce.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/admin/products")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    // Variable para usar el service
    private final ProductService productService;

    // Implementar el método save()
    @PostMapping
    public ResponseEntity<Product> save(@RequestParam("name") String name,
                                        @RequestParam("code") String code,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") BigDecimal price,
                                        @RequestParam("urlImage") String urlImage,
                                        @RequestParam("userId") Integer userId,
                                        @RequestParam("categoryId") Integer categoryId) {
        Product product = new Product();
        product.setName(name);
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        product.setUrlImage(urlImage);
        product.setUserId(userId);
        product.setCategoryId(categoryId);

        log.info("Nombre del producto creado: {}", product.getName());
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    // Implementar el método findAll()
    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {

        return ResponseEntity.ok(productService.findAll());
    }

    // Implementar el método findById()
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {

        return ResponseEntity.ok(productService.findById(id));
    }

    // Implementar el método deleteById()
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {

        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
