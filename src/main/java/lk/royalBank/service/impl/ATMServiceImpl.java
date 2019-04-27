package lk.royalBank.service.impl;

import lk.royalBank.dto.ATMcardDTO;
import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.dto.ClientDTO;
import lk.royalBank.entity.ATMcard;
import lk.royalBank.entity.BankAccount;
import lk.royalBank.entity.Client;
import lk.royalBank.repository.ATMRepository;
import lk.royalBank.service.ATMService;
import lk.royalBank.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Transactional
@Service
public class ATMServiceImpl implements ATMService {


    @Autowired
    private ATMRepository atmRepository;

    @Autowired
    private ClientService clientService;
    @Override
    public void addATM(ATMcardDTO atMcardDTO) {

        BankAccountDTO bankAccountDTO =
                atMcardDTO.getBankAccountDTO();
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO,bankAccount);
        ATMcard atMcard = new ATMcard();
        BeanUtils.copyProperties(atMcardDTO,atMcard);
        atMcard.setBankAccount(bankAccount);
        atmRepository.save(atMcard);

    }

    @Override
    public BankAccountDTO loginATM(String atmPIN) {

        ATMcard atMcard = atmRepository.loginATM(atmPIN);
        if(atMcard!=null){
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            ClientDTO clientDTO = new ClientDTO();
            Client client = atMcard.getBankAccount().getClient();
            BeanUtils.copyProperties(client,clientDTO);
            BeanUtils.copyProperties(atMcard.getBankAccount(),bankAccountDTO);
            bankAccountDTO.setClientDTO(clientDTO);
            return bankAccountDTO;
        }else{
            throw new RuntimeException("Authentication Failed  ");
        }

    }

    @Override
    public String requestDeactiveATM(String pin,String NIC) throws MessagingException {
        ATMcard atMcard = atmRepository.loginATM(pin);
        if(atMcard==null){
            throw new RuntimeException("No ATM card Found");
        }

        Random random = new Random();
        int number = random.nextInt(20);
        String code = "587"+number;

        ClientDTO client = clientService.findByID(NIC);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("royalbank1998123", "Royal,123456");
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("SLlibrary1234@gmail.com",false));
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress(client.getEmail()));
        msg.setSubject("Royal Bank");
        msg.setContent("Dear Customer,<br> Your verification code is "+code,"text/html");
        msg.setSentDate(new Date());
        Transport.send(msg);

        return code;


    }

    @Override
    public void removeATM(String pin) {
      atmRepository.remove(pin);
    }
}
