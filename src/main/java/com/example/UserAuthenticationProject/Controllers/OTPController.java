package com.example.UserAuthenticationProject.Controllers;

import com.example.UserAuthenticationProject.Exceptions.OTPException;
import com.example.UserAuthenticationProject.Exceptions.UserProfileNotFoundException;
import com.example.UserAuthenticationProject.Service.JwtService;
import com.example.UserAuthenticationProject.Service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestParam String username) {
        try {
            otpService.sendOTP(username);
            return ResponseEntity.ok("OTP sent successfully");
        } catch (OTPException | UserProfileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PostMapping("/validate")
//    public ResponseEntity<String> validateOTP(@RequestParam String username, @RequestParam String otp) {
//        try {
//            otpService.validateOTP(username, otp);
//            return ResponseEntity.ok("OTP validated successfully");
//        } catch (OTPException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOTP(@RequestParam String username, @RequestParam String otp) {
        try {
            otpService.validateOTP(username, otp);

            // Generate a JWT on successful validation
            String token = jwtService.generateToken(username);

            return ResponseEntity.ok("OTP validated successfully. Token: " + token);
        } catch (OTPException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
