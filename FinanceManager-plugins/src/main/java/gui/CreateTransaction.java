package gui;

import account.Account;
import application.ApplicationManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import exceptions.InvalidAmountException;
import exceptions.InvalidStatementException;
import exceptions.InvalidTransactionTypeException;
import exceptions.NoStatementFoundException;
import transaction.Transaction;
import transaction.TransactionType;

import javax.swing.*;
import java.awt.*;

public class CreateTransaction extends JFrame {
    private JTextField dayTextField;
    private JTextField monthTextField;
    private JTextField yearTextField;
    private JTextField amountTextField;
    private JTextField categoryTextField;
    private JRadioButton debitRadioButton;
    private JRadioButton paymentRadioButton;
    private JButton cancelButton;
    private JButton createButton;
    private JPanel createTransactionPanel;

    private ApplicationManager applicationManager;
    public Account account;

    public CreateTransaction(ApplicationManager applicationManager, Account account) {
        this.applicationManager = applicationManager;
        this.account = account;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FinanceManager");
        setSize(600, 400);
        setVisible(true);
        setContentPane(createTransactionPanel);

        cancelButton.addActionListener(e -> {
            dispose();
            new AccountOverview(applicationManager, account);
        });

        createButton.addActionListener(e -> {
            try {
                TransactionType transactionType;
                if (debitRadioButton.isSelected()) {
                    transactionType = new TransactionType(true, false);

                } else {
                    transactionType = new TransactionType(false, true);
                }
                Transaction transaction;
                if (dayTextField.getText().equals("") | monthTextField.getText().equals("") | yearTextField.getText().equals("")) {
                    transaction = applicationManager.getTransactionManager().createTransaction(categoryTextField.getText(), Double.parseDouble(amountTextField.getText()), transactionType);
                } else {
                    String date = yearTextField.getText() + "/" + monthTextField.getText() + "/" + dayTextField.getText();
                    transaction = applicationManager.getTransactionManager().createTransaction(categoryTextField.getText(), Double.parseDouble(amountTextField.getText()), date, transactionType);
                }
                applicationManager.getTransactionManager().executeTransaction(transaction, account);
                dispose();
                new AccountOverview(applicationManager, account);
            } catch (InvalidTransactionTypeException | InvalidAmountException | NoStatementFoundException | InvalidStatementException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createTransactionPanel = new JPanel();
        createTransactionPanel.setLayout(new GridLayoutManager(11, 7, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Day");
        createTransactionPanel.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        createTransactionPanel.add(spacer1, new GridConstraints(3, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Year");
        createTransactionPanel.add(label2, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dayTextField = new JTextField();
        createTransactionPanel.add(dayTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        monthTextField = new JTextField();
        createTransactionPanel.add(monthTextField, new GridConstraints(2, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        yearTextField = new JTextField();
        createTransactionPanel.add(yearTextField, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Amount");
        createTransactionPanel.add(label3, new GridConstraints(3, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        amountTextField = new JTextField();
        createTransactionPanel.add(amountTextField, new GridConstraints(4, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Category");
        createTransactionPanel.add(label4, new GridConstraints(5, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryTextField = new JTextField();
        createTransactionPanel.add(categoryTextField, new GridConstraints(6, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        debitRadioButton = new JRadioButton();
        debitRadioButton.setText("Debit");
        createTransactionPanel.add(debitRadioButton, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        paymentRadioButton = new JRadioButton();
        paymentRadioButton.setText("Payment");
        createTransactionPanel.add(paymentRadioButton, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Month");
        createTransactionPanel.add(label5, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        createTransactionPanel.add(spacer2, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10), 0, false));
        final Spacer spacer3 = new Spacer();
        createTransactionPanel.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10), 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        createTransactionPanel.add(cancelButton, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createButton = new JButton();
        createButton.setText("Create");
        createTransactionPanel.add(createButton, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        createTransactionPanel.add(spacer4, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10), 0, false));
        final Spacer spacer5 = new Spacer();
        createTransactionPanel.add(spacer5, new GridConstraints(10, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10), 0, false));
        final Spacer spacer6 = new Spacer();
        createTransactionPanel.add(spacer6, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10), 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(debitRadioButton);
        buttonGroup.add(paymentRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return createTransactionPanel;
    }
}
