package com.cloudsparker.personalfinance.domain.accounts;

import java.util.Currency;

public class Money {
    private final double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    public double getAmount() {
        return amount;
    }
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", amount, currency.getSymbol());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money money = (Money) o;

        if (Double.compare(money.amount, amount) != 0) return false;
        return currency.equals(money.currency);
    }
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + currency.hashCode();
        return result;
    }
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add Money with different currencies");
        }
        return new Money(this.amount + other.amount, this.currency);
    }
    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract Money with different currencies");
        }
        return new Money(this.amount - other.amount, this.currency);
    }
    
}
