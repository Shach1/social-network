package ru.learning.socialmedia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{id}")
    public UserFullInformation getUserById(@PathVariable() long id) {
        return userService.getUserById(id);
    }
}
