package ru.hse.muffin.wallet.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MuffinWalletMapper {

  ru.hse.muffin.wallet.data.api.dto.MuffinWallet serviceDtoToMuffinWalletDataDto(
      ru.hse.muffin.wallet.server.dto.MuffinWallet muffinWallet);

  ru.hse.muffin.wallet.server.dto.MuffinWallet dataDtoToMuffinWalletServiceDto(
      ru.hse.muffin.wallet.data.api.dto.MuffinWallet muffinWallet);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "balance", constant = "0")
  ru.hse.muffin.wallet.server.dto.MuffinWallet apiDtoToMuffinWalletServiceDto(
      ru.hse.muffin.wallet.api.dto.CreateMuffinWallet createMuffinWallet);

  ru.hse.muffin.wallet.api.dto.MuffinWallet serviceDtoToMuffinWalletApiDto(
      ru.hse.muffin.wallet.server.dto.MuffinWallet muffinWallet);
}
