package com.crud.crud.controller;

import com.crud.crud.model.Users;
import com.crud.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long userId){
        Users users = userService.getUserById(userId);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/add")
    public Users createUser(@RequestBody Users users) {
        return userService.createUser(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable(value = "id") Long userId, @RequestBody Users userDetails) {
        Users user = userService.updateUser(userId, userDetails);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long userId) {
        if (userService.deleteUser(userId)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
