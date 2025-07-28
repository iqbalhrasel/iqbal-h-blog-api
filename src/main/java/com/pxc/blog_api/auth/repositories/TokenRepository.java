package com.pxc.blog_api.auth.repositories;

import com.pxc.blog_api.auth.models.Token;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @EntityGraph(attributePaths = "user")
    @Query("SELECT t FROM Token t WHERE t.user.id = :userId AND t.revoked = false")
    List<Token> getAllValidTokensByUser(@Param(value = "userId") Integer userId);

    Optional<Token> findByJwtToken(String jwtToken);
}
