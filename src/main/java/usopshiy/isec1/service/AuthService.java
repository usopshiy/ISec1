package usopshiy.isec1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import usopshiy.isec1.dto.CredentialsDTO;
import usopshiy.isec1.entity.User;
import usopshiy.isec1.repository.UserRepository;
import usopshiy.isec1.utils.JWTUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    public String register(CredentialsDTO credentialsDto) {
        Optional<User> user = userRepository.findById(credentialsDto.login());
        if (user.isPresent())
            throw new RuntimeException("User with name " + credentialsDto.login() + " already exists!");

        User userModel = new User(
                HtmlUtils.htmlEscape(credentialsDto.login()),
                passwordEncoder.encode(credentialsDto.password())
        );
        userRepository.save(userModel);
        return jwtUtils.generateJwtToken(userModel.getLogin());
    }

    public String login(CredentialsDTO credentialsDto) {
        User user = userRepository.findById(credentialsDto.login())
                .orElseThrow(() -> new RuntimeException("User with name " + credentialsDto.login() + " not found!"));
        if (!passwordEncoder.matches(credentialsDto.password(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password!");
        }
        return jwtUtils.generateJwtToken(user.getLogin());
    }
}
