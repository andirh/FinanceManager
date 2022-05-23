import main.domain.account.AccountManager;
import main.domain.account.Owner;

import javax.security.auth.login.AccountException;
import java.io.File;
import java.util.Objects;

public class FinanceManager {

    //TODO: Plugins: Speichern / Laden - Adapters: CSV Objekt erstellen (CSV String) - Application: Abrechnungen generieren, Buchungen durchführen - Domain: Interfaces für repos Account repo transaction repo statement repo

    private static final String NAME = "SPK Giro";
    private static final Owner OWNER = new Owner("Andreas", "Rheinwalt");

    public static void main(String args[]){

        File accountFiles = new File("src/resources/account");
        AccountManager accountManager = new AccountManager();
        File accountDetails = null;
        for (File file : Objects.requireNonNull(accountFiles.listFiles())) {
            if ((NAME + ".csv").equalsIgnoreCase(file.getName())){
                accountDetails = file;
                break;
            }
        }

        if (accountDetails != null){
            try {
                accountManager.loadExistingAccount(accountDetails);
            } catch (AccountException e) {
                accountManager.createAccount(NAME, OWNER);
            }
        } else {
            accountManager.createAccount(NAME, OWNER);
        }

    }


}
