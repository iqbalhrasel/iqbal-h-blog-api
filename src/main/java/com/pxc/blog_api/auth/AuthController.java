package com.pxc.blog_api.auth;

import com.pxc.blog_api.auth.dtos.JwtResponse;
import com.pxc.blog_api.auth.dtos.LoginRequest;
import com.pxc.blog_api.auth.dtos.LoginResponse;
import com.pxc.blog_api.auth.dtos.SignupRequest;
import com.pxc.blog_api.auth.jwt.Jwt;
import com.pxc.blog_api.auth.jwt.JwtConfig;
import com.pxc.blog_api.users.DuplicateUserException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request,
                                             HttpServletResponse response){
        LoginResponse loginResponse = authService.login(request);

        String refreshToken = loginResponse.getRefreshToken().toString();

//        String cookieValue = "refreshToken=" + refreshToken +
//                "; HttpOnly" +
//                "; Path=/" +
//                "; Max-Age=" + jwtConfig.getRefreshTokenExpiration() +
//                "; SameSite=None" +
//                "; Secure=false";  // false for localhost, true for production HTTPS


        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/auth/refresh")
                .maxAge(jwtConfig.getRefreshTokenExpiration())
                .sameSite("None")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(
                new JwtResponse(loginResponse.getAccessToken().toString())
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@CookieValue(value = "refreshToken") String refreshToken){
        Jwt accessToken = authService.refreshAccessToken(refreshToken);

        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request){
        authService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signup-admin")
    public ResponseEntity<Void> signupAdmin(@RequestBody SignupRequest request){
        authService.signupAdmin(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String , String>> me(){
        return ResponseEntity.ok(Map.of("me", " i am accessible"));
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateUser(){
        return ResponseEntity.badRequest().body(Map.of("error", "user already exists"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredential(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
