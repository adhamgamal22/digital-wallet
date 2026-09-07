package com.digitalwallet.Services;

import com.digitalwallet.exception.InsufficientBalanceException;
import com.digitalwallet.exception.ResourceNotFoundException;
import com.digitalwallet.dtos.Mapper.TransactionMapper;
import com.digitalwallet.dtos.Mapper.WalletMapper;
import com.digitalwallet.dtos.TransactionResponse;
import com.digitalwallet.dtos.WalletResponse;
import com.digitalwallet.entity.Transaction;
import com.digitalwallet.model.entity.Wallet;
import com.digitalwallet.enums.TransactionStatus;
import com.digitalwallet.enums.TransactionType;
import com.digitalwallet.repository.TransactionRepository;
import com.digitalwallet.repository.WalletRepository;
import com.digitalwallet.Services.NotificationService;
import com.digitalwallet.Services.WalletServiceImpl;
import com.digitalwallet.util.TransactionIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionIdGenerator transactionIdGenerator;
    private final NotificationService notificationService;
    private final WalletMapper walletMapper;
    private final TransactionMapper transactionMapper;

    @Override
    public WalletResponse getWalletByUserId(String userId) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet", "userId", userId));
        return walletMapper.toResponse(wallet);
    }

    @Override
    public WalletResponse getWalletByWalletNumber(String walletNumber) {
        Wallet wallet = walletRepository.findByWalletNumber(walletNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet", "walletNumber", walletNumber));
        return walletMapper.toResponse(wallet);
    }

    @Override
    public WalletResponse getWalletBalance(String walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet", "id", walletId));
        return walletMapper.toResponse(wallet);
    }

    @Override
    @Transactional
    public TransactionResponse deposit(String walletId, BigDecimal amount, String description) {
        validateAmount(amount);

        Wallet wallet = walletRepository.findByIdWithLock(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet", "id", walletId));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);

        Transaction transaction = createTransaction(
                null,
                wallet.getWalletNumber(),
                amount,
                TransactionType.DEPOSIT,
                description,
                walletId,
                walletId
        );
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction = transactionRepository.save(transaction);

        notificationService.sendTransactionNotification(transaction);

        return transactionMapper.toResponse(transaction);
    }

    @Override
    @Transactional
    public TransactionResponse withdraw(String walletId, BigDecimal amount, String description) {
        validateAmount(amount);

        Wallet wallet = walletRepository.findByIdWithLock(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet", "id", walletId));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(walletId, amount.toString(), wallet.getBalance().toString());
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);

        Transaction transaction = createTransaction(
                wallet.getWalletNumber(),
                null,
                amount,
                TransactionType.WITHDRAWAL,
                description,
                walletId,
                walletId
        );
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction = transactionRepository.save(transaction);

        notificationService.sendTransactionNotification(transaction);

        return transactionMapper.toResponse(transaction);
    }

    @Override
    @Transactional
    public TransactionResponse transfer(String senderWalletId, String recipientWalletNumber,
                                        BigDecimal amount, String description) {
        validateAmount(amount);

        Wallet sender = walletRepository.findByIdWithLock(senderWalletId)
                .orElseThrow(() -> new ResourceNotFoundException("Sender wallet", "id", senderWalletId));

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(senderWalletId, amount.toString(), sender.getBalance().toString());
        }

        Wallet recipient = walletRepository.findByWalletNumber(recipientWalletNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Recipient wallet", "walletNumber", recipientWalletNumber));

        if (sender.getId().equals(recipient.getId())) {
            throw new IllegalArgumentException("Cannot transfer to the same wallet");
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));

        walletRepository.save(sender);
        walletRepository.save(recipient);

        Transaction transaction = createTransaction(
                sender.getWalletNumber(),
                recipient.getWalletNumber(),
                amount,
                TransactionType.TRANSFER,
                description,
                sender.getId(),
                recipient.getId()
        );
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction = transactionRepository.save(transaction);

        notificationService.sendTransactionNotification(transaction);

        return transactionMapper.toResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> getTransactionHistory(String walletId, Pageable pageable) {
        if (!walletRepository.existsById(walletId)) {
            throw new ResourceNotFoundException("Wallet", "id", walletId);
        }

        Page<Transaction> transactions = transactionRepository.findByWalletId(walletId, pageable);
        return transactions.map(transactionMapper::toResponse);
    }

    private Transaction createTransaction(String senderWalletNumber, String recipientWalletNumber,
                                          BigDecimal amount, TransactionType type,
                                          String description, String senderWalletId,
                                          String recipientWalletId) {
        return Transaction.builder()
                .id(transactionIdGenerator.generateTransactionId())
                .amount(amount)
                .type(type)
                .status(TransactionStatus.PROCESSING)
                .description(description)
                .senderWalletId(senderWalletId)
                .recipientWalletId(recipientWalletId)
                .senderWalletNumber(senderWalletNumber)
                .recipientWalletNumber(recipientWalletNumber)
                .build();
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (amount.scale() > 2) {
            throw new IllegalArgumentException("Amount cannot have more than 2 decimal places");
        }
    }
}