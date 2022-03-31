package com.trainingcenter.services.security;

import com.trainingcenter.entities.security.User;
import com.trainingcenter.repositories.security.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if (optionalUser.isEmpty()) {
//            throw new UsernameNotFoundException("User name: " + username + " not found");
//        } else {
            return optionalUser;
//        }
    }
}
