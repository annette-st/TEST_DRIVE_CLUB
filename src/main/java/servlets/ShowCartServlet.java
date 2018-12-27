package servlets;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import models.ProductName;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.AuthRepository;
import repositories.AuthRepositoryImpl;
import repositories.BasketRepositoryImpl;
import repositories.ProductRepositoryImpl;
import services.BasketServiceImpl;
import services.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/showcart")
public class ShowCartServlet extends HttpServlet {

    ProductServiceImpl productService;
    BasketServiceImpl basketService;

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
        basketService = new BasketServiceImpl(basketRepository, authRepository);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));

        List<ProductName> showProductsById = basketService.getAllByUserId(userId);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(showProductsById));
    }
}
