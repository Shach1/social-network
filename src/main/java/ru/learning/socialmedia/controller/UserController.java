package ru.learning.socialmedia.controller;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learning.socialmedia.entity.UserFullInformation;
import ru.learning.socialmedia.model.response.UserInSearchResponse;
import ru.learning.socialmedia.model.request.UserRegistrationRequest;
import ru.learning.socialmedia.model.response.ErrorResponse;
import ru.learning.socialmedia.model.response.UserLogInResponse;
import ru.learning.socialmedia.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Пользовательский контроллер", description = "Позволяет взаимодействовать с пользователями")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<UserFullInformation> getAllUsers() {return userService.getAllUsers();}

    @PostMapping()
    public UserLogInResponse addUser(@RequestBody UserRegistrationRequest user) {return userService.createUser(user);}

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден", content = {
                    @Content(schema = @Schema(implementation = UserFullInformation.class))
            }),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/user")
    public ResponseEntity<?> getUserById(@RequestParam("id") long id) {
        UserFullInformation response = userService.getUserById(id);
        if (response == null) {
            return ResponseEntity.status(404).body(new ErrorResponse("User not found"));
        }
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Результаты поиска", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = UserInSearchResponse.class))),
            }),
            @ApiResponse(responseCode = "404", description = "Пользователей не найдено", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/searchByName")
    public ResponseEntity<?> searchUserByName(@RequestParam("name") String name) {
        List<UserInSearchResponse> userList = userService.searchUsersByName(name);
        if (userList.isEmpty())return ResponseEntity.status(404).body(new ErrorResponse("Users not found by name: " + name));
        return ResponseEntity.status(200).body(userList);
    }
}
