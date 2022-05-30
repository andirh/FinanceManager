package persistency.statement;

import exceptions.InvalidStatementException;
import exceptions.NoStatementFoundException;
import exceptions.StatementAlreadyExistsException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatementFileManager {

    public List<String[]> getStatementDataFromFile(long accountId) throws InvalidStatementException, NoStatementFoundException {
        File files = new File("FinanceManager-plugins/src/main/resources/statement-repository/" + accountId);
        List<String[]> statementFiles = new ArrayList<>();
        try {
            if (files.listFiles().length == 0) {
                throw new NoStatementFoundException();
            }
        } catch (NullPointerException e) {

            throw new NoStatementFoundException();
        }
        for (File file : files.listFiles()) {
            if (file.getName().endsWith(".csv")) {
                try {
                    BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
                    List<String> statementContent = new ArrayList<>();
                    for (String line; (line = reader.readLine()) != null;) {
                        statementContent.add(line);
                    }
                    if (statementContent.get(0).split(",").length < 2) {
                        throw new InvalidStatementException();
                    }
                    statementFiles.add(statementContent.toArray(new String[0]));
                } catch (IOException | NullPointerException e) {
                    throw new InvalidStatementException();
                }
            }
        }
        return statementFiles;
    }


    public void createStatementFile(String statementData) {
        String[] statementMetadata = statementData.split(System.lineSeparator())[0].split(",");
        File files = new File("FinanceManager-plugins/src/main/resources/statement-repository/" + statementMetadata[0]);
        files.mkdirs();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(files.getPath() + "/" + statementMetadata[1] + ".csv"));
            writer.write(statementData);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeStatementFile(String statementId, long accountId) {
        File files = new File("FinanceManager-plugins/src/main/resources/statement-repository/" + accountId);
        File[] fileList = Objects.requireNonNull(files.listFiles());
        for (File file : fileList) {
            if (file.getName().equals(statementId + ".csv")) {
                file.delete();
                break;
            }
        }
    }

}
