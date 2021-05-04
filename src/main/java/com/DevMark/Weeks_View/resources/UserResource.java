package com.DevMark.Weeks_View.resources;

import com.DevMark.Weeks_View.domain.User;
import com.DevMark.Weeks_View.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;


     //simple post test
//    @PostMapping("/register")
//    public String registerUser(@RequestBody Map<String, Object> userMap){
//        String firstName = (String) userMap.get("first_name");
//        String lastName = (String) userMap.get("last_name");
//        String email = (String) userMap.get("email");
//        String password = (String) userMap.get("password");
//
//        return firstName + ", " + lastName + ", " + email +", " + password;
//    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String >> registerUser(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.registerUser(firstName, lastName, email, password);
        Map<String, String> map = new HashMap<>();
        // eventually will put jwt here
        map.put("message", "registered successfully");

        return new ResponseEntity<>(map, HttpStatus.OK);

    }



}
