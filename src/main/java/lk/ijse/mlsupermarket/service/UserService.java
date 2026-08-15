package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.UserDTO;

import java.util.List;

public interface UserService {
    public void saveUser(UserDTO userDTO);
    public void updateUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    public UserDTO getUserById(long userId);
    public void updateUserStatus(long userId);
}
