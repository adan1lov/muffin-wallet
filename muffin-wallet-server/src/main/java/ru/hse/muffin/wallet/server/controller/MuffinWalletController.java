package ru.hse.muffin.wallet.server.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.muffin.wallet.api.MuffinWalletApi;
import ru.hse.muffin.wallet.api.dto.CreateMuffinWallet;
import ru.hse.muffin.wallet.api.dto.MuffinWallet;
import ru.hse.muffin.wallet.api.dto.TransactionMuffin;
import ru.hse.muffin.wallet.api.dto.TransactionMuffinTo;
import ru.hse.muffin.wallet.server.mapper.MuffinWalletMapper;
import ru.hse.muffin.wallet.server.service.MuffinWalletService;

@RestController
@AllArgsConstructor
public class MuffinWalletController implements MuffinWalletApi {

  private final MuffinWalletService muffinWalletService;

  private final MuffinWalletMapper muffinWalletMapper;

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
    return muffinWalletMapper.serviceDtoToMuffinWalletApiDto(
        muffinWalletService.createMuffinWallet(
            muffinWalletMapper.apiDtoToMuffinWalletServiceDto(createMuffinWallet)));
  }
}
