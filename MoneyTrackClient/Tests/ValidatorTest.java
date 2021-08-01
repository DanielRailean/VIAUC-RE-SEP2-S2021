
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import mvvm.model.Validator;

import static org.junit.jupiter.api.Assertions.*;
public class ValidatorTest {
    private static Validator validator;
    @BeforeAll public static void setup(){
        validator = new Validator();
    }

    @Test public void passwordTest(){
        assertFalse(validator.isValidPassword(""));
        assertFalse(validator.isValidPassword("pass"));
        assertFalse(validator.isValidPassword("passsssssssssssssssssssssssssss"));
        assertTrue(validator.isValidPassword("passssssssssssssssssssssssssss"));
        assertTrue(validator.isValidPassword("passs"));
    }

    @Test public void emailTest(){
        assertFalse(validator.isValidEmail(""));
        assertFalse(validator.isValidEmail("email"));
        assertFalse(validator.isValidEmail("email@"));
        assertFalse(validator.isValidEmail("email@com"));
        assertFalse(validator.isValidEmail("email@.com"));
        assertTrue(validator.isValidEmail("email@domain.com"));
    }

    @Test public void descriptionsTest(){
        String invalid51 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String invalid102 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String valid50 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2";

        assertTrue(validator.isValidExpenseDescription(""));
        assertTrue(validator.isValidExpenseDescription("d"));
        assertTrue(validator.isValidExpenseDescription(valid50));
        assertFalse(validator.isValidExpenseDescription(invalid51));
        assertFalse(validator.isValidExpenseDescription(invalid102));

        assertTrue(validator.isValidIncomeDescription(""));
        assertTrue(validator.isValidIncomeDescription("d"));
        assertTrue(validator.isValidIncomeDescription(valid50));
        assertFalse(validator.isValidIncomeDescription(invalid51));
        assertFalse(validator.isValidIncomeDescription(invalid102));
    }

    @Test public void amountTest(){
        assertFalse(validator.isValidIncome(-1));
        assertFalse(validator.isValidIncome(-10));
        assertFalse(validator.isValidIncome(10000001));
        assertFalse(validator.isValidIncome(100000000));
        assertTrue(validator.isValidIncome(10));
        assertTrue(validator.isValidIncome(0));
        assertTrue(validator.isValidIncome(10000000));

        assertFalse(validator.isValidExpense(-1));
        assertFalse(validator.isValidExpense(-10));
        assertFalse(validator.isValidExpense(10000001));
        assertFalse(validator.isValidExpense(100000000));
        assertTrue(validator.isValidExpense(10));
        assertTrue(validator.isValidExpense(0));
        assertTrue(validator.isValidExpense(10000000));

        assertFalse(validator.isValidBalance(-1));
        assertFalse(validator.isValidBalance(-10));
        assertFalse(validator.isValidBalance(10000001));
        assertFalse(validator.isValidBalance(100000000));
        assertTrue(validator.isValidBalance(10));
        assertTrue(validator.isValidBalance(0));
        assertTrue(validator.isValidBalance(10000000));

        assertFalse(validator.isValidPriceInEur(-1));
        assertFalse(validator.isValidPriceInEur(-10));
        assertFalse(validator.isValidPriceInEur(10000001));
        assertFalse(validator.isValidPriceInEur(100000000));
        assertTrue(validator.isValidPriceInEur(10));
        assertTrue(validator.isValidPriceInEur(0));
        assertTrue(validator.isValidPriceInEur(10000000));
    }

    @Test public void nameTest(){
        String invalid51 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String invalid102 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String valid50 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2";
        String invalid21 = "_K[dTt9%M[K)3%H4#*.91";
        String valid20 = "_K[dTt9%M[K)3%H4#*.9";
        String invalid11 = "12345678901";
        String valid10 = "1234567890";

        assertTrue(validator.isValidAccountName(""));
        assertTrue(validator.isValidAccountName("d"));
        assertTrue(validator.isValidAccountName(valid20));
        assertFalse(validator.isValidAccountName(invalid21));
        assertFalse(validator.isValidAccountName(invalid51));

        assertTrue(validator.isValidCurrencyName(""));
        assertTrue(validator.isValidCurrencyName("d"));
        assertTrue(validator.isValidCurrencyName(valid10));
        assertFalse(validator.isValidCurrencyName(invalid11));
        assertFalse(validator.isValidCurrencyName(invalid102));

        assertTrue(validator.isValidCategoryName(""));
        assertTrue(validator.isValidCategoryName("d"));
        assertTrue(validator.isValidCategoryName(valid50));
        assertFalse(validator.isValidCategoryName(invalid51));
        assertFalse(validator.isValidCategoryName(invalid102));
    }
}
