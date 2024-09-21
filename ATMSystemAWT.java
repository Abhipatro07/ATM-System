import java.awt.*;
import java.awt.event.*;


class BankAccount {
    private double balance;


    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }


    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance to withdraw.");
        } else if (amount > 0) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Withdraw amount must be positive.");
        }
    }

    public double checkBalance() {
        return balance;
    }
}


class ATM extends Frame implements ActionListener {
    private BankAccount account;
    private TextField amountField;
    private Label balanceLabel, statusLabel;


    public ATM(BankAccount account) {
        this.account = account;

        setLayout(new GridLayout(5, 2, 10, 10));


        balanceLabel = new Label("Balance: " + account.checkBalance());
        add(balanceLabel);
        add(new Label(""));  


        add(new Label("Amount: "));
        amountField = new TextField();
        add(amountField);


        Button withdrawButton = new Button("Withdraw");
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        Button depositButton = new Button("Deposit");
        depositButton.addActionListener(this);
        add(depositButton);

        Button checkBalanceButton = new Button("Check Balance");
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        Button exitButton = new Button("Exit");
        exitButton.addActionListener(this);
        add(exitButton);

        statusLabel = new Label("");
        statusLabel.setForeground(Color.RED);
        add(statusLabel);

        setTitle("ATM System");
        setSize(400, 300);
        setVisible(true);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            double amount = Double.parseDouble(amountField.getText());

            if (command.equals("Withdraw")) {
                if (amount <= account.checkBalance() && amount > 0) {
                    account.withdraw(amount);
                    statusLabel.setText("Withdrawn: " + amount);
                } else if (amount <= 0) {
                    statusLabel.setText("Amount must be positive.");
                } else {
                    statusLabel.setText("Insufficient balance.");
                }
            } else if (command.equals("Deposit")) {
                if (amount > 0) {
                    account.deposit(amount);
                    statusLabel.setText("Deposited: " + amount);
                } else {
                    statusLabel.setText("Amount must be positive.");
                }
            } else if (command.equals("Check Balance")) {
                statusLabel.setText("Current Balance: " + account.checkBalance());
            } else if (command.equals("Exit")) {
                dispose();
            }


            balanceLabel.setText("Balance: " + account.checkBalance());
            amountField.setText("");

        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid amount. Please enter a number.");
        }
    }
}


public class ATMSystemAWT {
    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(10000);


        new ATM(userAccount);
    }
}
