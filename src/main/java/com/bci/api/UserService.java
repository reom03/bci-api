package com.bci.api;

import com.bci.api.dtos.UserCreateRequest;
import com.bci.api.dtos.UserCreateResponse;
import com.bci.api.model.User;
import com.bci.api.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.util.Date;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Value("${jwt.secret}")
    private String secret;

    public UserCreateResponse create(UserCreateRequest requestVO){
        UserCreateResponse response;
        if(!isEmailRegistered(requestVO.getEmail())) {
            User user = User.create(requestVO);
            user.setToken(generateJWTToken(user));
            userRepository.save(user);
            response = new UserCreateResponse(user);
        }else {
            response = new UserCreateResponse("Email ya se encuentra registrado");
        }
        return response;
    }

    public boolean isEmailRegistered(String email){
        return userRepository.findByEmail(email) != null;
    }
    private String generateJWTToken(User user){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String token = Jwts
                .builder()
                .setId("bciJWTToken")
                .setSubject(user.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(key).compact();
        return token;
    }
}
