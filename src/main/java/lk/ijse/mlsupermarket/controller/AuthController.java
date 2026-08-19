package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.dto.AuthDTO;
import lk.ijse.mlsupermarket.dto.response.UserDataDTO;
import lk.ijse.mlsupermarket.entity.User;
import lk.ijse.mlsupermarket.repository.UserRepository;
import lk.ijse.mlsupermarket.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public CommonResponse login(@RequestBody AuthDTO authDTO){
        Optional<User> optionalUser = userRepository.findByUserName(authDTO.getUserName());
        if(optionalUser.isEmpty()) throw new RuntimeException("Sorry invalid username or password!");

        User user = optionalUser.get();
        if(!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())){
            throw new RuntimeException("Sorry, invalid username or password");
        }

        String token = jwtUtil.generateToken(user);
        UserDataDTO data = new UserDataDTO(user.getUserId(), token, user.getUserRole().name());

        return new CommonResponse(ResponseCode.OPERATION_SUCCESS,data,"Login Successful!");
    }
}
