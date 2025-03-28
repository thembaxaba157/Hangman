package com.example.DatabaseHandling;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.UserHandling.Score;
import com.example.UserHandling.User;

public class DatabaseManager {
    
    private static final String DISK_DB_URL = "jdbc:sqlite:";
    
    private String dbUrl;
    private Connection connection;

    public DatabaseManager(){
        this.dbUrl = DISK_DB_URL+"hangman.db";
        this.connection = initConnection();
        initDatabase();
      
    }

    public DatabaseManager(String dbFilePath){
        this.dbUrl = DISK_DB_URL+dbFilePath;
        this.connection = initConnection();
        initDatabase();

    }

    private Connection initConnection(){
        try {
            
            return DriverManager.getConnection(this.dbUrl);
        } catch (Exception e) {
            // System.err.println("Database failed, trying to load in memory");
            e.printStackTrace();
            try {
                
                return DriverManager.getConnection("jdbc:sqlite::memory:");
            } catch (Exception f) {
                System.err.println("Failed to load in memory Database");
            }
            
        }
        return null;
    }

    private void initDatabase(){
        try {
            Statement statement = this.connection.createStatement();
            statement.execute("PRAGMA foreign_keys = ON");
            String createPlayersTable = """
                    CREATE TABLE IF NOT EXISTS players (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL
                    );
                    """;

                    String createScoresTable = """
                        CREATE TABLE IF NOT EXISTS scores (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        player_id INTEGER NOT NULL,
                        score INTEGER DEFAULT 0,
                        date_played TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
                        );
                        """;
   
                        String createPointsTable = """
                            CREATE TABLE IF NOT EXISTS points (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            player_id INTEGER NOT NULL UNIQUE,
                            points INTEGER DEFAULT 0,
                            FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
                            );
                            """;

                            statement.execute(createPlayersTable);
                            statement.execute(createScoresTable);
                            statement.execute(createPointsTable);
                            
                  }

                    

    catch(Exception e){
        System.err.println("Failed to create database");
    }

}



public boolean addPlayer(String username){
    try {
        PreparedStatement statement = this.connection.prepareStatement("INSERT INTO players (username) VALUES (?)");
        statement.setString(1, username);
        
      
        statement.setString(1, username);


        int effectedRows = statement.executeUpdate();

        if(effectedRows>0){
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                String insertPointsSql = """
                        INSERT INTO points (player_id, points)
                        VALUES (?, ?)
                        """;
                try {
                    PreparedStatement pointsStatement = this.connection.prepareStatement(insertPointsSql);
                    pointsStatement.setInt(1,id);
                    
                    // statement.setInt(2, 0); no need for this since the points default is a zero which is what we want
                    pointsStatement.executeUpdate();



                } catch (Exception e) {
                    System.err.println("Failed to put points on points table");
                    return false;
            }
                
            
            try {
                String insertScoreSql = """
                        INSERT INTO scores (player_id, score, date_played)
                        VALUES (?, ?, NULL)
                        """;
                PreparedStatement statement2 = this.connection.prepareStatement(insertScoreSql);
                statement2.setInt(1,id);
                statement2.setInt(2, 0);
            } catch(Exception e){
                System.err.println("Failed to put score on scores table");
                return false;
            }
            return true;
        }

        }
        }
        
        
    

catch(Exception E){

}
        
       
    return false;

}


public ArrayList<String> getPlayers(){
    ArrayList<String> players = new ArrayList<String>();
    try {
        String query = "SELECT username FROM players";
        PreparedStatement statement = this.connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            players.add(result.getString("username"));
            }
        }catch(Exception e){
            System.err.println("Failed to get players");
        }

        return players;

}


public User loadPlayer(String username) {
    User user = null;
    String selectPlayerSql = """
            SELECT id, username
            FROM players
            WHERE username = ?
            """;
    
    try {
        PreparedStatement selectPlayerStatement = this.connection.prepareStatement(selectPlayerSql);
        selectPlayerStatement.setString(1, username);

        ResultSet resultSetPlayerTable = selectPlayerStatement.executeQuery();
        if (resultSetPlayerTable.next()) {
            int id = resultSetPlayerTable.getInt("id");
            String username1 = resultSetPlayerTable.getString("username");

            try {
                String selectPointsSql = """
                        SELECT points
                        From points
                        WHERE player_id = ?
                        """;

                PreparedStatement preparedStatement = this.connection.prepareStatement(selectPointsSql);
                preparedStatement.setInt(1, id);
                ResultSet resultSetPointsTable = preparedStatement.executeQuery();
                
                if(resultSetPointsTable.next()){
                    int points = resultSetPointsTable.getInt("points");

                    try {
                            String selectHighScoreSql = """
                                    select MAX(score) AS highscore, date_played
                                    from scores
                                    where player_id = ?
                                    """;
                            PreparedStatement selectHighScoreStatement = this.connection.prepareStatement(selectHighScoreSql);
                            selectHighScoreStatement.setInt(1, id);
                            ResultSet resultSetHighScore = selectHighScoreStatement.executeQuery();

                            if(resultSetHighScore.next()){
                                int highScore = resultSetHighScore.getInt("highscore");
                                Date date = resultSetHighScore.getDate("date_played");
                                user = new User(id, username1, points, new Score(highScore, date));
                            }
                    
                    
                        } catch (Exception e) {
                        // TODO: handle exception
                    }
                }

            } catch (Exception e) {
                System.err.println("Failed loading Player points");
            }
        }

    } catch (SQLException e) {
        
        System.err.println("Failed to execute");
    }
  
return user;
}

public void deletePlayer(String string) {
    try {
        String deletePlayerSql = """
        DELETE FROM players
        WHERE username = ?
        """;
        PreparedStatement deletePlayerStatement = this.connection.prepareStatement(deletePlayerSql);
        deletePlayerStatement.setString(1, string);
        deletePlayerStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to delete player");
            }
}

public void addScoreEntry(User user) {
    try {
        String addScoreSql = """
        INSERT INTO scores (player_id, highscore, date_played)
        VALUES (?, ?, ?)
        """;
        PreparedStatement addScoreStatement = this.connection.prepareStatement(addScoreSql);
        addScoreStatement.setInt(1, user.getId());
        addScoreStatement.setInt(2, user.getCurrScore().getScoreValue());
        addScoreStatement.setDate(3, user.getCurrScore().getDate());
        addScoreStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to add score entry");
            }
            
}

public void updatePoints(User user) {
    try {
        String updatePointsSql = """
            UPDATE points
            SET points = ?
            WHERE player_id = ?
        """;
       PreparedStatement updatePointsStatement = this.connection.prepareStatement(updatePointsSql);
       updatePointsStatement.setInt(1, user.getPoints());
       updatePointsStatement.setInt(2, user.getId());
       updatePointsStatement.executeUpdate();
       } catch (SQLException e) {
        System.err.println("Failed to update points");
        }
        
        }
}
    