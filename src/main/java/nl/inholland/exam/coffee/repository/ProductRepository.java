package nl.inholland.exam.coffee.repository;

import nl.inholland.exam.coffee.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    // instead of relying on the default findAll method that returns an iterable,
    // we return a list.
    List<Product> findAll();
}
