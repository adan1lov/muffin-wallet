package ru.hse.muffin.wallet.data.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class MuffinWallet {

  private UUID id;

  private BigDecimal balance;

  private String ownerName;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
