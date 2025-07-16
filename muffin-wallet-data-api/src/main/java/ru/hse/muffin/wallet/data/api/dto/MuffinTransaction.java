package ru.hse.muffin.wallet.data.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class MuffinTransaction {

  private UUID id;

  private BigDecimal amount;

  private UUID fromAccount;

  private UUID toAccount;

  private LocalDateTime createdAt;
}
