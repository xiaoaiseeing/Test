package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> all() {
        return userService.findAll();
    }

    @GetMapping("/findPage/{page}/{size}")
    public Page<User> findPage(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return userRepository.findAll(pageable);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        User u = userRepository.save(user);
        return (u != null) ? "success":"error";
    }
}
