package persistency.statement;

import exceptions.InvalidStatementException;
import exceptions.StatementAlreadyExistsException;

import java.io.*;
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
                    String[] statementDetails = reader.readLine().split("\\r?\\n");
                    if (statementDetails.length < 2) {
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

    public void createStatementFile(String statementData) throws StatementAlreadyExistsException {
        File files = new File("src/resources/statement-repository");
        String[] statementMetadata = statementData.split("\\r?\\n")[0].split(",");
        File[] fileList = Objects.requireNonNull(files.listFiles());
        for (File file: fileList) {
            if (file.getName().equals(statementMetadata[1] + ".csv")){
                throw new StatementAlreadyExistsException();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(statementMetadata[1]+".csv"));
            writer.write(statementData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeStatementFile(String statementId){
        File files = new File("src/resources/statement-repository");
        File[] fileList = Objects.requireNonNull(files.listFiles());
        for (File file: fileList) {
            if (file.getName().equals(statementId + ".csv")){
                file.delete();
                break;
            }
        }
    }

}