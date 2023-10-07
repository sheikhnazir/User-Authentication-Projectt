package com.example.UserAuthenticationProject.Service;

import com.example.UserAuthenticationProject.Dto.addUserDto;
import com.example.UserAuthenticationProject.Exceptions.UserProfileNotFoundException;
import com.example.UserAuthenticationProject.Models.User;
import com.example.UserAuthenticationProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(addUserDto addUserDto) {

        User user = new User();
        user.setEmail(addUserDto.getEmail());
        user.setUsername(addUserDto.getUsername());
        userRepository.save(user);
        return "User has been added successfully...";
    }

    public User getUserProfile(String username) throws UserProfileNotFoundException {
        Optional<User> userProfile = userRepository.findByUsername(username);
        if (userProfile.isPresent()) {
            return userProfile.get();
        } else {
            throw new UserProfileNotFoundException("User profile not found for username: " + username);
        }
    }
}
