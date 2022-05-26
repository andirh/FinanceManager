package mapping;

import account.Account;
import account.Owner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AccountDataMapper {

    public List<Account> extractAccounts(List<String[]> accountData) {
        List<Account> accounts = new ArrayList<>();
        for (String[] accountInformation : accountData) {
            Owner owner = new Owner(accountInformation[1], accountInformation[2]);
            accounts.add(new Account(accountInformation[0], owner, Double.parseDouble(accountInformation[3]), Integer.parseInt(accountInformation[4])));
        }
        return accounts;
    }


}
