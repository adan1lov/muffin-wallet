package ru.hse.muffin.wallet.server.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MuffinWalletMapper {

  // MuffinWallet mappings
  ru.hse.muffin.wallet.data.api.dto.MuffinWallet serviceDtoToMuffinWalletDataDto(
      ru.hse.muffin.wallet.server.dto.MuffinWallet muffinWallet);

  ru.hse.muffin.wallet.server.dto.MuffinWallet dataDtoToMuffinWalletServiceDto(
      ru.hse.muffin.wallet.data.api.dto.MuffinWallet muffinWallet);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "balance", constant = "0")
  ru.hse.muffin.wallet.server.dto.MuffinWallet apiCreateDtoToMuffinWalletServiceDto(
      ru.hse.muffin.wallet.api.dto.CreateMuffinWallet createMuffinWallet);

  ru.hse.muffin.wallet.api.dto.MuffinWallet serviceDtoToMuffinWalletApiDto(
      ru.hse.muffin.wallet.server.dto.MuffinWallet muffinWallet);

  // MuffinTransaction mappings
  ru.hse.muffin.wallet.data.api.dto.MuffinTransaction serviceDtoToMuffinTransactionDataDto(
      ru.hse.muffin.wallet.server.dto.MuffinTransaction muffinTransaction);

  ru.hse.muffin.wallet.server.dto.MuffinTransaction dataDtoToMuffinTransactionServiceDto(
      ru.hse.muffin.wallet.data.api.dto.MuffinTransaction muffinTransaction);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "fromMuffinWalletId", source = "fromMuffinWalletId")
  ru.hse.muffin.wallet.server.dto.MuffinTransaction apiDtoToMuffinTransactionServiceDto(
      UUID fromMuffinWalletId,
      ru.hse.muffin.wallet.api.dto.TransactionMuffinTo transactionMuffinTo);

  ru.hse.muffin.wallet.api.dto.TransactionMuffin serviceDtoToMuffinTransactionApiDto(
      ru.hse.muffin.wallet.server.dto.MuffinTransaction muffinWallet);
}
