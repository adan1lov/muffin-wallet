package ru.hse.muffin.wallet.server.controller;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.lang.Nullable;
import ru.hse.muffin.wallet.api.MuffinWalletApi;
import ru.hse.muffin.wallet.api.dto.CreateMuffinWallet;
import ru.hse.muffin.wallet.api.dto.MuffinWallet;
import ru.hse.muffin.wallet.api.dto.TransactionMuffin;
import ru.hse.muffin.wallet.api.dto.TransactionMuffinTo;

@RestController
public class MuffinWalletController implements MuffinWalletApi {

  @Override
  public MuffinWallet v1MuffinWalletIdGet(UUID id) {
    return new MuffinWallet();
  }

  @Override
  public TransactionMuffin v1MuffinWalletIdTransactionPost(
      UUID id, @Valid TransactionMuffinTo transactionMuffinTo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'v1MuffinWalletsGet'");
  }

  @Override
  public PagedModel<MuffinWallet> v1MuffinWalletsGet(String ownerName, Pageable pageable) {
    return new PagedModel<MuffinWallet>(new PageImpl<MuffinWallet>(List.of(new MuffinWallet())));
  }

  @Override
  public MuffinWallet v1MuffinWalletsPost(@Valid CreateMuffinWallet createMuffinWallet) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'v1MuffinWalletsGet'");
  }
}
