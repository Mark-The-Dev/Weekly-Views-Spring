package com.DevMark.Weeks_View.repositories;

import com.DevMark.Weeks_View.domain.User;
import com.DevMark.Weeks_View.exceptions.WvAuthException;

public class UserRepositoryImpl implements UserRepository {
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
