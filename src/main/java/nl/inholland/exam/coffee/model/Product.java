package nl.inholland.exam.coffee.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @ManyToOne
    private Brand brand;

    @Column
    double price;

    @Column
    long stock;
}
