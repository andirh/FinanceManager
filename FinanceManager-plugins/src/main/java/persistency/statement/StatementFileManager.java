package persistency.statement;

import account.Owner;
import exceptions.InvalidAccountException;
import exceptions.InvalidStatementException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatementFileManager {

    public List<String[]> getStatementDataFromFile() throws InvalidStatementException {
        File files = new File("src/resources/statement-repository");
        List<String[]> statementFiles = new ArrayList<>();
        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (file.getName().endsWith(".csv")) {
                try {
                    BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
                    String[] statementDetails = reader.readLine().split(",");
                    if (statementDetails.length < 4) {
                        throw new InvalidStatementException();
                    }
                    statementFiles.add(statementDetails);
                } catch (IOException | NullPointerException e) {
                    throw new InvalidStatementException();
                }
            }
        }
        return statementFiles;
    }

}
