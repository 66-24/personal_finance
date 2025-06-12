package com.cloudsparker.personalfinance.domain.accounts;


import static org.assertj.core.api.Assertions.*;

import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AccountTest {
    @Test
    void testEquals() {

        Account scrooge = new Account("C124", "Scrooge Inc.", AccountType.SAVINGS, new Money(100, Currency.getInstance("USD")));
                
        assertThat(scrooge).isEqualTo(new Account("C124", "Scrooge Inc.", AccountType.SAVINGS, new Money(100, Currency.getInstance("USD"))));

    }

    @ParameterizedTest
    @CsvSource({
        "C125, Scrooge Inc., SAVINGS, 100",
        "C124, Scrooge Inc., CHECKING, 100", 
        "C124, Scrooge Inc., SAVINGS, 200"

    })
    void testNotEquals(String id, String name, AccountType type, int amount) {

        Account scrooge = new Account("C124", "Scrooge Inc.", AccountType.SAVINGS, new Money(100, Currency.getInstance("USD")));
        Account goofy = new Account(id, name, type, new Money(amount, Currency.getInstance("USD")));

        assertThat(scrooge).isNotEqualTo(goofy);
    }
}
