package com.sahil.SplitwiseApp.DTO.nonExceptionDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Setter
@Getter
public class UserSettledDTO{
    int expenseId;
    int userId;
    String userName;
    BigDecimal settlementAmount;
    Timestamp settledAt;
}
