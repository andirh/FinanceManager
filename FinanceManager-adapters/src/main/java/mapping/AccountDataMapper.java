package mapping;

import account.Account;
import account.AccountBuilder;
import account.Owner;

import java.util.ArrayList;
import java.util.List;

public class AccountDataMapper {


    public List<Account> extractAccounts(List<String[]> accountData) {
        AccountBuilder accountBuilder = new AccountBuilder();
        List<Account> accounts = new ArrayList<>();
        for (String[] accountInformation : accountData) {
            Owner owner = new Owner(accountInformation[1], accountInformation[2]);
            accounts.add(accountBuilder.accountName(accountInformation[0]).owner(owner)
                    .balance(Double.parseDouble(accountInformation[3])).id(Long.parseLong(accountInformation[4]))
                    .build());
        }
        return accounts;
    }

    public String mapAccountToCsvString(Account account){
        return account.getAccountName() + "," + account.getOwner().getFullName() + "," + account.getBalance() + "," + account.getId();
    }

    public String extractAccountNameFromAccount(Account account){
        return account.getAccountName();
    }

}
