package ru.hse.muffin.wallet.server.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
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
    return muffinWalletMapper.serviceDtoToMuffinWalletApiDto(
        muffinWalletService.getMuffinWallet(id));
  }

  @Override
  public TransactionMuffin v1MuffinWalletIdTransactionPost(
      UUID id, @Valid TransactionMuffinTo transactionMuffinTo) {
    return muffinWalletMapper.serviceDtoToMuffinTransactionApiDto(
        muffinWalletService.createMuffinTransaction(
            muffinWalletMapper.apiDtoToMuffinTransactionServiceDto(id, transactionMuffinTo)));
  }

  @Override
  public PagedModel<MuffinWallet> v1MuffinWalletsGet(String ownerName, Pageable pageable) {
    var muffinWallets =
        muffinWalletService
            .getMuffinWalletsByOwner(ownerName, pageable)
            .map(muffinWalletMapper::serviceDtoToMuffinWalletApiDto);

    return new PagedModel<>(muffinWallets);
  }

  @Override
  public MuffinWallet v1MuffinWalletsPost(@Valid CreateMuffinWallet createMuffinWallet) {
    return muffinWalletMapper.serviceDtoToMuffinWalletApiDto(
        muffinWalletService.createMuffinWallet(
            muffinWalletMapper.apiCreateDtoToMuffinWalletServiceDto(createMuffinWallet)));
  }
}
