package lk.royalBank.service;

import lk.royalBank.dto.ATMcardDTO;
import lk.royalBank.dto.BankAccountDTO;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface ATMService {


    void addATM(ATMcardDTO atMcardDTO);

    BankAccountDTO loginATM(String atmPIN);

    String requestDeactiveATM(String pin,String NIC) throws MessagingException;

    void removeATM(String pin);

}
