package com.sahil.SplitwiseApp.DTO.nonExceptionDTOs;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SettlementDTO {
    private int fromId;
    private int toId;
    private List<ExpensesDTO> expensesDTOS;
}

