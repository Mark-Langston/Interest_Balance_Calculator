import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Savings Account Balance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout(10, 10));

        // Fun title for the app
        JLabel titleLabel = new JLabel("Mark's Interest Balance Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel initialDepositLabel = new JLabel("Initial Deposit: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(initialDepositLabel, gbc);

        JTextField initialDepositField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(initialDepositField, gbc);

        JLabel annualInterestRateLabel = new JLabel("Annual Interest Rate: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(annualInterestRateLabel, gbc);

        JTextField annualInterestRateField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(annualInterestRateField, gbc);

        JLabel yearsLabel = new JLabel("Number of Years: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(yearsLabel, gbc);

        JTextField yearsField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(yearsField, gbc);

        frame.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton calculateButton = new JButton("Calculate New Balance");
        buttonPanel.add(calculateButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel finalBalanceLabel = new JLabel("Balance: ");
        JLabel finalBalanceValue = new JLabel("$0.00");
        balancePanel.add(finalBalanceLabel);
        balancePanel.add(finalBalanceValue);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(balancePanel, gbc);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double initialDeposit = Double.parseDouble(initialDepositField.getText());
                    double annualInterestRate = Double.parseDouble(annualInterestRateField.getText());
                    int years = Integer.parseInt(yearsField.getText());

                    BankAccount account = new BankAccount(initialDeposit);
                    account.calculateFinalBalance(annualInterestRate, years);

                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                    finalBalanceValue.setText(currencyFormat.format(account.getBalance()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialDeposit) {
        this.balance = initialDeposit;
    }

    public double getBalance() {
        return balance;
    }

    public void calculateFinalBalance(double annualInterestRate, int years) {
        balance *= Math.pow(1 + annualInterestRate / 100, years);
    }
}