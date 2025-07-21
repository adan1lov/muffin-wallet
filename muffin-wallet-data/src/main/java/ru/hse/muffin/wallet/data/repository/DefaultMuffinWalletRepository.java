package ru.hse.muffin.wallet.data.repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.muffin.wallet.data.api.MuffinWalletRepository;
import ru.hse.muffin.wallet.data.api.dto.MuffinWallet;

@Repository
@AllArgsConstructor
public class DefaultMuffinWalletRepository implements MuffinWalletRepository {

  private static final RowMapper<MuffinWallet> ROW_MAPPER =
    (rs, rowNum) -> {
      var muffinWallet = new MuffinWallet();

      muffinWallet.setId(rs.getObject("id", UUID.class));
      muffinWallet.setBalance(rs.getBigDecimal("balance"));
      muffinWallet.setOwnerName(rs.getString("owner_name"));
      muffinWallet.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
      muffinWallet.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));

      return muffinWallet;
    };

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public MuffinWallet save(MuffinWallet wallet) {
    return namedParameterJdbcTemplate.queryForObject(
      """
      insert into muffin_wallet (id, balance, owner_name)
      values (uuid_generate_v4(), :balance, :owner_name)
      returning *;
      """,
      Map.of("balance", wallet.getBalance(), "owner_name", wallet.getOwnerName()),
      ROW_MAPPER);
  }

  @Override
  public MuffinWallet findById(UUID id) {
    return namedParameterJdbcTemplate.queryForObject(
      "select * from muffin_wallet where id = :id",
      Map.of("id", id),
      ROW_MAPPER);
  }

  /*
   * так как имена владельцев у двух разных кошельков могут совпадать (например
   * в случае полных тезок, ну или один человек владеет несколькими кошельками)
   * то возвращаем все кошельки с таким именем
   */
  @Override
  public List<MuffinWallet> findByOwnerName(String ownerName) {
    return namedParameterJdbcTemplate.query(
      "select * from muffin_wallet where owner_name = :owner_name",
      Map.of("owner_name", ownerName),
      ROW_MAPPER);
  }

  @Override
  public MuffinWallet update(MuffinWallet wallet) {
    return namedParameterJdbcTemplate.queryForObject(
      """
      update muffin_wallet
      set balance = :balance, owner_name = :owner_name
      where id = :id
      returning *;
      """,
      Map.of(
          "id", wallet.getId(),
          "balance", wallet.getBalance(),
          "owner_name", wallet.getOwnerName()),
      ROW_MAPPER);
  }
}
