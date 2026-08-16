package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.UserDTO;
import lk.ijse.mlsupermarket.entity.User;
import lk.ijse.mlsupermarket.repository.UserRepository;
import lk.ijse.mlsupermarket.service.UserService;
import lk.ijse.mlsupermarket.status.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        log.info("Execute saveUser() dto {}", userDTO);
        try {
            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setContact(userDTO.getContact());
            user.setUserRole(userDTO.getUserRole());
            user.setStatus(UserStatus.ACTIVE);

            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error in saveUser()");
            throw e;
        }
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        log.info("Execute updateUser()");
        try{
            Optional<User> optionalUser = userRepository.findById(userDTO.getUserId());

            if(optionalUser.isEmpty())throw new RuntimeException("Sorry, related User NOT FOUND!");

            User user = optionalUser.get();
            user.setUserName(userDTO.getUserName());
            user.setContact(userDTO.getContact());
            user.setUserRole(userDTO.getUserRole());

            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error in updateUser()");
            throw e;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Execute getAllUsers()");

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    null,
                    user.getContact(),
                    user.getUserRole(),
                    user.getStatus()
            );

            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    public UserDTO getUserById(long userId) {
        log.info("Executes getUserById()");

        try{
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) throw new RuntimeException("Sorry, Related User NOT Found!");

            User user = optionalUser.get();
            return new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    null,
                    user.getContact(),
                    user.getUserRole(),
                    user.getStatus()
            );

        }catch(Exception e){
            log.error("Error in getUserNyId()");
            throw e;

        }
    }

    @Override
    public void updateUserStatus(long userId) {
        log.info("Execute updateUserStatus()");

        try{
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isEmpty()) throw new RuntimeException("Sorry, related user in not found!");

            User user = optionalUser.get();
            user.setStatus(
                    user.getStatus() == UserStatus.ACTIVE ? UserStatus.INACTIVE : UserStatus.ACTIVE
            );

            userRepository.save(user);

        }catch(Exception e){
            log.error("Error in updateUserStatus()");
            throw e;
        }

    }
}
