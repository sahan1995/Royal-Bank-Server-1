package lk.royalBank.service.impl;

import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.dto.ClientDTO;
import lk.royalBank.entity.BankAccount;
import lk.royalBank.entity.Client;
import lk.royalBank.entity.Employee;
import lk.royalBank.repository.BankAccountRepository;
import lk.royalBank.service.BankAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;
    @Override
    public void addBankAccount(String accountNumber, BankAccountDTO bankAccountDTO) {
        if(!accountNumber.equals(bankAccountDTO.getAccountNumber())){
            throw new RuntimeException("Account Numbers are Not Same");
        }
        System.out.println("Bank");
        BankAccount bankAccount = new BankAccount();
        Client client = new Client();
        Employee employee = new Employee();
        BeanUtils.copyProperties(bankAccountDTO,bankAccount);
        BeanUtils.copyProperties(bankAccountDTO.getClientDTO(),client);
        BeanUtils.copyProperties(bankAccountDTO.getEmployeeDTO(),employee);
        bankAccount.setClient(client);
        bankAccount.setEmployee(employee);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public BankAccountDTO getAccountByID(String accountNumber) {
        if(bankAccountRepository.findById(accountNumber).isPresent()){
            BankAccount bankAccount = bankAccountRepository.findById(accountNumber).get();
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            ClientDTO clientDTO = new ClientDTO();
            BeanUtils.copyProperties(bankAccount,bankAccountDTO);
            BeanUtils.copyProperties(bankAccount.getClient(),clientDTO);
            bankAccountDTO.setClientDTO(clientDTO);
            return bankAccountDTO;
        }else{
            throw new RuntimeException("Invalid Account Number ");
        }
    }

    @Override
    public double checkBalance(String accountNumber) {
        if(bankAccountRepository.findById(accountNumber).isPresent()){
            BankAccount bankAccount = bankAccountRepository.findById(accountNumber).get();
            return bankAccount.getAmount();
//            BankAccountDTO bankAccountDTO = new BankAccountDTO();
//            ClientDTO clientDTO = new ClientDTO();
//            BeanUtils.copyProperties(bankAccount,bankAccountDTO);
//            BeanUtils.copyProperties(bankAccount.getClient(),clientDTO);
//            bankAccountDTO.setClientDTO(clientDTO);
//            return bankAccountDTO.getAmount();
        }else{
            throw new RuntimeException("Invalid Account Number ");
        }
    }

    @Override
    public void doTransAction(String transactionType, String accountNumber,Double amount) {
        double balance = checkBalance(accountNumber);

        if(transactionType.equals("deposit")){
            BankAccount bankAccount = bankAccountRepository.findById(accountNumber).get();
            double newamount = balance + amount;
            bankAccount.setAmount(newamount);

        } else if (transactionType.equals("withdraw")) {
            BankAccount bankAccount = bankAccountRepository.findById(accountNumber).get();
            double newamount = balance - amount;
            bankAccount.setAmount(newamount);

        }



    }
}
