

import account.AccountManager;
import account.Owner;

import javax.security.auth.login.AccountException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class FinanceManager {
    //TODO: Plugins: Speichern / Laden - Adapters: CSV Objekt erstellen (CSV String) - Application: Abrechnungen generieren, Buchungen durchführen - Domain: Interfaces für repos Account repo transaction repo statement repo

    private static final String NAME = "SPK Giro";
    private static final Owner OWNER = new Owner("Andreas", "Rheinwalt");

    public static void main(String args[]) {

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);
        System.out.println(stringDate);

    }


}

