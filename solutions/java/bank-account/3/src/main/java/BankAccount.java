class BankAccount {
    private boolean opened = false;
    private int balance = 0;

    void open() throws BankAccountActionInvalidException {
        if (opened) {
            throw new BankAccountActionInvalidException("Account already open");
        }
        opened = true;
        balance = 0;
    }

    void close() throws BankAccountActionInvalidException {
        if (!opened) {
            throw new BankAccountActionInvalidException("Account not open");
        }
        opened = false;
    }

    synchronized int getBalance() throws BankAccountActionInvalidException {
        if (!opened) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        return balance;
    }

    synchronized void deposit(final int amount) throws BankAccountActionInvalidException {
        if (!opened) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        if (amount < 0) {
            throw new BankAccountActionInvalidException(
                    "Cannot deposit or withdraw negative amount");
        }
        balance += amount;
    }

    synchronized void withdraw(final int amount) throws BankAccountActionInvalidException {
        if (!opened) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        if (amount < 0) {
            throw new BankAccountActionInvalidException(
                    "Cannot deposit or withdraw negative amount");
        }
        if (amount > balance) {
            throw new BankAccountActionInvalidException(
                    "Cannot withdraw more money than is currently in the account");
        }
        balance -= amount;
    }
}
