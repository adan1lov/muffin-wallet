package ru.hse.muffin.wallet.server.bdd.glue.step;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AllArgsConstructor
public class MockMvcStepDefinition {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @Then("get muffin wallet by id {uuid} returns data:")
  public void getMuffinWalletByIdReturnsData(UUID id, Map<String, String> data) throws Exception {

    var result =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/v1/muffin-wallet/{id}", id))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

    var muffinWallet = objectMapper.readTree(result.getResponse().getContentAsString());

    assertEquals(data.get("id"), muffinWallet.get("id").asText());
    assertEquals(data.get("owner_name"), muffinWallet.get("owner_name").asText());

    var expectedBalance = new BigDecimal(data.get("balance"));

    var actualBalance = new BigDecimal(muffinWallet.get("balance").asText());

    assertEquals(expectedBalance, actualBalance);
  }
}
