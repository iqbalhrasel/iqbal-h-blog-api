package com.pxc.blog_api.auth.models;

import com.pxc.blog_api.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jwt_token")
    private String jwtToken;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(name = "revoked")
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Token(String jwtToken, TokenType tokenType, boolean revoked, User user) {
        this.jwtToken = jwtToken;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.user = user;
    }
}
