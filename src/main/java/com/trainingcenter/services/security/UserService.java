package com.trainingcenter.services.security;

import com.trainingcenter.entities.security.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByUsername(String username);
}
