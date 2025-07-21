package ru.hse.muffin.wallet.data.repository;

import java.time.LocalDateTime;
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
      muffinTransaction.setFromAccount(rs.getObject("from_account", UUID.class));
      muffinTransaction.setToAccount(rs.getObject("to_account", UUID.class));
      muffinTransaction.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));

      return muffinTransaction;
    };

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public MuffinTransaction save(MuffinTransaction transaction) {
    return namedParameterJdbcTemplate.queryForObject(
      """
      insert into muffin_transaction (id, amount, from_account, to_account, created_at)
      values (uuid_generate_v4(), :amount, :from_account, :to_account, :created_at)
      returning *;
      """,
      Map.of(
          "amount", transaction.getAmount(),
          "from_account", transaction.getFromAccount(),
          "to_account", transaction.getToAccount(),
          "created_at", transaction.getCreatedAt()),
      ROW_MAPPER
    );
  }

  @Override
  public MuffinTransaction findById(UUID id) {
    return namedParameterJdbcTemplate.queryForObject(
      "select * from muffin_transaction where id = :id",
      Map.of("id", id),
      ROW_MAPPER
    );
  }

  @Override
  public MuffinTransaction findByFromAccount(UUID FromAccountId) {
    return namedParameterJdbcTemplate.queryForObject(
      "select * from muffin_transaction where from_account = :from_account",
      Map.of("from_account", FromAccountId),
      ROW_MAPPER
    );
  }

  @Override
  public MuffinTransaction findByToAccount(UUID toAccountId) {
    return namedParameterJdbcTemplate.queryForObject(
      "select * from muffin_transaction where to_account = :to_account",
      Map.of("to_account", toAccountId),
      ROW_MAPPER
    );
  }
}
