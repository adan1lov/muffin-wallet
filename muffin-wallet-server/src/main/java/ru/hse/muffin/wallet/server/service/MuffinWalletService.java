package ru.hse.muffin.wallet.server.service;

import ru.hse.muffin.wallet.server.dto.MuffinWallet;
import ru.hse.muffin.wallet.server.dto.MuffinTransaction;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MuffinWalletService {

  MuffinWallet getMuffinWallet(UUID id);

  Page<MuffinWallet> getMuffinWalletsByOwner(String ownerName, Pageable pageable);

  MuffinWallet createMuffinWallet(MuffinWallet muffinWallet);

  MuffinTransaction createMuffinTransaction(MuffinTransaction muffinTransaction);
}
