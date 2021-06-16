package ru.job4j.grabber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
                    );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    @Override
    public void save(Post post) {
        String sql = "insert into post values (?,?,?,?)";
        try(PreparedStatement preparedStatement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, post.getName());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getLink());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            preparedStatement.execute();
            try (ResultSet key = preparedStatement.getGeneratedKeys()) {
                if (key.next()) {
                    post.setId(key.getInt(1));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> allPost = new ArrayList<>();
        String sql = "select * from post";
        try (Statement statement = cnn.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Post post = getPostFromResultSet(resultSet);
                    allPost.add(post);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return allPost;
    }

    @Override
    public Post findById(String id) {
        Post post = null;
        String sql = "select * from post where id = ?";
        try (PreparedStatement preparedStatement = cnn.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    post = getPostFromResultSet(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return post;
    }

    private Post getPostFromResultSet(ResultSet resultSet) throws SQLException {
        return new Post(
                resultSet.getInt(1), // id
                resultSet.getString(2), // name
                resultSet.getString(3), // text
                resultSet.getString(4), //link
                resultSet.getTimestamp(4).toLocalDateTime() // created
        );
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("database.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Wrong file");
        }
        PsqlStore store = new PsqlStore(config);
        store.save(new Post("name", "text", "link", LocalDateTime.now()));
        System.out.println(store.findById("1"));
        store.save(new Post("name1", "text", "link1", LocalDateTime.now().minusDays(1)));
        System.out.println(store.getAll());
    }
}
