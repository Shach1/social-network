package ru.learning.socialmedia.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<UserFullInformation> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public UserFullInformation getUserById(@RequestParam("id") long id) {

        return userService.getUserById(id);
    }
}
