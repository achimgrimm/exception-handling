package de.neuland.exception;

public interface BankAccountService {
    double getBalance(String accountId) throws UnknownAccountException;

    String withdraw(String accountId, double balance) throws UnknownAccountException, AccountLimitExceededException;

    static class UnknownAccountException extends Exception {
        public UnknownAccountException(String accountId) {
            super("The account with id " + accountId + " is not known.");
        }
    }

    static class AccountLimitExceededException extends Exception {
        public AccountLimitExceededException(double accountLimit) {
            super("The limit of " + accountLimit + " was exceeded.");
        }
    }
}
