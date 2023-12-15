package com.example.DoItBackendProject.mapper;

import com.example.DoItBackendProject.model.NewUserRequest;
import com.example.DoItBackendProject.model.User;
import com.example.DoItBackendProject.model.UserDto;
import com.example.DoItBackendProject.model.UserShortDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(NewUserRequest newUserRequest) {
        return new User(null, newUserRequest.getEmail(), newUserRequest.getName(), false);
    }

    public UserDto mapFromUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public UserShortDto mapToShortDto(User user) {
        return new UserShortDto(user.getId(), user.getName());
    }
}
