package com.example.demo.service;

import com.example.demo.dataclasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class ScheduleService {

    Date todaysDate = new Date();
    DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
    String strDate = df2.format(todaysDate);

    @Autowired
    private Repository repository;

    Employee employee = new Employee();


    @Transactional
    public String createEmployee(String name, String idNumber, String departmentCode,
                                    BigDecimal hourlyPay, int salaryCode, String password) {
        repository.cre (name, idNumber, departmentCode, hourlyPay, salaryCode, password);
        return "New employee " +employee.getName()+" has been created.";
    }
/*
    @Transactional
    public String createCustomerAccount(Accounts bankAccount) {
        bankRepository.createCustomerAccount(bankAccount);
        return "New account for ID " + bankAccount.getAccountCustId() + " has been created.\n" +
                "This account is " + bankAccount.getCustAccType() + ".";
    }


    public int accountBalance(String account_nr) {
        return bankRepository.accountBalance(account_nr);
    }

    public String userPassword(String cust_id) {
        return bankRepository.userPassword(cust_id);
    }


    @Transactional
    public String depositMoney(String accountNrTo, Integer amount) {
        int newAccBalanceTo;
        if (amount < 0)
            throw new MyException("Check your amount input, cant add negative amount");
        else {
            int oldAccBalance = bankRepository.accountBalance(accountNrTo);
            newAccBalanceTo = oldAccBalance + amount;
            bankRepository.updateAccountBalance(accountNrTo, newAccBalanceTo);
            bankRepository.dataToTransHistory(null, accountNrTo, "Money deposit to account", amount, newAccBalanceTo, strDate);
        }
        return amount + " was deposited to account nr: " + accountNrTo + ". The new balance is " + newAccBalanceTo;
    }

    @Transactional
    public String withdrawMoney(String accountFrom, Integer amount) {
        int newAccBalanceFrom;
        if (amount <= 0) {
            throw new MyException("Check your amount input, cant add negative amount");
        }
        int oldAccBalanceFrom = bankRepository.accountBalance(accountFrom);
        if ((oldAccBalanceFrom - amount) < 0) {
            throw new MyException("There is not enough cash in the account");
        } else {
            newAccBalanceFrom = oldAccBalanceFrom - amount;
            bankRepository.updateAccountBalance(accountFrom, newAccBalanceFrom);
            bankRepository.dataToTransHistory(accountFrom, null, "Money withrawal from account", -amount, newAccBalanceFrom, strDate);
        }
        return "New balance for account nr: " + accountFrom + " is: " + newAccBalanceFrom;
    }

    @Transactional
    public String transferMoney(String accountFrom, String accountTo, int amount) {
        int newAccBalanceFrom;
        int newAccBalanceTo;
        if (amount <= 0)
            throw new MyException("Check your amount input, cant add negative amount");
        int oldAccBalance = bankRepository.accountBalance(accountFrom);
        if ((oldAccBalance - amount) < 0) {
            throw new MyException("There is not enough cash in the account");
        } else {
            newAccBalanceFrom = oldAccBalance - amount;
            bankRepository.updateAccountBalance(accountFrom, newAccBalanceFrom);
            bankRepository.dataToTransHistory(accountFrom, accountTo, "FROM acc to acc transfer", -amount, newAccBalanceFrom, strDate);
            int oldAccBalanceTo = bankRepository.accountBalance(accountTo);
            newAccBalanceTo = oldAccBalanceTo + amount;
            bankRepository.updateAccountBalance(accountTo, newAccBalanceTo);
            bankRepository.dataToTransHistory(accountFrom, accountTo, "TO acc to acc transfer", amount, newAccBalanceTo, strDate);
        }
        return "The balance after transactions is: \n " +
                "for the account: " + accountFrom + ", the balance after outgoing transfer is: " + newAccBalanceFrom + ".\n" +
                " For the account: " + accountTo + ", the balance after incoming transfer is: " + newAccBalanceTo;
    }

    public List<CustomerData> getCustomerList() {
        return bankRepository.bankCustomers();
    }

    public List<Accounts> getAccounts() {
        return bankRepository.bankAccounts();
    }

    public List<Accounts> getTransactionHistory() {
        return bankRepository.transactionHistory();
    }

    public List<Accounts> getcustomerTransactionHistory(String custAccNr) {
        return bankRepository.customerTransactionHistory(custAccNr);
    }

 */
}

