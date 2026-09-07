package com.digitalwallet.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWalletRequest {

    @Size(max = 10, message = "Currency code must not exceed 10 characters")
    private String currency;

    private Boolean isActive;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
}