package com.sahil.SplitwiseApp.DTO.nonExceptionDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class DebtUsersDTO {
    private int id;
    private int userId;
    private int expenseId;
    private BigDecimal debtAmount;
    private boolean isSettled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
