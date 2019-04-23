package lk.royalBank.service.impl;

import lk.royalBank.dto.*;
import lk.royalBank.entity.*;
import lk.royalBank.repository.BankAccountRepository;
import lk.royalBank.service.BankAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            List<WidthdrawDTO> widthdrawDTOS = new ArrayList<>();
            List<DepositDTO> depositDTOS = new ArrayList<>();
            List<SendMoneyDTO> sendMoneyDTOS = new ArrayList<>();

            List<Withdraw> withdraws = bankAccount.getWithdraws();
            Collections.reverse(withdraws);
            withdraws.forEach(withdraw -> {

                WidthdrawDTO widthdrawDTO = new WidthdrawDTO();
                BeanUtils.copyProperties(withdraw,widthdrawDTO);
                widthdrawDTOS.add(widthdrawDTO);
            });

            List<Deposit> deposits = bankAccount.getDeposits();
            Collections.reverse(deposits);
            deposits.forEach(deposit -> {
                DepositDTO depositDTO = new DepositDTO();
                BeanUtils.copyProperties(deposit,depositDTO);
                depositDTOS.add(depositDTO);
            });

            List<SendMoney> sendMonies = bankAccount.getSendMonies();
            Collections.reverse(sendMonies);
            sendMonies.forEach(sendmoney -> {
                SendMoneyDTO sendMoneyDTO = new SendMoneyDTO();
                BeanUtils.copyProperties(sendmoney,sendMoneyDTO);
                sendMoneyDTOS.add(sendMoneyDTO);
            });

            Client client = bankAccount.getClient();
            Branch branch = client.getBranch();
            ClientDTO clientDTO = new ClientDTO();
            BranchDTO branchDTO = new BranchDTO();
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            BeanUtils.copyProperties(bankAccount,bankAccountDTO);
            BeanUtils.copyProperties(client,clientDTO);
            BeanUtils.copyProperties(branch,branchDTO);
            bankAccountDTO.setClientDTO(clientDTO);

            bankAccountDTO.setWidthdrawDTOS(widthdrawDTOS);
            bankAccountDTO.setDepositDTOS(depositDTOS);
            bankAccountDTO.setSendMoneyDTOS(sendMoneyDTOS);
            return bankAccountDTO;
        }
        throw new RuntimeException("Invalid Account Number ");
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
