package persistency;

import account.Owner;
import exceptions.InvalidAccountException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileManager {

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

    public List<File> getStatementDataFromFile() {
        File files = new File("src/resources/statement-repository");
        List<File> statementFiles = new ArrayList<>();
        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (file.getName().endsWith(".csv")) {
                statementFiles.add(file);
            }
        }
        return statementFiles;
    }

}
