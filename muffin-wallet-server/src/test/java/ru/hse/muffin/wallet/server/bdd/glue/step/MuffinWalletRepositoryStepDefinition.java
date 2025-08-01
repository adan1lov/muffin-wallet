package ru.hse.muffin.wallet.server.bdd.glue.step;

import static org.junit.Assert.assertNotNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import ru.hse.muffin.wallet.data.api.MuffinWalletRepository;
import ru.hse.muffin.wallet.data.api.dto.MuffinWallet;

@AllArgsConstructor
public class MuffinWalletRepositoryStepDefinition {

  private final MuffinWalletRepository muffinWalletRepository;

  @Given("muffin wallet exists with parameters:")
  public void muffinWalletExistsWithParams(Map<String, String> params) {
    var muffinWallet = new MuffinWallet();

    muffinWallet.setId(UUID.fromString(params.get("id")));
    muffinWallet.setBalance(new BigDecimal(params.get("balance")));
    muffinWallet.setOwnerName(params.get("owner_name"));

    muffinWalletRepository.save(muffinWallet);
  }

  @Then("check muffin wallet exists with parameters:")
  public void checkMuffinWalletExistsWithParams(Map<String, String> params) {
    var allMuffinWallet = muffinWalletRepository.findAll(Pageable.ofSize(Integer.MAX_VALUE));

    assertNotNull(allMuffinWallet.filter((m) ->
        m.getBalance().equals(new BigDecimal(params.get("balance")))
            && m.getOwnerName().equals(params.get("owner_name"))
    ).toList().getFirst());
  }
}
