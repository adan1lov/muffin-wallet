package ru.hse.muffin.wallet.data.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class MuffinWallet {

  private UUID id;

  private BigDecimal balance;

  private String ownerName;

  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;
}
