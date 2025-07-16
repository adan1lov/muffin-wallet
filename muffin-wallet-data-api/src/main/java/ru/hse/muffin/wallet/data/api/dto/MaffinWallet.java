package ru.hse.muffin.wallet.data.api.dto;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MaffinWallet {

  private UUID id;

  private BigDecimal balance;

  private String owner;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
