// JavaScript code for handling form submissions

document.getElementById("sendOTPForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const username = document.getElementById("otpUsername").value;
    sendOTP(username);
});

document.getElementById("validateOTPForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const username = document.getElementById("validateUsername").value;
    const otp = document.getElementById("otp").value;
    validateOTP(username, otp);
});

document.getElementById("addUserForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const username = document.getElementById("addUserUsername").value;
    const email = document.getElementById("email").value;
    addUser(username, email);
});

function sendOTP(username) {
    // Implementing AJAX or fetch to send OTP API
    // Use fetch to send a POST request
    fetch("/otp/send", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `username=${username}`,
    })
        .then((response) => response.text())
        .then((data) => {
            alert(data);
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

function validateOTP(username, otp) {
    // Implementing AJAX or fetch to validate OTP API
    // Use fetch to send a POST request
    fetch("/otp/validate", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `username=${username}&otp=${otp}`,
    })
        .then((response) => response.text())
        .then((data) => {
            alert(data);
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

function addUser(username, email) {
    // Implementing AJAX or fetch to add user API
    // Use fetch to send a POST request
    fetch("/user/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            username: username,
            email: email,
        }),
    })
        .then((response) => response.text())
        .then((data) => {
            alert(data);
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}
