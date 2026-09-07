package com.digitalwallet.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final RedisService redisService;

    @Value("${jwt.expiration:86400000}")
    private long jwtExpirationMs;

    public void blacklistToken(String token) {
        long ttl = jwtExpirationMs / 1000;
        redisService.set("blacklist:" + token, "true", ttl, TimeUnit.SECONDS);
    }

    public boolean isTokenBlacklisted(String token) {
        return redisService.exists("blacklist:" + token);
    }

    public void removeFromBlacklist(String token) {
        redisService.delete("blacklist:" + token);
    }
}