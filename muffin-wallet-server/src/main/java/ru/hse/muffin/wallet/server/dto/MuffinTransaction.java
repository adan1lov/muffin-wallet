package ru.hse.muffin.wallet.server.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class MuffinTransaction {

  private UUID id;

  private BigDecimal amount;

  private UUID fromMuffinWalletId;

  private UUID toMuffinWalletId;

  private OffsetDateTime createdAt;
}
