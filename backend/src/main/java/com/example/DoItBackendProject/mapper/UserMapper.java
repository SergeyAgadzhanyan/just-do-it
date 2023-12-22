package com.example.DoItBackendProject.mapper;

import com.example.DoItBackendProject.enums.Role;
import com.example.DoItBackendProject.model.NewUserRequest;
import com.example.DoItBackendProject.model.User;
import com.example.DoItBackendProject.model.UserDto;
import com.example.DoItBackendProject.model.UserShortDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class UserMapper {
    public User mapToUser(NewUserRequest newUserRequest) {
        return new User(null, newUserRequest.getEmail(), newUserRequest.getName(), Set.of(Role.USER));
    }

    public UserDto mapFromUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public UserShortDto mapToShortDto(User user) {
        return new UserShortDto(user.getId(), user.getName());
    }
}
