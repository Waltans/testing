package com.urfu.testing.application.serviceImpl;

import com.urfu.testing.application.services.UserService;
import com.urfu.testing.domain.User;
import com.urfu.testing.extern.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public void updateUser(UUID id, String username, String password) {
        this.userRepository.findById(id).ifPresentOrElse(totalUser -> {
            if (username != null) totalUser.setUsername(username);
            if (password != null) {
                totalUser.setPassword(bCryptPasswordEncoder.encode(password));
            }
        }, () -> {
            throw new NoSuchElementException();
        });
    }


    @Override
    public void deleteUser(UUID uuid) {
        this.userRepository.findById(uuid).ifPresent(this.userRepository::delete);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
