package com.tsikalov.proj.controller;

import com.tsikalov.proj.dto.request.UserRequest;
import com.tsikalov.proj.dto.response.UserResponse;
import com.tsikalov.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void save(@Valid @RequestBody UserRequest request) {
        userService.save(request);
    }

    @GetMapping
    public List<UserResponse> findAll(@RequestParam(defaultValue = "id") String fieldName) {
        return userService.findAll(fieldName);
    }

    @PutMapping
    public void update(@Valid @RequestBody UserRequest request, Long id) {
        userService.update(request, id);
    }
}
