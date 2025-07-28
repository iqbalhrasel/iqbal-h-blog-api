package com.pxc.blog_api.auth;

import com.pxc.blog_api.auth.repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserLogoutHandler implements LogoutHandler {
    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }

        String token = authHeader.replace("Bearer ", "");

        var storedToken = tokenRepository.findByJwtToken(token).orElse(null);
        if(storedToken != null){
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}
