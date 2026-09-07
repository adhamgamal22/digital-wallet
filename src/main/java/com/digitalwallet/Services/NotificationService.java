package com.digitalwallet.Services;

import com.digitalwallet.entity.Transaction;

public interface NotificationService {
    void sendTransactionNotification(Transaction transaction);
    void sendWalletNotification(String userId, String message);
    void sendEmailNotification(String email, String subject, String body);
    void sendSmsNotification(String phoneNumber, String message);
}