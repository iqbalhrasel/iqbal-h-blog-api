package com.pxc.blog_api.auth;

import com.pxc.blog_api.auth.dtos.LoginRequest;
import com.pxc.blog_api.auth.dtos.LoginResponse;
import com.pxc.blog_api.auth.dtos.SignupRequest;
import com.pxc.blog_api.auth.jwt.Jwt;
import com.pxc.blog_api.auth.jwt.JwtService;
import com.pxc.blog_api.auth.models.Token;
import com.pxc.blog_api.auth.repositories.TokenRepository;
import com.pxc.blog_api.blogs.models.Role;
import com.pxc.blog_api.auth.models.TokenType;
import com.pxc.blog_api.users.DuplicateUserException;
import com.pxc.blog_api.users.User;
import com.pxc.blog_api.users.UserMapper;
import com.pxc.blog_api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository, TokenRepository tokenRepository, AuthenticationManager authenticationManager, JwtService jwtService, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        invalidatePreviousAccessToken(user);
        saveAccessToken(accessToken, user);

        return new LoginResponse(accessToken, refreshToken);
    }

    public void signup(SignupRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new DuplicateUserException();

        var user = userMapper.toEntity(request);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public Jwt refreshAccessToken(String refreshToken) {
        Jwt jwt = jwtService.parseToken(refreshToken);
        if(jwt == null || jwt.isExpired())
            throw new BadCredentialsException("Invalid refresh token");

        var user = userRepository.findById(jwt.getUserId()).orElseThrow();

        invalidatePreviousAccessToken(user);

        var accessToken = jwtService.generateAccessToken(user);

        saveAccessToken(accessToken, user);

        return accessToken;
    }

    private void saveAccessToken(Jwt accessToken, User user) {
        var token = new Token(
                accessToken.toString(),
                TokenType.BEARER,
                false, user);
        tokenRepository.save(token);
    }

    private void invalidatePreviousAccessToken(User user) {
        var validTokens = tokenRepository.getAllValidTokensByUser(user.getId());
        if(validTokens.isEmpty())
            return;

        validTokens.forEach(t -> t.setRevoked(true));

        tokenRepository.saveAll(validTokens);
    }

    public void signupAdmin(SignupRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new DuplicateUserException();

        var user = userMapper.toEntity(request);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }
}
