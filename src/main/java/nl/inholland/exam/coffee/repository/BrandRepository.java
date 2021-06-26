package nl.inholland.exam.coffee.repository;

import nl.inholland.exam.coffee.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    boolean existsByName(String name);

    // instead of relying on the default findAll method that returns an iterable,
    // we return a list.
    List<Brand> findAll();
}
