package ru.hse.muffin.wallet.data.api;

import ru.hse.muffin.wallet.data.api.dto.MuffinTransaction;

public interface MuffinTransactionRepository {

  MuffinTransaction save(MuffinTransaction transaction);
}
