package sample;
import java.sql.*;

public class Andmebaas {
    Connection conn = null; //iga meetod mis midagi teeb, hoiab conni avatud?

    // Constructor, ehk meetod mis käivitub kohe objekti välja kutsumisel
    public Andmebaas() {
        looYhendus();
        looTabel();
    }

    // Et andmebaasi kasutada peame sellega esiteks ühenduse looma
    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                          // Lae draiver sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:test.db"); // loo ühendus andmebaasi failiga
        } catch ( Exception e ) {                                      // püüa kinni võimalikud errorid
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Opened database successfully");            // lihtsalt meie enda jaoks teade
    }

    // Et andmebaasist kasu oleks, loome uue tabeli. See on nagu uus 'sheet' excelis.
    public void looTabel() {
    }

    // Andmebaasi muudatused ei tagasta väärtusi (erinevalt
    // päringutest) ja on lihtne eraldi meetodi tuua.
    private void teostaAndmebaasiMuudatus(String sql) {
        try {
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kui programmis avad ainult ühendusi ja ühtegi ei sulge siis see kulutab arvuti (serveri) ressursse.
    // Varsti võib masin kokku joosta.
    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }

}