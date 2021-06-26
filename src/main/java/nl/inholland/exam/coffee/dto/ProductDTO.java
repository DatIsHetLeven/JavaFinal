package nl.inholland.exam.coffee.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private long stock;
    private double price;
}
