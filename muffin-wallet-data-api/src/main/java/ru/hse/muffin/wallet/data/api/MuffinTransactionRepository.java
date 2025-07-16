package ru.hse.muffin.wallet.data.api;

import ru.hse.muffin.wallet.data.api.dto.MaffinTransaction;

public interface MuffinTransactionRepository {

  MaffinTransaction save(MaffinTransaction transaction);
}
