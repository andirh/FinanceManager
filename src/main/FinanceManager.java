package main;

import main.domain.account.Account;
import main.domain.account.AccountManager;
import main.domain.account.Owner;

import java.io.File;
import java.util.Objects;

public class FinanceManager {

    private static final String NAME = "SPK Giro";
    private static final Owner OWNER = new Owner("Andreas", "Rheinwalt");

    public static void main(String args[]){

        File accountFiles = new File("src/resources/account");
        AccountManager accountManager = new AccountManager();
        File accountDetails = null;
        for (File file : Objects.requireNonNull(accountFiles.listFiles())) {
            if (NAME.equalsIgnoreCase(file.getName())){
                accountDetails = file;
                break;
            }
        }

        if (accountDetails != null){
            accountManager.loadExistingAccount(accountDetails);
        } else {
            accountManager.createAccount(NAME, OWNER, 666);
        }

    }


}
