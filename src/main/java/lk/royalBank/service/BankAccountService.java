package lk.royalBank.service;

import lk.royalBank.dto.BankAccountDTO;

public interface BankAccountService {
   void addBankAccount(String accountNumber, BankAccountDTO bankAccountDTO);

   BankAccountDTO getAccountByID(String accountNumber);

   double checkBalance(String accountNumber);

   void doTransAction(String transactionType,String accountNumber,Double amount);
}
