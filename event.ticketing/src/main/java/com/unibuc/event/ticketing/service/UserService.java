package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private void createUser() {

    }
}
