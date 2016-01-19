package de.neuland.exception;

public class ATM {
    private BankAccountService bankAccountService;

    public void printBalance(String accountId) {
        try {
            System.out.println("The account " + accountId + " has a current balance of " + bankAccountService.getBalance(accountId) + ".");
        } catch (BankAccountService.UnknownAccountException e) {
            System.out.println("The account is not known to the system!");
        }
    }

    public void withdraw(String accountId, double amount) {
        try {
            bankAccountService.withdraw(accountId, amount);
            System.out.println("Please take the money and enjoy. :)");
        } catch (BankAccountService.UnknownAccountException e) {
            System.out.println("The account is not known to the system!");
        } catch (BankAccountService.AccountLimitExceededException e) {
            System.out.println("The account limit is exceeded.");
            printBalance(accountId);
        }
    }

    public void setBankAccountService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
}
