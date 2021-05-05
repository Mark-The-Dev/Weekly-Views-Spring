package com.DevMark.Weeks_View.resources;

import com.DevMark.Weeks_View.Constants;
import com.DevMark.Weeks_View.domain.User;
import com.DevMark.Weeks_View.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.validateUser(email, password);
        Map<String, String> map = new HashMap<>();
        // will later return JWT token!
        map.put("message", "LoggedIn successfully");

        return new ResponseEntity<>(map, HttpStatus.OK);

    };


     //simple post test example using a string response
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


    private Map<String, String> generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
