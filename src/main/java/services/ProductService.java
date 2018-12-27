package services;

import forms.FeedbackForm;
import models.Category;
import models.Feedback;
import models.ProductName;

import java.util.List;

public interface ProductService {
    List<ProductName> showProducts();
    List<Category> showCategories();
    List<ProductName> showProductsByCatId(Long categoryId);
    List<Feedback> showFeedback();
    void insertFeedback(FeedbackForm form);
    String get(Long productId);
    List<ProductName> addProductToUserBasket(String cookieValue, Long productId);
}
