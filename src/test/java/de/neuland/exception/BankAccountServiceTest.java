package de.neuland.exception;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


public class BankAccountServiceTest {
    public static final String ACCOUNT_ID = "4711";
    private BankAccountServiceImpl bankAccountService;

    @Before
    public void setUp() throws Exception {
        bankAccountService = new BankAccountServiceImpl();
    }

    @Test
    public void shouldGetBalanceForAccount() throws Exception {
        // given
        bankAccountService.addAccount(ACCOUNT_ID, 20.00);
        // when
        double balance = bankAccountService.getBalance(ACCOUNT_ID);
        // then
        assertEquals(20.00, balance);
    }

    @Test(expected = BankAccountService.UnknownAccountException.class)
    public void shouldNotFindAccountToGetBlance() throws Exception {
        // when
        bankAccountService.getBalance("someRandomAccountId");
    }

    @Test
    public void shouldWithdrawFromAccount() throws Exception {
        // given
        bankAccountService.addAccount(ACCOUNT_ID, 20.00);
        // when
        String transactionId = bankAccountService.withdraw(ACCOUNT_ID, 5.00);
        // then
        assertNotNull(transactionId);
    }

    @Test(expected = BankAccountService.UnknownAccountException.class)
    public void shouldNotFindAccountToWithdrawFrom() throws Exception {
        // when
        bankAccountService.withdraw("someRandomAccountId", 20.00);
    }

    @Test(expected = BankAccountService.AccountLimitExceededException.class)
    public void shouldExceedAccountLimit() throws Exception {
        // given
        bankAccountService.addAccount(ACCOUNT_ID, 20.00);
        // when
        bankAccountService.withdraw(ACCOUNT_ID, 100.00);
    }
}
