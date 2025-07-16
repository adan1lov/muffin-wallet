package ru.hse.muffin.wallet.data.api;

import ru.hse.muffin.wallet.data.api.dto.MuffinWallet;

public interface MuffinWalletRepository {

  MuffinWallet save(MuffinWallet wallet);
}
