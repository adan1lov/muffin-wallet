package ru.hse.muffin.wallet.data.api;

import java.util.UUID;
import ru.hse.muffin.wallet.data.api.dto.MuffinTransaction;

public interface MuffinTransactionRepository {

  MuffinTransaction save(MuffinTransaction transaction);

  MuffinTransaction findById(UUID id);

  MuffinTransaction findByFromMuffinWalletId(UUID fromMuffinWalletId);

  MuffinTransaction findByToMuffinWalletId(UUID toMuffinWalletId);
}
