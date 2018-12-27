package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Category {
    private Long id;
    private String image;
    private String title;
    private List<ProductName> products;
}
