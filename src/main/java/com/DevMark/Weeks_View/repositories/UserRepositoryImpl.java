package com.DevMark.Weeks_View.repositories;

import com.DevMark.Weeks_View.domain.User;
import com.DevMark.Weeks_View.exceptions.WvAuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

// repository implementation that handles data, redundant with hibernate / jpa but a good practice!

@Repository
public class UserRepositoryImpl implements UserRepository {

    // SQL strings
    private static final String SQL_CREATE = "INSERT INTO WV_USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) " +
            "VALUES(NEXTVAL('WV_USERS_SEQ'), ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM WV_USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD " +
            "FROM WV_USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "select user_id, first_name, last_name, email, password " +
            "from wv_users where email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    // create method used in user services.
    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws WvAuthException {

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        try {
            // creates keyholder to place in template update
            KeyHolder keyHolder = new GeneratedKeyHolder();

            // sends an update with a prepared statement
            // helps prevent sql injections and can call the sql statement without compiling.
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("USER_ID");

        } catch (Exception e){
            throw new WvAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws WvAuthException {
        try{
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);

            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new WvAuthException("Invalid email/password");
            return user;

        } catch (EmptyResultDataAccessException e){
            throw new WvAuthException("Invalid email/password");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
            rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });
}
