package com.mglowinski.app.repository;

import com.mglowinski.app.model.Address;
import com.mglowinski.app.model.Country;
import com.mglowinski.app.model.User;
import com.mglowinski.app.model.UserAddressDto;
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

    public void fillData() {
        for (int i = 0; i < 1000; i++) {
            String QUERY = "INSERT INTO users (username, firstName, lastName, addressId, " +
                    "countryId) VALUES (?, ?, ?, ?, ?)";

            int addressId;
            int countryId;

            if (i % 2 == 0) {
                addressId = 1;
                countryId = 1;
            } else {
                addressId = 2;
                countryId = 2;
            }
            jdbcTemplate.update(
                    QUERY,
                    "Sample_User_" + i, "Sample_User_" + i, "Sample_User_" + i, addressId, countryId);
        }
    }

    public List<User> findAll() {
        String QUERY = "SELECT * FROM " +
                "users " +
                "INNER JOIN addresses ON users.addressId = addresses.id " +
                "INNER JOIN countries ON users.countryId = countries.id";
        return jdbcTemplate.query(QUERY, new UserMapper());
    }

    public List<User> findAllByPostCode(String postCode) {
        String QUERY = "SELECT * FROM " +
                "users " +
                "INNER JOIN addresses ON users.addressId = addresses.id " +
                "INNER JOIN countries ON users.countryId = countries.id " +
                "WHERE addresses.postCode = ?";
        return jdbcTemplate.query(QUERY, new UserMapper(), postCode);
    }

    public List<User> findAllByCountry(String country) {
        String QUERY = "SELECT * FROM " +
                "users " +
                "INNER JOIN addresses ON users.addressId = addresses.id " +
                "INNER JOIN countries ON users.countryId = countries.id " +
                "WHERE countries.name = ?";
        return jdbcTemplate.query(QUERY, new UserMapper(), country);
    }

    public List<User> findAllByPostCodeAndCountry(String postCode, String country) {
        String QUERY = "SELECT * FROM " +
                "users " +
                "INNER JOIN addresses ON users.addressId = addresses.id " +
                "INNER JOIN countries ON users.countryId = countries.id " +
                "WHERE addresses.postCode = ? " +
                "AND countries.name = ?";
        return jdbcTemplate.query(QUERY, new UserMapper(), postCode, country);
    }

    public List<UserAddressDto> findUsersAddresses() {
        String QUERY = "SELECT users.id, users.addressId, addresses.street, addresses.postCode FROM " +
                "users " +
                "INNER JOIN addresses ON users.addressId = addresses.id " +
                "INNER JOIN countries ON users.countryId = countries.id";
        return jdbcTemplate.query(QUERY, new UsersAddressesMapper());
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

    class UsersAddressesMapper implements RowMapper<UserAddressDto> {
        @Override
        public UserAddressDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address(rs.getString("addressId"), rs.getString("street"),
                    rs.getString("postCode"));

            return new UserAddressDto(rs.getString("id"), address);
        }
    }
}
