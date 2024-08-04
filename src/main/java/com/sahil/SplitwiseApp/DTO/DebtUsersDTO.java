package com.sahil.SplitwiseApp.DTO;

import com.sahil.SplitwiseApp.model.DebtUsers;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class DebtUsersDTO {
    private int id;
    private int userId;
    private int expenseId;
    private BigDecimal debtAmount;
    private boolean isSettled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
