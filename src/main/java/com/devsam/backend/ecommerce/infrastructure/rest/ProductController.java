package com.devsam.backend.ecommerce.infrastructure.rest;

import com.devsam.backend.ecommerce.application.ProductService;
import com.devsam.backend.ecommerce.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<Product> save(@RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam("name") String name,
                                        @RequestParam("code") String code,
                                        @RequestParam("description") String description,
                                        @RequestParam("price") BigDecimal price,
                                        @RequestParam("urlImage") String urlImage,
                                        @RequestParam("userId") Integer userId,
                                        @RequestParam("categoryId") Integer categoryId,
                                        @RequestParam(value = "image", required = false)MultipartFile multipartFile
                                        )  throws IOException {
        System.out.println("RECIBO ");
        System.out.println("Id: " + id + " Name: " + name + " Code: " + code + " Description: " + description + " Url: " + urlImage + "");

        Product product = new Product();

        if (id != null && id != 0) {
            product.setId(id); // update
            System.out.println("El valor que asigno a id producto es: " + product.getId());
        } else {
            product.setId(null); // create
        }
        product.setName(name);
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        product.setUrlImage(urlImage);
        product.setUserId(userId);
        product.setCategoryId(categoryId);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Name: " + product.getName() + " Description: " + product.getDescription() + " Url: " + product.getUrlImage() + "");

        log.info("Nombre del producto creado: {}", product.getName());
        return new ResponseEntity<>(productService.save(product, multipartFile), HttpStatus.CREATED);
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
