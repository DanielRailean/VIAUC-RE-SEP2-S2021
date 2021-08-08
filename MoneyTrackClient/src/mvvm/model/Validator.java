package mvvm.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static final int MIN_PASSWORD =5;
    public static final int MAX_PASSWORD =30;
    public static final int MAX_EXPENSE_DESCRIPTION = 50;
    public static final int MAX_INCOME_DESCRIPTION = 50;
    public static final long MAX_EXPENSE = 10000000;
    public static final long MAX_INCOME = 10000000;
    public static final long MAX_BUDGET = 10000000;
    public static final long MAX_START_BALANCE = 10000000;
    public static final long MAX_PRICE_IN_EUR = 10000000;
    public static final int MAX_CURRENCY_NAME = 10;
    public static final int MAX_CATEGORY_NAME = 50;
    public static final int MAX_ACCOUNT_NAME = 20;
    public static final String ADMIN_DOMAIN = "@mt.com";
    public static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static boolean isValidPassword(String password){
        return password.length()>=MIN_PASSWORD&&password.length()<=MAX_PASSWORD;
    }
    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidAdminEmail(String email){
        return isValidEmail(email)&&email.contains(ADMIN_DOMAIN);
    }

    public static boolean isValidIncome(float amount){
        return amount>=0&&amount<=MAX_INCOME;
    };
    public static boolean isValidExpense(float amount){
        return amount>=0&&amount<=MAX_EXPENSE;

    };
    public static boolean isValidBudgetAmount(float amount){
        return amount>=0&&amount<=MAX_BUDGET;

    };
    public static boolean isValidBalance(float amount){
        return amount>=0&&amount<=MAX_START_BALANCE;
    };
    public static boolean isValidPriceInEur(double amount){
        return amount>=0&&amount<=MAX_PRICE_IN_EUR;

    };

    public static boolean isValidExpenseDescription(String text){
        return text.length()<=MAX_EXPENSE_DESCRIPTION;
    }
    public static boolean isValidIncomeDescription(String text){
        return text.length()>=0&&text.length()<=MAX_INCOME_DESCRIPTION;
    }
    public static boolean isValidCurrencyName(String text){
        return text.length()>=0&&text.length()<=MAX_CURRENCY_NAME;
    }
    public static boolean isValidCategoryName(String text){
        return text.length()>=0&&text.length()<=MAX_CATEGORY_NAME;
    }
    public static boolean isValidAccountName(String text){
        return text.length()>=0&&text.length()<=MAX_ACCOUNT_NAME;
    }

}
