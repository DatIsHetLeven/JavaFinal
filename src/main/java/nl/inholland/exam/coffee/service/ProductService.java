package nl.inholland.exam.coffee.service;

import nl.inholland.exam.coffee.dto.ProductDTO;
import nl.inholland.exam.coffee.model.Product;
import nl.inholland.exam.coffee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product findProductById(long id){
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such name"));
    }

    public void updateProduct(ProductDTO productDTO){
        var product = findProductById(productDTO.getId());

        // if the price or stock is negative, throw an error.
        if (productDTO.getPrice() < 0 || productDTO.getStock() < 0){
            throw new IllegalArgumentException("Product price or stock cannot be less than 0");
        }
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        productRepository.save(product);
    }
}
