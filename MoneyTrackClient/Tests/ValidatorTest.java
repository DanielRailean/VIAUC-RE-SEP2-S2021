
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import mvvm.model.Validator;

import static org.junit.jupiter.api.Assertions.*;
public class ValidatorTest {

    @BeforeAll public static void setup(){
        //no setup required
    }

    @Test public void passwordTest(){
        assertFalse(Validator.isValidPassword(""));
        assertFalse(Validator.isValidPassword("pass"));
        assertFalse(Validator.isValidPassword("passsssssssssssssssssssssssssss"));
        assertTrue(Validator.isValidPassword("passssssssssssssssssssssssssss"));
        assertTrue(Validator.isValidPassword("passs"));
    }

    @Test public void emailTest(){
        assertFalse(Validator.isValidEmail(""));
        assertFalse(Validator.isValidEmail("email"));
        assertFalse(Validator.isValidEmail("email@"));
        assertFalse(Validator.isValidEmail("email@com"));
        assertFalse(Validator.isValidEmail("email@.com"));
        assertTrue(Validator.isValidEmail("email@domain.com"));
    }
    
    @Test public void adminEmailTest(){
        assertFalse(Validator.isValidAdminEmail(""));
        assertFalse(Validator.isValidAdminEmail("email"));
        assertFalse(Validator.isValidAdminEmail("email@"));
        assertFalse(Validator.isValidAdminEmail("email@com"));
        assertFalse(Validator.isValidAdminEmail("email@.com"));
        assertFalse(Validator.isValidAdminEmail("email@domain.com"));
        assertFalse(Validator.isValidAdminEmail("email@any.mt"));
        assertFalse(Validator.isValidAdminEmail("@mt.com@mt.com"));
        assertTrue(Validator.isValidAdminEmail("me@mt.com"));
        assertTrue(Validator.isValidAdminEmail("dd@mt.com"));
        assertTrue(Validator.isValidAdminEmail("matt@mt.com"));
        assertTrue(Validator.isValidAdminEmail("joseph@mt.com"));
    }

    @Test public void descriptionsTest(){
        String invalid51 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String invalid102 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String valid50 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2";

        assertTrue(Validator.isValidExpenseDescription(""));
        assertTrue(Validator.isValidExpenseDescription("d"));
        assertTrue(Validator.isValidExpenseDescription(valid50));
        assertFalse(Validator.isValidExpenseDescription(invalid51));
        assertFalse(Validator.isValidExpenseDescription(invalid102));

        assertTrue(Validator.isValidIncomeDescription(""));
        assertTrue(Validator.isValidIncomeDescription("d"));
        assertTrue(Validator.isValidIncomeDescription(valid50));
        assertFalse(Validator.isValidIncomeDescription(invalid51));
        assertFalse(Validator.isValidIncomeDescription(invalid102));
    }

    @Test public void amountTest(){
        assertFalse(Validator.isValidIncome(-1));
        assertFalse(Validator.isValidIncome(-10));
        assertFalse(Validator.isValidIncome(10000001));
        assertFalse(Validator.isValidIncome(100000000));
        assertTrue(Validator.isValidIncome(10));
        assertTrue(Validator.isValidIncome(0));
        assertTrue(Validator.isValidIncome(10000000));

        assertFalse(Validator.isValidExpense(-1));
        assertFalse(Validator.isValidExpense(-10));
        assertFalse(Validator.isValidExpense(10000001));
        assertFalse(Validator.isValidExpense(100000000));
        assertTrue(Validator.isValidExpense(10));
        assertTrue(Validator.isValidExpense(0));
        assertTrue(Validator.isValidExpense(10000000));

        assertFalse(Validator.isValidBalance(-1));
        assertFalse(Validator.isValidBalance(-10));
        assertFalse(Validator.isValidBalance(10000001));
        assertFalse(Validator.isValidBalance(100000000));
        assertTrue(Validator.isValidBalance(10));
        assertTrue(Validator.isValidBalance(0));
        assertTrue(Validator.isValidBalance(10000000));

        assertFalse(Validator.isValidPriceInEur(-1));
        assertFalse(Validator.isValidPriceInEur(-10));
        assertFalse(Validator.isValidPriceInEur(10000001));
        assertFalse(Validator.isValidPriceInEur(100000000));
        assertTrue(Validator.isValidPriceInEur(10));
        assertTrue(Validator.isValidPriceInEur(0));
        assertTrue(Validator.isValidPriceInEur(10000000));
    }

    @Test public void nameTest(){
        String invalid51 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String invalid102 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2=";
        String valid50 = "J%GnvT@N8LMS=K!wY$;zcPTT!:m&&phd)Hch7MC2J[.[RBemA2";
        String invalid21 = "_K[dTt9%M[K)3%H4#*.91";
        String valid20 = "_K[dTt9%M[K)3%H4#*.9";
        String invalid11 = "12345678901";
        String valid10 = "1234567890";

        assertTrue(Validator.isValidAccountName(""));
        assertTrue(Validator.isValidAccountName("d"));
        assertTrue(Validator.isValidAccountName(valid20));
        assertFalse(Validator.isValidAccountName(invalid21));
        assertFalse(Validator.isValidAccountName(invalid51));

        assertTrue(Validator.isValidCurrencyName(""));
        assertTrue(Validator.isValidCurrencyName("d"));
        assertTrue(Validator.isValidCurrencyName(valid10));
        assertFalse(Validator.isValidCurrencyName(invalid11));
        assertFalse(Validator.isValidCurrencyName(invalid102));

        assertTrue(Validator.isValidCategoryName(""));
        assertTrue(Validator.isValidCategoryName("d"));
        assertTrue(Validator.isValidCategoryName(valid50));
        assertFalse(Validator.isValidCategoryName(invalid51));
        assertFalse(Validator.isValidCategoryName(invalid102));
    }
}
