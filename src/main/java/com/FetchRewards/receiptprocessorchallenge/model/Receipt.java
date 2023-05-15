package com.FetchRewards.receiptprocessorchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Receipt {
    @NotNull
    @Size(min = 1, max = 100)
    private String retailer;

    @NotNull
    private LocalDate purchaseDate;

    @NotNull
    private LocalTime purchaseTime;

    @NotNull
    @NotEmpty
    private List<Item> items;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal total;

    @JsonIgnore
    private int points;

}
