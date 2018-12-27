package services;

import models.ProductName;

import java.util.List;

public interface BasketService {
    void add(Long userId, Long productId);
    void delete(Long userId, Long productId);
    List<ProductName> getAllByUserId(Long userId);
}
