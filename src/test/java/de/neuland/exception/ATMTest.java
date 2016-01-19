package de.neuland.exception;

import org.junit.Before;
import org.junit.Test;


public class ATMTest {
    public static final String ACCOUNT_ID = "4711";
    private ATM atm;

    @Before
    public void setUp() throws Exception {
        BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();
        bankAccountService.addAccount(ACCOUNT_ID, 100.00);

        atm = new ATM();
        atm.setBankAccountService(bankAccountService);
    }

    @Test
    public void shouldPrintBalance() throws Exception {
        atm.printBalance(ACCOUNT_ID);
    }

    @Test
    public void shouldWithdrawFromAccount() throws Exception {
        atm.withdraw(ACCOUNT_ID, 5.00);
    }

    @Test
    public void shouldExceedLimit() throws Exception {
        atm.withdraw(ACCOUNT_ID, 200.00);
    }

    @Test
    public void shouldPrintErrorForUnknownAccount() throws Exception {
        atm.printBalance("666");
    }

    @Test
    public void shouldNotWithdrawFromUnknownAccount() throws Exception {
        atm.withdraw("666", 5.00);
    }
}
