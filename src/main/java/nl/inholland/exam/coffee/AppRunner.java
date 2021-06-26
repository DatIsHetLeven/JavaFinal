package nl.inholland.exam.coffee;

import nl.inholland.exam.coffee.model.Brand;
import nl.inholland.exam.coffee.model.Product;
import nl.inholland.exam.coffee.repository.BrandRepository;
import nl.inholland.exam.coffee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // First add all the brands.
        var brandNames = Arrays.asList("Lavazza", "Segafredo", "Boss Coffee");
        var brandMap = new HashMap<String, Brand>();

        for (String brandName : brandNames) {
            var brand = new Brand();
            brand.setName(brandName);
            brand.setProducts(new ArrayList<>());
            var savedBrand = brandRepository.save(brand);
            brandMap.put(brandName, savedBrand);
        }

        // next add the products.
        var product1 = new Product();
        product1.setName("Espresso Italiano Clasico");
        product1.setBrand(brandMap.get("Lavazza"));
        product1.setPrice(2.40);
        product1.setStock(100);


        var product2 = new Product();
        product2.setName("Espresso Barista Perfetto");
        product2.setBrand(brandMap.get("Lavazza"));
        product2.setPrice(2.50);
        product2.setStock(75);

        var product3 = new Product();
        product3.setName("Cold Cafe Vanilla");
        product3.setBrand(brandMap.get("Boss Coffee"));
        product3.setPrice(1.12);
        product3.setStock(250);

        var product4 = new Product();
        product4.setName("Cold Black Coffee");
        product4.setBrand(brandMap.get("Boss Coffee"));
        product4.setPrice(1.12);
        product4.setStock(250);

        var product5 = new Product();
        product5.setName("Intermezzo");
        product5.setBrand(brandMap.get("Segafredo"));
        product5.setPrice(0.90);
        product5.setStock(125);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
    }


}
