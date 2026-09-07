package com.digitalwallet.dtos.Mapper;

import com.digitalwallet.dtos.TransactionResponse;
import com.digitalwallet.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponse toResponse(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .status(transaction.getStatus())
                .description(transaction.getDescription())
                .senderWalletNumber(transaction.getSenderWalletNumber())
                .recipientWalletNumber(transaction.getRecipientWalletNumber())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}