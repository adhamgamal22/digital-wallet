package com.digitalwallet.controller;

import com.digitalwallet.dtos.TransactionRequest;
import com.digitalwallet.dtos.ApiResponse;
import com.digitalwallet.dtos.TransactionResponse;
import com.digitalwallet.Services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final WalletService walletService;

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<TransactionResponse>> deposit(@Valid @RequestBody TransactionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        String walletId = walletService.getWalletByUserId(userId).getId();
        TransactionResponse response = walletService.deposit(
                walletId, request.getAmount(), request.getDescription()
        );
        return ResponseEntity.ok(ApiResponse.success(response, "Deposit successful"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<TransactionResponse>> withdraw(@Valid @RequestBody TransactionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        String walletId = walletService.getWalletByUserId(userId).getId();
        TransactionResponse response = walletService.withdraw(
                walletId, request.getAmount(), request.getDescription()
        );
        return ResponseEntity.ok(ApiResponse.success(response, "Withdrawal successful"));
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<TransactionResponse>> transfer(@Valid @RequestBody TransactionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        String walletId = walletService.getWalletByUserId(userId).getId();
        TransactionResponse response = walletService.transfer(
                walletId, request.getRecipientWalletNumber(),
                request.getAmount(), request.getDescription()
        );
        return ResponseEntity.ok(ApiResponse.success(response, "Transfer successful"));
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<Page<TransactionResponse>>> getTransactionHistory(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        String walletId = walletService.getWalletByUserId(userId).getId();
        Page<TransactionResponse> history = walletService.getTransactionHistory(walletId, pageable);
        return ResponseEntity.ok(ApiResponse.success(history));
    }
}