
import account.AccountRepository;
import application.ApplicationManager;
import gui.AccountSelection;
import repositories.AccountRepositoryImpl;
import repositories.StatementRepositoryImpl;
import statement.StatementRepository;

public class FinanceManager {

    public static void main(String args[]) {

        AccountRepository accountRepository = new AccountRepositoryImpl();
        StatementRepository statementRepository = new StatementRepositoryImpl();
        ApplicationManager applicationManager = new ApplicationManager(statementRepository, accountRepository);
        applicationManager.init();

        AccountSelection accountSelectionGui = new AccountSelection(applicationManager);


    }


}

