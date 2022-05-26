package account;

import javax.security.auth.login.AccountException;
import java.io.File;

//TODO: Application oder Transform layer

public class AccountManager {

//gets list of accounts and selects right one

    public AccountManager() {
    }

    public Account loadAccount(String accountName, Owner owner, double balance, int id){
        return null;
    }

    public Account createAccount(String accountName, Owner owner){
        return null;
    }

    public Account loadExistingAccount(File accountDetails) throws AccountException {
        return null;
    }

}
