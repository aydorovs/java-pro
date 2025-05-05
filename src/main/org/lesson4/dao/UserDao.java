package org.lesson4.dao;

import org.lesson4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private final DataSource dataSource;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User create(User user) {
        String sql = "INSERT INTO users (username) VALUES (?) RETURNING id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getLong("id"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create user by SQLException", e);
        }

        throw new RuntimeException("Failed to create user");
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("username")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find user by id", e);
        }
        return null;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all users", e);
        }
        return users;
    }

    public void update(User user) {
        String sql = "UPDATE users SET username = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setLong(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}