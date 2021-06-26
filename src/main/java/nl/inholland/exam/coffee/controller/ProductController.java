package nl.inholland.exam.coffee.controller;

import nl.inholland.exam.coffee.dto.ProductDTO;
import nl.inholland.exam.coffee.dto.TotalStockDTO;
import nl.inholland.exam.coffee.model.Product;
import nl.inholland.exam.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO){
        // the service may throw an exception when updating the product.
        // we handle this by sending a 400 error
        try {
            productService.updateProduct(productDTO);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (IllegalArgumentException e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/totalstock")
    public TotalStockDTO getTotalStock(){
        var allProducts = productService.getProducts();

        // to get the total value, map each product to it's price, then get the sum.
        var totalValue = allProducts.stream().mapToDouble(Product::getPrice).sum();

        var totalStockDTO = new TotalStockDTO();
        totalStockDTO.setTotalStock(allProducts.size());
        totalStockDTO.setTotalValue(totalValue);
        return totalStockDTO;
    }
}
