package com.tsikalov.proj.service;

import com.tsikalov.proj.dto.request.UserRequest;
import com.tsikalov.proj.dto.response.UserResponse;
import com.tsikalov.proj.entity.User;
import com.tsikalov.proj.exception.HasDependenciesException;
import com.tsikalov.proj.exception.NoMatchesException;
import com.tsikalov.proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void save(UserRequest request) {
        userRepository.save(userRequestToUser(null, request));
    }

    public List<UserResponse> findAll(String fieldName) {
        return userRepository.findAll(Sort.by(fieldName)).stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public void update(UserRequest request, Long id) {
        userRepository.save(userRequestToUser(findOne(id), request));
    }

    public User findOne(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoMatchesException("User with id " + id + " not exists"));
    }

    private User userRequestToUser(User user, UserRequest request) {
        if (user == null) {
            user = new User();
        }
        user.setNickname(request.getNickname());
        user.setMail(request.getMail());
        return user;
    }
}
