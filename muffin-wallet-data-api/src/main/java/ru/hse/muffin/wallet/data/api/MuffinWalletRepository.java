package ru.hse.muffin.wallet.data.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.hse.muffin.wallet.data.api.dto.MuffinWallet;

public interface MuffinWalletRepository {

  MuffinWallet save(MuffinWallet wallet);

  Optional<MuffinWallet> findById(UUID id);

  List<MuffinWallet> findByIdInForUpdate(List<UUID> ids);

  Page<MuffinWallet> findByOwnerNameLike(String ownerName, Pageable pageable);

  Page<MuffinWallet> findAll(Pageable pageable);

  MuffinWallet update(MuffinWallet wallet);
}
