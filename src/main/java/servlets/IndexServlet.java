package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import lombok.SneakyThrows;
import models.Feedback;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.AuthRepository;
import repositories.AuthRepositoryImpl;
import repositories.BasketRepositoryImpl;
import repositories.ProductRepositoryImpl;
import services.ProductServiceImpl;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    ProductServiceImpl productService;

    @Override
    @SneakyThrows
    public void init() throws ServletException {
        Class.forName("org.postgresql.Driver");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("annatugb17");
        dataSource.setUrl("jdbc:postgresql://localhost:5434/TDC_table");
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl(dataSource);
        BasketRepositoryImpl basketRepository = new BasketRepositoryImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryImpl(dataSource);
        productService = new ProductServiceImpl(productRepository, authRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Feedback> showAllFeedback = productService.showFeedback();
        request.setAttribute("feedback", showAllFeedback);
        request.getRequestDispatcher("ftl/index.ftl").forward(request, response);

    }
}