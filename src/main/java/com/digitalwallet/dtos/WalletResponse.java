package com.digitalwallet.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {

    private String id;
    private String walletNumber;
    private BigDecimal balance;
    private String currency;
    private Boolean isActive;
    private String userId;
}