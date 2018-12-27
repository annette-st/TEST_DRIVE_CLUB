package repositories;

import forms.FeedbackForm;
import models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import models.ProductName;
import models.Feedback;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String SQL_READ_BY_ID =
            "SELECT title from product where id = ?";


    private static final String SQL_GET_ALL =
            "SELECT * FROM product";

    private static final String SQL_GET_ALL_CATEGORIES =
            "SELECT * FROM categories";

    private static final String SQL_GET_ALL_BY_CATEGORY_ID =
            "SELECT * FROM product WHERE category_id = ?";

    private static final String SQL_GET_ALL_FEEDBACK =
            "select first_name, content from customers c inner join feedback f on c.id=f.customer_id";

    private static final String SQL_INSERT =
            "insert into feedback (customer_id, content) values (?, ?)";


    public void save(FeedbackForm model) {
        jdbcTemplate.update(SQL_INSERT, model.getCustomer_id(), model.getContent());
    }


    private RowMapper<Feedback> rowMapperFeedback = new RowMapper<Feedback>() {
        @Override
        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Feedback.builder()
                    .first_name(rs.getString("first_name"))
                    .content((rs.getString("content")))
                    .build();
        }
    };


    private RowMapper<Category> rowMapperAllCategories = new RowMapper<Category>() {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Category.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
//                    .image(rs.getString("img"))
                    .build();
        }
    };

    private RowMapper<ProductName> rowMapperSearch = new RowMapper<ProductName>() {
        @Override
        public ProductName mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProductName.builder()
                    .title(rs.getString("title"))
                    .id(rs.getLong("id"))
                    .image(rs.getString("img"))
                    .model(rs.getString("model"))
                    .build();
        }
    };

    private RowMapper<ProductName> rowMapperAllProducts = new RowMapper<ProductName>() {
        @Override
        public ProductName mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProductName.builder()
                    .title(rs.getString("title"))
                    .id(rs.getLong("id"))
                    .image(rs.getString("img"))
                    .model(rs.getString("model"))
                    .number_of_places(rs.getInt("number_of_places"))
                    .price(rs.getInt("price"))
                    .build();
        }
    };

    private RowMapper<String> rowMapper = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("title");
        }
    };

    public List<ProductName> getAllProducts() {
        return jdbcTemplate.query(SQL_GET_ALL, rowMapperAllProducts);
    }

    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_GET_ALL_CATEGORIES, rowMapperAllCategories);
    }

    public String get(Long productId) {
        return jdbcTemplate.queryForObject(SQL_READ_BY_ID, rowMapper, productId);
    }

    public List<ProductName> getAllByCategoryId(Long categoryId) {
        return jdbcTemplate.query(SQL_GET_ALL_BY_CATEGORY_ID, rowMapperAllProducts, categoryId);
    }

    public List<Feedback> showAllFeedback() {
        return jdbcTemplate.query(SQL_GET_ALL_FEEDBACK, rowMapperFeedback);
    }


}
