package ru.hse.muffin.wallet.data.repository;

import java.time.OffsetDateTime;
import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        muffinWallet.setCreatedAt(rs.getObject("created_at", OffsetDateTime.class));
        muffinWallet.setUpdatedAt(rs.getObject("updated_at", OffsetDateTime.class));

        return muffinWallet;
      };

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public MuffinWallet save(MuffinWallet wallet) {
    var id = wallet.getId() == null ? UUID.randomUUID() : wallet.getId();

    return namedParameterJdbcTemplate.queryForObject(
        """
        insert into muffin_wallet (id, balance, owner_name)
        values (:id, :balance, :owner_name)
        returning *;
        """,
        Map.of("id", id, "balance", wallet.getBalance(), "owner_name", wallet.getOwnerName()),
        ROW_MAPPER);
  }

  @Override
  public Optional<MuffinWallet> findById(UUID id) {
    var foundedWallets =
        namedParameterJdbcTemplate.query(
            "select * from muffin_wallet where id = :id", Map.of("id", id), ROW_MAPPER);

    if (foundedWallets.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(foundedWallets.getFirst());
  }

  @Override
  public List<MuffinWallet> findByIdInForUpdate(List<UUID> ids) {
    return namedParameterJdbcTemplate.query(
        "select * from muffin_wallet where id in (:ids) for update",
        Map.of("ids", ids),
        ROW_MAPPER);
  }

  /*
   * так как имена владельцев у двух разных кошельков могут совпадать (например
   * в случае полных тезок, ну или один человек владеет несколькими кошельками)
   * то возвращаем все кошельки с таким именем
   */
  @Override
  public Page<MuffinWallet> findByOwnerNameLike(String ownerName, Pageable pageable) {
    var wallets =
        namedParameterJdbcTemplate.query(
            """
              select * from muffin_wallet
              where owner_name like :owner_name
              limit :limit offset :offset;
            """,
            Map.of(
                "owner_name",
                "%" + ownerName + "%",
                "limit",
                pageable.getPageSize(),
                "offset",
                pageable.getOffset()),
            ROW_MAPPER);

    var walletsCount =
        namedParameterJdbcTemplate.queryForObject(
            """
              select count(*) from muffin_wallet
              where owner_name like :owner_name;
            """,
            Map.of("owner_name", "%" + ownerName + "%"),
            Integer.class);

    return new PageImpl<MuffinWallet>(wallets, pageable, walletsCount);
  }

  @Override
  public Page<MuffinWallet> findAll(Pageable pageable) {
    var wallets =
        namedParameterJdbcTemplate.query(
            """
              select * from muffin_wallet
              limit :limit offset :offset;
            """,
            Map.of("limit", pageable.getPageSize(), "offset", pageable.getOffset()),
            ROW_MAPPER);

    var walletsCount =
        namedParameterJdbcTemplate.queryForObject(
            """
              select count(*) from muffin_wallet;
            """,
            Collections.emptyMap(),
            Integer.class);

    return new PageImpl<MuffinWallet>(wallets, pageable, walletsCount);
  }

  @Override
  public MuffinWallet update(MuffinWallet wallet) {
    return namedParameterJdbcTemplate.queryForObject(
        """
        update muffin_wallet
        set balance = :balance, owner_name = :owner_name,
        updated_at = now()
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
