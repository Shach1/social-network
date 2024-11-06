package ru.learning.socialmedia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learning.socialmedia.model.request.UserLogInRequest;
import ru.learning.socialmedia.model.response.UserLogInResponse;
import ru.learning.socialmedia.service.UserService;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LogInController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserLogInResponse> login(@RequestBody UserLogInRequest userLogInRequest) {
        return new ResponseEntity<>(
                userService.logInUser(userLogInRequest.getUsername(), userLogInRequest.getPasswordHash()),
                HttpStatusCode.valueOf(200));
    }

}
