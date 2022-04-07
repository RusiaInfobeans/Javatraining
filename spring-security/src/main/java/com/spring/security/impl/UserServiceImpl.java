package com.spring.security.impl;

import com.spring.security.model.User;
import com.spring.security.repo.UserRepository;
import com.spring.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository repo;
    @Override
    public Integer saveUser(User user) {
       return repo.save(user).getId();
    }
}
