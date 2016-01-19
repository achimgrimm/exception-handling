package de.neuland.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class BankAccountServiceImpl implements BankAccountService {
    private Map<String, Double> accounts = new HashMap<String, Double>();

    @Override public double getBalance(String accountId) throws UnknownAccountException {
        if (accounts.containsKey(accountId)) {
            return accounts.get(accountId);
        } else {
            throw new UnknownAccountException(accountId);
        }
    }

    @Override public String withdraw(String accountId, double amount) throws UnknownAccountException, AccountLimitExceededException {
        double balance = getBalance(accountId);
        if (balance < amount) {
            throw new AccountLimitExceededException(amount);
        }

        accounts.put(accountId, balance - amount);
        return UUID.randomUUID().toString();
    }

    void addAccount(String accountId, double balance) {
        accounts.put(accountId, balance);
    }
}
