package lk.royalBank.service;

import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.dto.ClientDTO;
import lk.royalBank.dto.CreateAccountDTO;

public interface CreateAccountService {
    void createAccount(CreateAccountDTO createAccountDTO);
}
