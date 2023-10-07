package com.example.UserAuthenticationProject.Service;

import com.example.UserAuthenticationProject.Exceptions.OTPException;
import com.example.UserAuthenticationProject.Exceptions.UserProfileNotFoundException;
import com.example.UserAuthenticationProject.Models.OTP;
import com.example.UserAuthenticationProject.Models.User;
import com.example.UserAuthenticationProject.Repository.OTPRepository;
import com.example.UserAuthenticationProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OTPService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    public String sendOTP(String username) throws OTPException, UserProfileNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UserProfileNotFoundException("Invalid userName");
        }

        User user = optionalUser.get();

        // Generate a random OTP using generateRandomOTP function
        String otp = generateRandomOTP();

        // Save the OTP in the database with an expiry time (e.g. 15 minutes from now)
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(15);
        OTP otpEntity = new OTP();
        otpEntity.setUsername(username);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(expiryTime);
        otpRepository.save(otpEntity);

        // Create a SimpleMailMessage instance
        SimpleMailMessage simpleMessageMail = new SimpleMailMessage();

        String body = "Hi "+user.getUsername()+" ! \n"+
                "Your validation otp for Authentication is "+
                 otp +" and it is valid for 15 minutes only.";

        simpleMessageMail.setSubject("OTP Validation");
        simpleMessageMail.setFrom("backendtestmail4@gmail.com");
        simpleMessageMail.setText(body);
        simpleMessageMail.setTo(user.getEmail());

        emailSender.send(simpleMessageMail);

        return "OTP Sent successfully!!!";
    }

    private String generateRandomOTP() {

        //generating a 6-digit numeric OTP
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    public String validateOTP(String username, String otp) throws OTPException {
        // Find the OTP if present in the database for the given username
        Optional<OTP> otpRecord = otpRepository.findByUsernameAndOtp(username, otp);

        if (otpRecord.isPresent()) {
            // Check if the OTP is already expired
            LocalDateTime currentTime = LocalDateTime.now();
            if (currentTime.isAfter(otpRecord.get().getExpiryTime())) {
                throw new OTPException("OTP has expired");
            }
            // Delete the OTP record from the database after successful validation, to prevent for again verification
            otpRepository.delete(otpRecord.get());

            // OTP is valid, so i can return from the function
            return "OTP validated successfully...";
        } else {
            throw new OTPException("Invalid OTP");
        }
    }

}
