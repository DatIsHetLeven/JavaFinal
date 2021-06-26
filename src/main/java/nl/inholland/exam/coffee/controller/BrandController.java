package nl.inholland.exam.coffee.controller;

import nl.inholland.exam.coffee.dto.BrandDTO;
import nl.inholland.exam.coffee.model.Brand;
import nl.inholland.exam.coffee.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity<String> addBrand(@RequestBody  BrandDTO brandDTO){
        // the service will throw an IllegalArgumentException
        // if a duplicate brand name is detected. We handle the error by sending a 400 status code.
        try {
            brandService.addBrand(brandDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Brand> getBrands(){
        return brandService.getBrands();
    }

}
