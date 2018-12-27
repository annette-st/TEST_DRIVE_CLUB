package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ProductName {
    private Long id;
    private String title;
    private String image;
    private String model;
    private int number_of_places;
    private int price;
}

