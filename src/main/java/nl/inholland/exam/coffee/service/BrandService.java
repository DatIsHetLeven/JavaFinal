package nl.inholland.exam.coffee.service;

import nl.inholland.exam.coffee.dto.BrandDTO;
import nl.inholland.exam.coffee.model.Brand;
import nl.inholland.exam.coffee.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getBrands(){
        return brandRepository.findAll();
    }

    public void addBrand(BrandDTO brandDTO) {
        // if duplicate name is detected, throw an error.
        if (brandRepository.existsByName(brandDTO.getName().trim())){
            throw new IllegalArgumentException("Duplicate brand name");
        }

        // create and store the brand.
        var brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setProducts(new ArrayList<>());
        brandRepository.save(brand);
    }
}
