package ru.hse.muffin.wallet.data.api.dto;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MaffinTransaction {
  
  private UUID id;

  private BigDecimal amount;

  private UUID fromAccount;

  private UUID toAccount;

  private LocalDateTime createdAt;
}
