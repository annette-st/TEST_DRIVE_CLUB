package servlets;

import forms.FeedbackForm;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repositories.*;
import services.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insertfeed")
public class InsertFeedbackServlet extends HttpServlet {
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
        request.getRequestDispatcher("ftl/choose-car.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long userId = null;

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("userId")) {
                userId = Long.valueOf(cookies[i].getValue());
            }
        }

        FeedbackForm form = FeedbackForm.builder()
                .customer_id(userId)
                .content(request.getParameter("your_fb"))
                .build();

        productService.insertFeedback(form);
        response.sendRedirect("/shop");
    }

}
