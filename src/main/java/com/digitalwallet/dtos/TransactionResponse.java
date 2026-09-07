package com.digitalwallet.dtos;

import com.digitalwallet.enums.TransactionStatus;
import com.digitalwallet.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private String id;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private String description;
    private String senderWalletNumber;
    private String recipientWalletNumber;
    private LocalDateTime createdAt;
}