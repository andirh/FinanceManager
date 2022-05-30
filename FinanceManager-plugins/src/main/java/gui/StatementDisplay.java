package gui;

import account.Account;
import application.ApplicationManager;
import com.intellij.uiDesigner.core.GridLayoutManager;
import statement.Statement;

import javax.swing.*;
import java.awt.*;

public class StatementDisplay extends JFrame {

    private ApplicationManager applicationManager;
    public Account account;
    private JPanel statementDisplay;


    public StatementDisplay(ApplicationManager applicationManager, Account account, Statement statement) {
        this.applicationManager = applicationManager;
        this.account = account;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FinanceManager");
        setSize(400, 400);
        setVisible(true);
        setContentPane(statementDisplay);
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
        statementDisplay = new JPanel();
        statementDisplay.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return statementDisplay;
    }
}
