package com.digitalwallet.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final RedisService redisService;

    public boolean isAllowed(String key, int maxRequests, long timeWindow, TimeUnit unit) {
        String rateKey = "rate:" + key;
        Long currentCount = redisService.increment(rateKey);

        if (currentCount == 1) {
            redisService.expire(rateKey, timeWindow, unit);
        }

        return currentCount <= maxRequests;
    }

    public long getRemainingRequests(String key, int maxRequests) {
        String rateKey = "rate:" + key;
        Object count = redisService.get(rateKey);
        if (count == null) {
            return maxRequests;
        }
        return maxRequests - Long.parseLong(count.toString());
    }

    public void resetLimit(String key) {
        redisService.delete("rate:" + key);
    }
}