package com.FetchRewards.receiptprocessorchallenge.model;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    @NotNull
    @Size(min = 1, max = 100)
    private String shortDescription;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;
}
