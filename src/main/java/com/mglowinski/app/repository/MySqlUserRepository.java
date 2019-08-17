package com.mglowinski.app.repository;

import com.mglowinski.app.model.Address;
import com.mglowinski.app.model.Country;
import com.mglowinski.app.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class MySqlUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        String GET_USERS = "SELECT * FROM " +
                "users " +
                "INNER JOIN addresses ON users.addressId = addresses.id " +
                "INNER JOIN countries ON users.countryId = countries.id";
        return jdbcTemplate.query(GET_USERS, new UserMapper());
    }

    class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address(rs.getString("addressId"), rs.getString("street"),
                    rs.getString("postCode"));

            Country country = new Country(rs.getString("name"));

            return new User(rs.getString("id"), rs.getString("username"),
                    rs.getString("firstName"), rs.getString("lastName"),
                    country, address);
        }
    }
}
