package com.digitalwallet.dtos.Mapper;

import com.digitalwallet.dtos.WalletResponse;
import com.digitalwallet.model.entity.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletResponse toResponse(Wallet wallet) {
        if (wallet == null) {
            return null;
        }

        return WalletResponse.builder()
                .id(wallet.getId())
                .walletNumber(wallet.getWalletNumber())
                .balance(wallet.getBalance())
                .currency(wallet.getCurrency())
                .isActive(wallet.getIsActive())
                .userId(wallet.getUser() != null ? wallet.getUser().getId() : null)
                .build();
    }
}