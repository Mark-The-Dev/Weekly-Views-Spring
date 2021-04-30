package com.DevMark.Weeks_View.repositories;

import com.DevMark.Weeks_View.domain.User;
import com.DevMark.Weeks_View.exceptions.WvAuthException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO WV_USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD)" +
            "VALUES(NEXTVAL('WV_USERS-SEQ'), ?, ?, ?, ?)";

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws WvAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws WvAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return null;
    }
}
