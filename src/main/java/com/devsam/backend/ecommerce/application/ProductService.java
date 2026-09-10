package com.devsam.backend.ecommerce.application;

import com.devsam.backend.ecommerce.domain.model.Product;
import com.devsam.backend.ecommerce.domain.port.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// CRUD de Product
@Slf4j
public class ProductService {
    private final IProductRepository iProductRepository;
    private final UploadFile uploadFile;

    // Constructor de ProductService
    public ProductService(IProductRepository iProductRepository, UploadFile uploadFile) {
        this.iProductRepository = iProductRepository;
        this.uploadFile = uploadFile;
    }

    // Implementación de save() + Añadimos fichero de imagen
    public Product save(Product product, MultipartFile multipartFile) throws IOException {

        if (product.getId() != null && product.getId() != 0){ // Modificamos un producto que existe
            if (multipartFile == null) { // No se incluye imagen
                product.setUrlImage(product.getUrlImage());
            } else {
                // Obtengo el nombre del fichero de la imagen
                String name = product.getUrlImage().substring(29);
                log.info("El nombre del fichero de imagen a borrar: {}", name);

                // Pregunto que no sea default.jpg para borrar imagen
                if (!name.equals("default.jpg")) {
                    uploadFile.delete(name);
                }

                // Incluimos imagen nueva a disco y producto
                product.setUrlImage(uploadFile.upload(multipartFile));
            }
        } else {  //nuevo producto, incluimos la imagen a disco y producto
            product.setUrlImage(uploadFile.upload(multipartFile));
        }

        return this.iProductRepository.save(product);
    }

    // Implementacón de findAll()
    public Iterable<Product> findAll() {

        return this.iProductRepository.findAll();
    }

    // Implementación de findById()
    public Product findById(Integer id) {

        return this.iProductRepository.findById(id);
    }

    // Implementación de deleteById()
    public void deleteById(Integer id) {
        // Busco los datos del producto a borrar
        Product product = findById(id);

        // Obtengo el nombre del fichero de la imagen
        String name = product.getUrlImage().substring(29);
        log.info("El nombre del fichero de imagen a borrar: {}", name);

        // Pregunto que no sea default.jpg para borrar imagen
        if (!name.equals("default.jpg")) {
            uploadFile.delete(name);
        }

        // Acción de borrar fsicamente el producto
        this.iProductRepository.deleteById(id);
    }

}
