package ru.hse.muffin.wallet.data.api;

import java.util.List;
import java.util.UUID;

import ru.hse.muffin.wallet.data.api.dto.MuffinWallet;

public interface MuffinWalletRepository {

  MuffinWallet save(MuffinWallet wallet);

  MuffinWallet findById(UUID id);

  List<MuffinWallet> findByOwnerName(String ownerName);

  MuffinWallet update(MuffinWallet wallet);
}
