package persistency.account;

import exceptions.AccountAlreadyExistsException;
import exceptions.InvalidAccountException;
import exceptions.NoAccountFilesException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountFileManager {

    public List<String[]> getAccountDataFromFile() throws InvalidAccountException, NoAccountFilesException {
        File files = new File("FinanceManager-plugins/src/main/resources/account-repository");
        List<String[]> accountFiles = new ArrayList<>();
        try {
            if (files.listFiles().length == 0) {
                throw new NoAccountFilesException();
            }
        } catch (NullPointerException e) {
            throw new NoAccountFilesException();
        }
        for (File file : files.listFiles()) {
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
        File files = new File("FinanceManager-plugins/src/main/resources/account-repository");
        String accountName = accountData.split(",")[0];
        try {
            File[] fileList = Objects.requireNonNull(files.listFiles());
            for (File file : fileList) {
                if (file.getName().equals(accountName + ".csv")) {
                    throw new AccountAlreadyExistsException();
                }
            }
            writeAccountData(accountData, files, accountName);
        } catch (NullPointerException ex) {
            writeAccountData(accountData, files, accountName);
        }
    }

    private void writeAccountData(String accountData, File files, String accountName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(files.getPath() + "/" + accountName + ".csv"));
            writer.write(accountData);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeAccountFile(String accountName) {
        File files = new File("FinanceManager-plugins/src/main/resources/account-repository");
        File[] fileList = Objects.requireNonNull(files.listFiles());
        for (File file : fileList) {
            if (file.getName().equals(accountName + ".csv")) {
                file.delete();
                break;
            }
        }
    }


}
