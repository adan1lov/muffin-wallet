package ru.hse.muffin.wallet.data.api;

import ru.hse.muffin.wallet.data.api.dto.MaffinWallet;

public interface MuffinWalletRepository {

  MaffinWallet save(MaffinWallet wallet);
}
