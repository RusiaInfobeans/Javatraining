package com.spring.security.controller;

import com.spring.security.model.User;
import com.spring.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){
       Integer id=service.saveUser(user);
       String body="user'"+id+"' saved";
       return ResponseEntity.ok(body);
       //return new ResponseEntity<String>(body, HttpStatus.OK);
    }
}
