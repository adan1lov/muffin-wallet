package ru.hse.muffin.wallet.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.muffin.wallet.data.api.MuffinWalletRepository;
import ru.hse.muffin.wallet.server.dto.MuffinWallet;
import ru.hse.muffin.wallet.server.mapper.MuffinWalletMapper;

@Service
@AllArgsConstructor
public class DefaultMuffinWalletService implements MuffinWalletService {

  private final MuffinWalletMapper muffinWalletMapper;

  private final MuffinWalletRepository muffinWalletRepository;

  @Override
  public MuffinWallet createMuffinWallet(MuffinWallet muffinWallet) {
    return muffinWalletMapper.dataDtoToMuffinWalletServiceDto(
        muffinWalletRepository.save(
            muffinWalletMapper.serviceDtoToMuffinWalletDataDto(muffinWallet)));
  }
}
