package com.sahil.SplitwiseApp.DTO.nonExceptionDTOs;

import lombok.*;
import java.util.List;

@Setter
@Getter
@Builder
public class ExpenseSettlementStatusDTO {
    List<UserSettledDTO> userSettledDTOList;
    List<UserNotSettledDTO> userNotSettledDTOList;
}
