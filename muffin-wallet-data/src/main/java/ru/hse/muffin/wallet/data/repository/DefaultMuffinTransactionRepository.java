package ru.hse.muffin.wallet.data.repository;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.muffin.wallet.data.api.MuffinTransactionRepository;
import ru.hse.muffin.wallet.data.api.dto.MuffinTransaction;

@Repository
@AllArgsConstructor
public class DefaultMuffinTransactionRepository implements MuffinTransactionRepository {
  private static final RowMapper<MuffinTransaction> ROW_MAPPER =
      (rs, rowNum) -> {
        var muffinTransaction = new MuffinTransaction();

        muffinTransaction.setId(rs.getObject("id", UUID.class));
        muffinTransaction.setAmount(rs.getBigDecimal("amount"));
        muffinTransaction.setFromMuffinWalletId(rs.getObject("from_muffin_wallet_id", UUID.class));
        muffinTransaction.setToMuffinWalletId(rs.getObject("to_muffin_wallet_id", UUID.class));
        muffinTransaction.setCreatedAt(rs.getObject("created_at", OffsetDateTime.class));

        return muffinTransaction;
      };

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public MuffinTransaction save(MuffinTransaction transaction) {
    return namedParameterJdbcTemplate.queryForObject(
        """
        insert into muffin_transaction (id, amount, from_muffin_wallet_id, to_muffin_wallet_id)
        values (uuid_generate_v4(), :amount, :from_muffin_wallet_id, :to_muffin_wallet_id)
        returning *;
        """,
        Map.of(
            "amount", transaction.getAmount(),
            "from_muffin_wallet_id", transaction.getFromMuffinWalletId(),
            "to_muffin_wallet_id", transaction.getToMuffinWalletId()),
        ROW_MAPPER);
  }

  @Override
  public MuffinTransaction findById(UUID id) {
    return namedParameterJdbcTemplate.queryForObject(
        "select * from muffin_transaction where id = :id", Map.of("id", id), ROW_MAPPER);
  }

  @Override
  public MuffinTransaction findByFromMuffinWalletId(UUID fromMuffinWalletId) {
    return namedParameterJdbcTemplate.queryForObject(
        "select * from muffin_transaction where from_muffin_wallet_id = :from_muffin_wallet_id",
        Map.of("from_muffin_wallet_id", fromMuffinWalletId),
        ROW_MAPPER);
  }

  @Override
  public MuffinTransaction findByToMuffinWalletId(UUID toMuffinWalletId) {
    return namedParameterJdbcTemplate.queryForObject(
        "select * from muffin_transaction where to_muffin_wallet_id = :to_muffin_wallet_id",
        Map.of("to_muffin_wallet_id", toMuffinWalletId),
        ROW_MAPPER);
  }
}
