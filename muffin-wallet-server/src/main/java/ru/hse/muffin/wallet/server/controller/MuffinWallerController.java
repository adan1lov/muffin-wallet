package ru.hse.muffin.wallet.server.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.muffin.wallet.api.MuffinWalletApi;
import ru.hse.muffin.wallet.api.dto.CreateMuffinWallet;
import ru.hse.muffin.wallet.api.dto.MuffinWallet;
import ru.hse.muffin.wallet.api.dto.TransferMuffin;
import ru.hse.muffin.wallet.api.dto.TransferMuffinTo;

@RestController
public class MuffinWallerController implements MuffinWalletApi {

  @Override
  public MuffinWallet v1MuffinWalletIdGet(UUID id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'v1MuffinWalletIdGet'");
  }

  @Override
  public TransferMuffin v1MuffinWalletIdTransferPost(
      UUID id, @Valid TransferMuffinTo transferMuffinTo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'v1MuffinWalletIdTransferPost'");
  }

  @Override
  public String v1MuffinWalletsGet(@Valid String ownerName, Pageable pageable) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'v1MuffinWalletsGet'");
  }

  @Override
  public MuffinWallet v1MuffinWalletsPost(@Valid CreateMuffinWallet createMuffinWallet) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'v1MuffinWalletsPost'");
  }
}
