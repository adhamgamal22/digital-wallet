package com.digitalwallet.Services;

import com.digitalwallet.entity.Transaction;
import com.digitalwallet.Services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "notification.exchange";
    private static final String ROUTING_KEY = "notification.routing.key";

    @Override
    public void sendTransactionNotification(Transaction transaction) {
        try {
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, transaction);
            log.info("Transaction notification sent: {}", transaction.getId());
        } catch (Exception e) {
            log.error("Failed to send transaction notification: {}", e.getMessage());
        }
    }

    @Override
    public void sendWalletNotification(String userId, String message) {
        // Implementation
    }

    @Override
    public void sendEmailNotification(String email, String subject, String body) {
        // Implementation
    }

    @Override
    public void sendSmsNotification(String phoneNumber, String message) {
        // Implementation
    }
}