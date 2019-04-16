package lk.royalBank.service.impl;

import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.dto.DepositDTO;
import lk.royalBank.entity.BankAccount;
import lk.royalBank.entity.Deposit;
import lk.royalBank.repository.DepositRepository;
import lk.royalBank.service.BankAccountService;
import lk.royalBank.service.DepositService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepositServiceImpl implements DepositService {

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    BankAccountService bankAccountService;
    @Override
    public void depositMoney(DepositDTO depositDTO) {

//        Deposit deposit = new Deposit();
//        BankAccount bankAccount = new BankAccount();
//        BeanUtils.copyProperties(depositDTO,deposit);
//        BeanUtils.copyProperties(depositDTO.getBankAccountDTO(),bankAccount);
//        deposit.setBankAccount(bankAccount);
//        depositRepository.save(deposit);


        Deposit deposit = new Deposit();
        BankAccount bankAccount = new BankAccount();

        BeanUtils.copyProperties(depositDTO,deposit);
        BeanUtils.copyProperties(depositDTO.getBankAccountDTO(),bankAccount);
        deposit.setBankAccount(bankAccount);
        depositRepository.save(deposit);

        bankAccountService.doTransAction("deposit",bankAccount.getAccountNumber(),depositDTO.getAmount());

    }
}
