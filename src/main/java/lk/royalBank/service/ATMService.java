package lk.royalBank.service;

import lk.royalBank.dto.ATMcardDTO;
import lk.royalBank.dto.BankAccountDTO;

public interface ATMService {


    void addATM(ATMcardDTO atMcardDTO);

    BankAccountDTO loginATM(String atmPIN);
}
