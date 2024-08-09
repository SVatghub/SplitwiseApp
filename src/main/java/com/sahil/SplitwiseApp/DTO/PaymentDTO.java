package com.sahil.SplitwiseApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDTO {
    private int userId;
    private BigDecimal amount;
    private String name;
}