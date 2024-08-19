package Utils;

import java.io.*;
import java.sql.*;

public class DBConnector {
    private final String url = "jdbc:mysql://localhost:3306/fp2_23b";
    private final String user = "fp2_23b";
    private final String password = "12345678";
    private Connection connection;

    public static void main(String[] args) {
        new DBConnector();
    }

    public DBConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);
            if (!checkInitialized()) {
                initDatabase();
                writeInitialized();
            }

            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkInitialized() {
        File initFile = new File("data/dbinit.dat");
        return initFile.exists();
    }

    private void writeInitialized() throws IOException {
        File dir = new File("data/");
        if (!dir.exists())
            dir.mkdirs();
        File initFile = new File("data/dbinit.dat");
        initFile.createNewFile();
    }

    private void initDatabase() throws SQLException {
        connection.prepareStatement(
                "CREATE TABLE players_videogame (id int NOT NULL AUTO_INCREMENT, name varchar(30) NOT NULL, password varchar(30) NOT NULL, PRIMARY KEY (id))")
                .execute();
        connection.prepareStatement(
                "CREATE TABLE matches_videogame (id int NOT NULL AUTO_INCREMENT, winner_id int NOT NULL, loser_id int NOT NULL, PRIMARY KEY (id), INDEX winner_id (winner_id), INDEX loser_id (loser_id))")
                .execute();
        connection.prepareStatement(
                "ALTER TABLE matches_videogame ADD CONSTRAINT winner_id FOREIGN KEY (winner_id) REFERENCES players_videogame(id) ON DELETE CASCADE ON UPDATE CASCADE")
                .execute();
        connection.prepareStatement(
                "ALTER TABLE matches_videogame ADD CONSTRAINT loser_id FOREIGN KEY (loser_id) REFERENCES players_videogame(id) ON DELETE CASCADE ON UPDATE CASCADE")
                .execute();
    }

    public int loginPlayer(String name, String password) {
        try {
            String query = String.format("SELECT id FROM players_videogame WHERE name = '%s' AND password = '%s'", name,
                    password);
            ResultSet results = connection.prepareStatement(query).executeQuery();
            if (results.next())
                return results.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int[] getWinsLoses(int id) {
        try {
            int[] totals = new int[2];
            String query = String.format("SELECT COUNT(*) from matches_videogame WHERE winner_id = '%d'", id);
            ResultSet results = connection.prepareStatement(query).executeQuery();
            if (results.next())
                totals[0] = results.getInt(1);

            query = String.format("SELECT COUNT(*) from matches_videogame WHERE loser_id = '%d'", id);
            results = connection.prepareStatement(query).executeQuery();
            if (results.next())
                totals[1] = results.getInt(1);

            return totals;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registerPlayer(String name, String password) {
        try {
            String query = String.format("INSERT INTO players_videogame (name, password) VALUES ('%s', '%s')", name, password);
            connection.prepareStatement(query).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMatch(int winner_id, int loser_id) {
        try {
            String query = String.format("INSERT INTO matches_videogame (winner_id, loser_id) VALUES ('%d', '%d')", winner_id,
                    loser_id);
            System.out.println(query);
            connection.prepareStatement(query).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
