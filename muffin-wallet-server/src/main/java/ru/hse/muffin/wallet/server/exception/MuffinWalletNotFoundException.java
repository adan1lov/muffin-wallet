package ru.hse.muffin.wallet.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MuffinWalletNotFoundException  extends RuntimeException{

  public MuffinWalletNotFoundException(){
    super("Muffin wallet not found");
  }
}
