package jdbc.service;

import jdbc.domain.Zwierz;
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
public class ZwierzManager {
    static Connection polaczenie;
    static Statement statement;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://drax1224.unixstorm.org/drax1224_base?connectTimeout=600000";
    static final String USER = "drax1224_base";
    static final String PASS = "drax122";

    public static void poloczenie(){
        try{
            polaczenie = DriverManager.getConnection(DB_URL, USER, PASS);
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

    public static int DodajZwierza(Zwierz zwierz){
        int count = 0;
        try {
            PreparedStatement DodajZwierza = polaczenie.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
            PreparedStatement DodajZwierzaStmt = polaczenie.prepareStatement("INSERT INTO Pracownik (zwierz_ID, wybieg_ID, gatunek, rasa, co_szama) VALUES (?, ?, ?, ?, ?)");
            DodajZwierzaStmt.setString(1, zwierz.getZwierz_ID());
            DodajZwierzaStmt.setString(2, zwierz.getWybieg_ID());
            DodajZwierzaStmt.setString(3, zwierz.getGatunek());
            DodajZwierzaStmt.setString(4, zwierz.getRasa());
            DodajZwierzaStmt.setString(5, zwierz.getCo_szama());
            DodajZwierza.executeUpdate();
            count = DodajZwierzaStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ArrayList<Zwierz> WyswietlZwierza(){
        ArrayList<Zwierz> zwierz = new ArrayList<Zwierz>();
        Zwierz p = new Zwierz();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM Zwierz");

            while(rs.next()){
                p.setZwierz_ID(rs.getString("zwierz_ID"));
                p.setWybieg_ID(rs.getString("wybieg_ID"));
                p.setGatunek(rs.getString("gatunek"));
                p.setRasa(rs.getString("rasa"));
                p.setCo_szama(rs.getString("co_szama"));
                zwierz.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zwierz;
    }
    public static int ZaktualizujZwierza(Zwierz zwierz) {
        int count = 0;
        try {
            PreparedStatement UpdateStmt = polaczenie.prepareStatement("UPDATE Zwierz SET co_szama=?, gatunek=?, rasa=? WHERE zwierz_ID=?;");
            UpdateStmt.setString(1, zwierz.getCo_szama());
            UpdateStmt.setString(2, zwierz.getGatunek());
            UpdateStmt.setString(3, zwierz.getRasa());
            UpdateStmt.setString(4, zwierz.getZwierz_ID());
            count =  UpdateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int UsunZwierza(Zwierz zwierz){
        int count = 0;
        try {
            PreparedStatement delStmt = polaczenie.prepareStatement("DELETE FROM Zwierz WHERE zwierz_ID=?;");
            delStmt.setString(1, zwierz.getZwierz_ID());
            count =  delStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ArrayList<Zwierz> WyswietlZwierzeZWybiegu(Zwierz zwierz){
        ArrayList<Zwierz> Zwierze = new ArrayList<Zwierz>();
        Zwierz z = new Zwierz();
        try {

            PreparedStatement Statement = polaczenie.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
            Statement.executeUpdate();
            String query = "SELECT * FROM Zwierz WHERE wybieg_ID=?;";
            PreparedStatement Ssij = polaczenie.prepareStatement(query);
            Ssij.setString(1, zwierz.getWybieg_ID());

            ResultSet rs = Ssij.executeQuery();

            while(rs.next()){
                z.setZwierz_ID(rs.getString("zwierz_ID"));
                z.setWybieg_ID(rs.getString("wybieg_ID"));
                z.setRasa(rs.getString("rasa"));
                z.setGatunek(rs.getString("gatunek"));
                z.setCo_szama(rs.getString("co_szama"));
                Zwierze.add(z);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Zwierze;
    }

    public static int ZmianaWybieguDlaZwierza(Zwierz zwierz){
        int count = 0;
        try {
            PreparedStatement Statement = polaczenie.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
            PreparedStatement Stmt = polaczenie.prepareStatement("UPDATE Zwierz SET wybieg_ID=? WHERE zwierz_ID=?;");
            Stmt.setString(1, zwierz.getWybieg_ID());
            Stmt.setString(2, zwierz.getZwierz_ID());
            Statement.executeUpdate();
            count = Stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void delTableZwierz() {
        try {
            PreparedStatement WykasujTabelePracownik = polaczenie.prepareStatement("DELETE FROM Pracownik");
            WykasujTabelePracownik.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
