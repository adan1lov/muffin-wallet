package ru.hse.muffin.wallet.data.api;

import java.util.UUID;
import ru.hse.muffin.wallet.data.api.dto.MuffinTransaction;

public interface MuffinTransactionRepository {

  MuffinTransaction save(MuffinTransaction transaction);

  MuffinTransaction findById(UUID id);

  MuffinTransaction findByFromAccount(UUID FromAccountId);

  MuffinTransaction findByToAccount(UUID toAccountId);
}
