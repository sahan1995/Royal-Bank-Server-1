package lk.royalBank.service.impl;

import lk.royalBank.dto.CreateAccountDTO;
import lk.royalBank.service.BankAccountService;
import lk.royalBank.service.ClientService;
import lk.royalBank.service.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateAccountServiceImpl implements CreateAccountService {
    @Autowired
    ClientService  clientService;
    @Autowired
    BankAccountService bankAccountService;
    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) {
        clientService.addClient(createAccountDTO.getClientDTO().getClientID(),createAccountDTO.getClientDTO());
        bankAccountService.addBankAccount(createAccountDTO.getBankAccountDTO().getAccountNumber(),createAccountDTO.getBankAccountDTO());
    }
}
