package com.kpi.fellowtravelersfinder.repository;

import com.kpi.fellowtravelersfinder.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UsersRepository {
    JdbcTemplate jdbcTemplate;
    private final String SELECT_BY_ID = "SELECT id, email, username, phone FROM users WHERE id = ?";
    private final String SELECT_ALL = "SELECT id, email, username, phone FROM users";
    private final String INSERT = "INSERT INTO users (email, username, phone) VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE users SET email = ?, username = ?, phone = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM users WHERE id = ?";
    private final String SELECT_BY_USERNAME = "SELECT id, email, username, phone FROM users WHERE username = ?";

    public UsersRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }
    private User rowToUserMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        return new User(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("phone"));
    }
    public Optional<User> findById(int id) {
        List<User> users = jdbcTemplate.query(SELECT_BY_ID, this::rowToUserMapper, id);
        return users.size() == 0 ? Optional.empty() : Optional.of(users.get(0));
    }
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::rowToUserMapper);
    }
    public Optional<User> save(User user) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(INSERT, new String[]{"id"});
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPhone());
            return preparedStatement;
        }, generatedKeyHolder);
        return Optional.of(new User(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue(), user.getUsername(), user.getEmail(), user.getPhone()));
    }
    public Optional<User> update(User user) {
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(UPDATE);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setInt(4, user.getId());
            return preparedStatement;
        });
        return Optional.of(user);
    }
    public void delete(User user) {
        jdbcTemplate.update(DELETE, user.getId());
    }

    public User findByUsername(String username) {
        return jdbcTemplate.query(SELECT_BY_USERNAME, this::rowToUserMapper, username).get(0);
    }
}

