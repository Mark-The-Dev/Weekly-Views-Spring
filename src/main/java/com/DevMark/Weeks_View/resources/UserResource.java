package com.DevMark.Weeks_View.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    // simple post test
//    @PostMapping("/register")
//    public String registerUser(@RequestBody Map<String, Object> userMap){
//        String firstName = (String) userMap.get("first_name");
//        String lastName = (String) userMap.get("last_name");
//        String email = (String) userMap.get("email");
//        String password = (String) userMap.get("password");
//
//        return firstName + ", " + lastName + ", " + email +", " + password;
//    }



}