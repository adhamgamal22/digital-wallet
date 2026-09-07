package com.digitalwallet.Services;

import com.digitalwallet.dtos.TransactionResponse;
import com.digitalwallet.dtos.WalletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface WalletService {

    WalletResponse getWalletByUserId(String userId);

    WalletResponse getWalletByWalletNumber(String walletNumber);

    WalletResponse getWalletBalance(String walletId);

    @Transactional
    TransactionResponse deposit(String walletId, BigDecimal amount, String description);

    @Transactional
    TransactionResponse withdraw(String walletId, BigDecimal amount, String description);

    @Transactional
    TransactionResponse transfer(String senderWalletId, String recipientWalletNumber,
                                 BigDecimal amount, String description);

    Page<TransactionResponse> getTransactionHistory(String walletId, Pageable pageable);
}