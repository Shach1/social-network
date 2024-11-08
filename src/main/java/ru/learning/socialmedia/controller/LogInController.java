package ru.learning.socialmedia.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learning.socialmedia.model.request.UserLogInRequest;
import ru.learning.socialmedia.model.response.ErrorResponse;
import ru.learning.socialmedia.model.response.UserLogInResponse;
import ru.learning.socialmedia.service.UserService;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LogInController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserLogInRequest userLogInRequest) {
        UserLogInResponse response = userService.logInUser(
                userLogInRequest.getUsername(),
                userLogInRequest.getPasswordHash());
        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Wrong username or Password"));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
