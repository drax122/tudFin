package jdbc.service;

import jdbc.domain.Wybieg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by draxeer on 2016-11-23.
 */
public class WybiegManager {
    static Connection polaczenie;
    static Statement statement;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://drax1224.unixstorm.org/drax1224_base";
    static final String USER = "drax1224_base";
    static final String PASS = "drax122";

    public static void polaczenie(){
        try{
            polaczenie = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = polaczenie.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            polaczenie = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = polaczenie.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return polaczenie;
    }

    public static int DodajWybieg(Wybieg wybieg){
        int count = 0;
        try {
            PreparedStatement DodajWybiegStmt = polaczenie.prepareStatement("INSERT INTO Wybieg (wybieg_ID,nazwa_wybiegu, powierzchnia, typ_wybiegu) VALUES (?, ?, ?, ?)");
            DodajWybiegStmt.setString(1, wybieg.getWybieg_ID());
            DodajWybiegStmt.setString(2, wybieg.getNazwa_wybiegu());
            DodajWybiegStmt.setString(3, wybieg.getPowierzchnia());
            DodajWybiegStmt.setString(4, wybieg.getTyp_wybiegu());
            count = DodajWybiegStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ArrayList<Wybieg> WyswietlWybiegi(){
        ArrayList<Wybieg> Wybiegi = new ArrayList<Wybieg>();

        Wybieg ww = new Wybieg();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Wybieg");

            while(rs.next()){
                ww.setWybieg_ID(rs.getString("wybieg_ID"));
                ww.setNazwa_wybiegu(rs.getString("nazwa_wybiegu"));
                ww.setPowierzchnia(rs.getString("powierzchnia"));
                ww.setTyp_wybiegu(rs.getString("typ_wybiegu"));

            }
            Wybiegi.add(ww);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Wybiegi;
    }

    public static int ZaktualizujWybieg(Wybieg update) {
        int count = 0;
        try {
            PreparedStatement UpdateStmt = polaczenie.prepareStatement("UPDATE Wybieg SET nazwa_wybiegu=?, powierzchnia=?, typ_wybiegu=? WHERE wybieg_ID=?;");
            UpdateStmt.setString(1, update.getNazwa_wybiegu());
            UpdateStmt.setString(2, update.getPowierzchnia());
            UpdateStmt.setString(3, update.getTyp_wybiegu());
            UpdateStmt.setString(4, update.getWybieg_ID());
            count = UpdateStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int UsunWybieg(Wybieg delete){
        int count = 0;
        try {
            PreparedStatement DelStmt = polaczenie.prepareStatement("DELETE FROM Wybieg WHERE wybieg_ID=?;");
            DelStmt.setString(1, delete.getWybieg_ID());
            count = DelStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void delTableWybieg() {
        try {
            PreparedStatement delTable = polaczenie.prepareStatement("DELETE FROM Wybieg");
            delTable.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
