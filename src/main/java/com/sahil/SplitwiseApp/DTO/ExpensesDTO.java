package com.sahil.SplitwiseApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesDTO {
    private int id;
    private String title;
    private BigDecimal amount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
