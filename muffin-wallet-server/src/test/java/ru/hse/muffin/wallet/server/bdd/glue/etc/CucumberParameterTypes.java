package ru.hse.muffin.wallet.server.bdd.glue.etc;

import io.cucumber.java.ParameterType;
import java.util.UUID;

public class CucumberParameterTypes {

    @ParameterType("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")
    public UUID uuid(String uuid) {
        return UUID.fromString(uuid);
    }
}