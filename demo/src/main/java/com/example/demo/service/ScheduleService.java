package com.example.demo.service;

import com.example.demo.dataclasses.Schedule;
import com.example.demo.repositories.ScheduleRepository;
import errorHandling.ScheduleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ScheduleService {

    Date todaysDate = new Date();
    DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
    String strDate = df2.format(todaysDate);

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Transactional
    public String createEmployee(String idNumber, String name, String departmentCode,
                                    BigDecimal hourlyPay, int salaryCode, String password) {
        scheduleRepository.createEmployee(idNumber, name, departmentCode, hourlyPay, salaryCode, password);
        return "New employee has been created.";
    }

    @Transactional
    public void createSchedule(String name, Date date, Time startTime, Time endTime) {
        String id = scheduleRepository.getEmployeeId(name);
        if (id==null) {
            throw new ScheduleException("No such name");
        } else {
            scheduleRepository.createSchedule(id, date, startTime, endTime);
        }
    }

    public String getEmployeeId(String name) {
        return scheduleRepository.getEmployeeId(name);
    }

    public List<Schedule> getEmployeeScheduleData(String name, Calendar dateFrom, Calendar dateTo){
        String id = scheduleRepository.getEmployeeId(name);
        List scheduleResult = new ArrayList();
        while (dateFrom.compareTo(dateTo) != 0) {
            if(scheduleRepository.getEmployeeScheduleData(id, dateFrom)!= null){
//on see õige asi? Tuleb siit "if"-ist tulemus "null", kui kodanik sel kuupäeval tööl ei olnud?
            scheduleResult.add(scheduleRepository.getEmployeeScheduleData(id, dateFrom));
            dateFrom.add(Calendar.DAY_OF_MONTH, 1);
        }}
//kas saab üldse listi laadida fronti/Vuesse?
//Mul on tunne, et olen väga valel teel
        return scheduleResult;
    }



/*

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

