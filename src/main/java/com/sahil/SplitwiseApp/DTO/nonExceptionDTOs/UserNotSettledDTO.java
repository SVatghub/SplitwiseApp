package com.sahil.SplitwiseApp.DTO.nonExceptionDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Builder
@Setter
@Getter
public class UserNotSettledDTO{
    int expenseId;
    int userId;
    String userName;
    BigDecimal settlementAmount;
}
