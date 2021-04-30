package com.DevMark.Weeks_View.repositories;

import com.DevMark.Weeks_View.domain.User;
import com.DevMark.Weeks_View.exceptions.WvAuthException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws WvAuthException;

    User findByEmailAndPassword(String email, String password) throws WvAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
