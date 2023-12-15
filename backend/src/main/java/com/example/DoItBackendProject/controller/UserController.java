package com.example.DoItBackendProject.controller;

import com.example.DoItBackendProject.model.NewUserRequest;
import com.example.DoItBackendProject.model.UserDto;
import com.example.DoItBackendProject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    Set<UserDto> getUsers(@RequestParam(required = false) List<Long> ids,
                          @RequestParam(defaultValue = "0") int from,
                          @RequestParam(defaultValue = "10") int size) {
        return userService.getUser(ids, from / size, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto addUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        return userService.addUser(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
