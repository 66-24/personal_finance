package com.cloudsparker.personalfinance.domain.accounts;

import static org.assertj.core.api.Assertions.*;

import java.util.Currency;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AccountTest {
    @Test
    void testEquals() {

        Account scrooge = new Account("C124", "Scrooge Inc.", AccountType.SAVINGS,
                new Money(100, Currency.getInstance("USD")));

        assertThat(scrooge).isEqualTo(
                new Account("C124", "Scrooge Inc.", AccountType.SAVINGS, new Money(100, Currency.getInstance("USD"))));

    }

    @ParameterizedTest
    @CsvSource({
            "C125, Scrooge Inc., SAVINGS, 100",
            "C124, Scrooge Inc., CHECKING, 100",
            "C124, Scrooge Inc., SAVINGS, 200"

    })
    void testNotEquals(String id, String name, AccountType type, int amount) {

        Account scrooge = new Account("C124", "Scrooge Inc.", AccountType.SAVINGS,
                new Money(100, Currency.getInstance("USD")));
        Account goofy = new Account(id, name, type, new Money(amount, Currency.getInstance("USD")));

        assertThat(scrooge).isNotEqualTo(goofy);
    }


    @ParameterizedTest
    @ValueSource(ints = { 1000, 5000, 10000 })
    void testHashCodeCollisionRate(int accountCount) {
        Set<Account> accounts = generateManyAccounts(accountCount);
        Map<Integer, Long> hashFrequency = accounts.stream()
                .collect(Collectors.groupingBy(Account::hashCode, Collectors.counting()));

        long collisions = hashFrequency.values().stream()
                .mapToLong(count -> count - 1)
                .sum();

        double collisionRate = (double) collisions / accountCount;
        // Less than 5% collision rate is generally good
        assertThat(collisionRate).isLessThan(0.05);
    }

    private Set<Account> generateManyAccounts(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> 
                        new Account("C" + i, 
                                    "Account" + i, 
                                    i % 2 == 0 ? AccountType.SAVINGS : AccountType.CHECKING, 
                                    new Money(i * 100, Currency.getInstance("USD")))
                    ).collect(Collectors.toSet());
    }
}