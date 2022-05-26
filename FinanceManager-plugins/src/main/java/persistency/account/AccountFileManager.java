package persistency.account;

import exceptions.AccountAlreadyExistsException;
import exceptions.InvalidAccountException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountFileManager {

    public List<String[]> getAccountDataFromFile() throws InvalidAccountException {
        File files = new File("src/resources/account-repository");
        List<String[]> accountFiles = new ArrayList<>();
        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (file.getName().endsWith(".csv")) {
                try {
                    BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
                    String[] accountDetails = reader.readLine().split(",");
                    if (accountDetails.length < 5) {
                        throw new InvalidAccountException();
                    }
                    accountFiles.add(accountDetails);
                } catch (IOException | NullPointerException e) {
                    throw new InvalidAccountException();
                }
            }
        }
        return accountFiles;
    }

    public void createAccountFile(String accountData) throws AccountAlreadyExistsException {
        File files = new File("src/resources/account-repository");
        String accountName = accountData.split(",")[0];
        File[] fileList = Objects.requireNonNull(files.listFiles());
        for (File file: fileList) {
            if (file.getName().equals(accountName + ".csv")){
                throw new AccountAlreadyExistsException();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(accountName+".csv")));
            writer.write(accountData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeAccountFile(String accountName){
        File files = new File("src/resources/account-repository");
        File[] fileList = Objects.requireNonNull(files.listFiles());
        for (File file: fileList) {
            if (file.getName().equals(accountName + ".csv")){
               file.delete();
               break;
            }
        }
    }


}
