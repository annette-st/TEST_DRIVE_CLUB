package repositories;

import models.Category;
import models.Feedback;
import models.ProductName;

import java.util.List;

public interface ProductRepository {
    List<ProductName> getAllProducts();
    List<Category> getAllCategories();
    String get(Long productId);
    List<ProductName> getAllByCategoryId(Long categoryId);
    List<Feedback> showAllFeedback();
}
