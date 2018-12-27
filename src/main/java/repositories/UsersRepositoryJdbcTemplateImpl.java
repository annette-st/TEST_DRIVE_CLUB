package repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_USER_BY_ID =
            "select * from customers where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_USERS =
            "select * from customers";

    //language=SQL
    private static final String SQL_INSERT =
            "insert into customers (first_name, last_name, email, hash_password) values (?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL =
            "select * from customers where email = ?";

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .hashPassword(resultSet.getString("hash_password"))
            .build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, userRowMapper);
    }

    @Override
    public User find(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID,
                userRowMapper, new Object[]{id});
    }

    @Override
    public void save(User model) {
        jdbcTemplate.update(SQL_INSERT, model.getFirstName(), model.getLastName(), model.getEmail(), model.getHashPassword());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
