package com.cloudsparker.personalfinance.domain.accounts;

public class Account {
    private String accountId;
    private String accountName;
    private AccountType accountType;
    private Money balance;

    public Account(String accountId, String accountName, AccountType accountType, Money balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Money getBalance() {
        return balance;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!accountId.equals(account.accountId)) return false;
        if (!accountName.equals(account.accountName)) return false;
        if (accountType != account.accountType) return false;
        return balance.equals(account.balance);
    }

    @Override
    public int hashCode() {
        int result = accountId.hashCode();
        result = 31 * result + accountName.hashCode();
        result = 31 * result + accountType.hashCode();
        result = 31 * result + balance.hashCode();
        return result;
    }
}
