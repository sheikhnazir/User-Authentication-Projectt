package com.example.UserAuthenticationProject.Controllers;

import com.example.UserAuthenticationProject.Dto.addUserDto;
import com.example.UserAuthenticationProject.Exceptions.UserProfileNotFoundException;
import com.example.UserAuthenticationProject.Models.User;
import com.example.UserAuthenticationProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody addUserDto user){

        try{
            String result = userService.addUser(user);
            return result;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserProfile(@PathVariable("username") String username) {
        try {
            User userProfile = userService.getUserProfile(username);
            return ResponseEntity.ok(userProfile);
        } catch (UserProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
