package com.example.DoItBackendProject.service;

import com.example.DoItBackendProject.exception.ConflictException;
import com.example.DoItBackendProject.exception.Messages;
import com.example.DoItBackendProject.exception.NotFoundException;
import com.example.DoItBackendProject.mapper.UserMapper;
import com.example.DoItBackendProject.model.NewUserRequest;
import com.example.DoItBackendProject.model.UserDto;
import com.example.DoItBackendProject.storage.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto addUser(NewUserRequest newUserRequest) {
        try {
            return userMapper.mapFromUser(userRepository.save(userMapper.mapToUser(newUserRequest)));
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(Messages.DB_CONFLICT.getMessage());
        }
    }

    public Set<UserDto> getUser(List<Long> ids, int from, int size) {
        Pageable pageableWithSort = PageRequest.of(from, size);
        if (ids == null || ids.isEmpty())
            return userRepository.findAll(pageableWithSort).getContent().stream().map(userMapper::mapFromUser).collect(Collectors.toCollection(LinkedHashSet::new));
        return userRepository.findAllByIdIn(ids, pageableWithSort).stream().map(userMapper::mapFromUser).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException(Messages.RESOURCE_NOT_FOUND.getMessage()));
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(userEmail)
                .map(u -> new User(
                        u.getName(),
                        u.getPassword(),
                        u.getRoles()))
                .orElseThrow(()
                        -> new UsernameNotFoundException("User don't exists"));
    }
}
