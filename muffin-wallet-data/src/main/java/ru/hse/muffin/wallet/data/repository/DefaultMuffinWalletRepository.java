package ru.hse.muffin.wallet.data.repository;

import java.time.LocalDateTime;
import java.util.Map;
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
}
