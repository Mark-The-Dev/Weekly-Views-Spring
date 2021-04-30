package com.DevMark.Weeks_View.services;

import com.DevMark.Weeks_View.domain.User;
import com.DevMark.Weeks_View.exceptions.WvAuthException;

import javax.security.auth.message.AuthException;

public interface UserService {


    User validateUser(String email, String password) throws WvAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws WvAuthException;

}
