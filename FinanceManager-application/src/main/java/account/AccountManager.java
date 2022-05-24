package account;
/*
import javax.security.auth.login.AccountException;
import java.io.File;

//TODO: Application oder Transform layer

public class AccountManager {

    private final FileWriter fileWriter;
    private final FileReader fileReader;

    public AccountManager() {
        fileWriter = new CsvFileWriter();
        fileReader = new CsvFileReader();
    }

    public Account loadAccount(String accountName, Owner owner, double balance, int id){
        Account account = new Account(accountName, owner, balance, id);
        fileWriter.reportAccountDetails(account);
        return account;
    }

    public Account createAccount(String accountName, Owner owner){
        Account account = new Account(accountName, owner);
        fileWriter.reportAccountDetails(account);
        return account;
    }

    public Account loadExistingAccount(File accountDetails) throws AccountException {
        return fileReader.extractAccountDetails(accountDetails);
    }

}
*/