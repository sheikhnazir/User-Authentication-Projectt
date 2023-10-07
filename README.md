# User Authentication Project

This project is a simple user authentication system with OTP verification using Spring Boot , HTML and javascript.

## Overview

This project provides a basic user authentication system with the following features:

- User registration with a username and email.
- Sending OTP (One-Time Password) to registered email addresses.
- Validating OTP to authenticate users.
- JWT-based token generation upon successful OTP validation.

## Tech Stack

- Java Development Kit (JDK) installed
- Spring Boot and Spring Security configured
- MySQL database set up (or another database of your choice)
- SMTP email server for sending OTP emails
- Git (optional)

## API'S

Use the "Add User" form to register a user with a username and email.

Use the "Send OTP" form to send an OTP to the registered email.

Use the "Validate OTP" form to validate the OTP and generate a JWT token.

API Endpoints
/user/add (POST): Register a user with a username and email.
/otp/send (POST): Send an OTP to the registered email.
/otp/validate (POST): Validate the OTP and generate a JWT token upon success.

## The project has also basic frontend part to test all the api's.
