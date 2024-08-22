package com.sahil.SplitwiseApp.service.settlements;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.PaymentDTO;
import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.SettlementDTO;
import java.util.*;

public interface ISettlementService {
    SettlementDTO debtUserToLenderHistory(int debtUserId, int lenderId);
    void settleDebtsBetweenUsers(int debtorId, int lenderId);
    void settleDebtsForUser(int userId, int otherUserId);
    List<PaymentDTO> allSettlementsToPay(int userId);
}
