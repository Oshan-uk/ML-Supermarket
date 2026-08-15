package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.UserDTO;
import lk.ijse.mlsupermarket.repository.UserRepository;
import lk.ijse.mlsupermarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDTO) {

    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(long userId) {
        return null;
    }

    @Override
    public void updateUserStatus(long userId) {

    }
}
