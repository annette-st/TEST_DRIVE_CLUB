package services;

import forms.FeedbackForm;
import models.Category;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import models.ProductName;
import models.Feedback;
import repositories.AuthRepository;
import repositories.ProductRepositoryImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private PasswordEncoder encoder;

    private ProductRepositoryImpl productRepository;
    private AuthRepository authRepository;

    public ProductServiceImpl(ProductRepositoryImpl productRepository, AuthRepository authRepository) {
        this.productRepository = productRepository;
        this.authRepository = authRepository;
        this.encoder = new BCryptPasswordEncoder();
    }


    public List<ProductName> showProducts() {
        return productRepository.getAllProducts();
    }

    public List<Category> showCategories() {
        return productRepository.getAllCategories();
    }

    public List<ProductName> showProductsByCatId(Long categoryId) {
        return productRepository.getAllByCategoryId(categoryId);
    }

    public List<Feedback> showFeedback() {
        return productRepository.showAllFeedback();
    }

    public void insertFeedback(FeedbackForm form) {

                FeedbackForm feedback = FeedbackForm.builder()
                        .customer_id(form.getCustomer_id())
                        .content(form.getContent())
                        .build();
                productRepository.save(feedback);
            }


    public String get(Long productId) {
        return productRepository.get(productId);
    }

    public List<ProductName> addProductToUserBasket(String cookieValue, Long productId) {
        return null;
    }
}

